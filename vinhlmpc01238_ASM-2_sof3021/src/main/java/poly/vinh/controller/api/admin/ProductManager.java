package poly.vinh.controller.api.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poly.vinh.module.service.ParamService;
import poly.vinh.repository.ProductImageRepository;
import poly.vinh.repository.ProductRepository;
import poly.vinh.repository.ProductTypeRepository;
import poly.vinh.repository.entity.Account;
import poly.vinh.repository.entity.Product;
import poly.vinh.repository.entity.ProductImage;
import poly.vinh.repository.entity.ProductType;

@RequestMapping("/api/admin/product-manager")
@RestController
public class ProductManager {
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private ProductRepository productDao;
	
	@Autowired
	private ProductImageRepository imageDao;
	
	@Autowired
	private ProductTypeRepository typeDao;
	
	@GetMapping("get-all")
	List<Product> getAll() {
		return productDao.findAll();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@PostMapping("add")
	Product add(
			@RequestPart(name = "file", required = false) MultipartFile[] file, 
			@RequestParam HashMap<String, String> param, 
			@RequestParam(name = "productTypes", required = false) List<String> productTypes) {
		
		Integer idProduct = null; // for new save
		String title = param.get("title");
		Double price = Double.parseDouble(param.getOrDefault("price", "0"));
		String trademark = param.get("trademark");
		Double guaratee = Double.parseDouble(param.getOrDefault("guaratee", "0"));
		String deliveryAddress = param.get("deliveryAddress");
		String detail = param.get("detail");

		Product product = new Product(idProduct, title, price, trademark, guaratee, deliveryAddress, detail, null, null, null, null);
		
		this.productDao.save(product);
		
		List<ProductType> types = new ArrayList<ProductType>();
		productTypes.forEach(item -> {
			types.add(new ProductType(null, product, item));
		});
		this.typeDao.saveAll(types);
		
		List<ProductImage> images = new ArrayList<ProductImage>();
		if (file != null) {
			for (MultipartFile f : file) {
				if (f.getOriginalFilename().isBlank()) {
					continue;
				}
				File fDesk = this.paramService.save(f, "/images/products");
				images.add(new ProductImage(null, product, fDesk.getName(), "Ảnh mô tả của ".concat(product.getTitle())));
			}
		}
		this.imageDao.saveAll(images);
		
		product.setProductImages(images);
		return product;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@PostMapping("update")
	Product update(
			@RequestPart(name = "file", required = false) MultipartFile[] file, 
			@RequestParam HashMap<String, String> param, 
			@RequestParam(name = "productTypes", required = false) List<String> productTypes) {
		//
		Integer idProduct = Integer.parseInt(param.getOrDefault("idProduct", "-1")); // for new save
		String title = param.get("title");
		Double price = Double.parseDouble(param.getOrDefault("price", "0"));
		String trademark = param.get("trademark");
		Double guaratee = Double.parseDouble(param.getOrDefault("guaratee", "0"));
		String deliveryAddress = param.get("deliveryAddress");
		String detail = param.get("detail");
		if (-1 == idProduct) {
			return null;
		}
		Product oriPro = productDao.findById(idProduct).get();
		oriPro.setTitle(title);
		oriPro.setPrice(price);
		oriPro.setTrademark(trademark);
		oriPro.setGuaratee(guaratee);
		oriPro.setDeliveryAddress(deliveryAddress);
		oriPro.setDetail(detail);
		this.productDao.save(oriPro);
		//		
		
		if (null == oriPro.getProductTypes()) {
			oriPro.setProductTypes(new ArrayList<ProductType>());
		}
		
		List<ProductType> types = new ArrayList<ProductType>();
		typeDao.deleteAll(oriPro.getProductTypes());
		productTypes.forEach(item -> {
			types.add(new ProductType(null, oriPro, item));
		});
		this.typeDao.saveAll(types);
		//		
		List<ProductImage> deleteImage = new ArrayList<ProductImage>();
		List<ProductImage> oldImages = oriPro.getProductImages(); 
		oldImages.sort((i, ii) -> {
			return i.getIdImage() < ii.getIdImage() ? -1 : 1;
		});
		List<ProductImage> images = new ArrayList<ProductImage>();
		if (file != null) {
			for (MultipartFile f : file) {
				if (f.getOriginalFilename().isBlank()) {
					continue;
				}
				File fDesk = this.paramService.save(f, "/images/products");
				images.add(new ProductImage(null, oriPro, fDesk.getName(), "Ảnh mô tả của ".concat(oriPro.getTitle())));
				if (null != oldImages && !oldImages.isEmpty() && (oldImages.size() + images.size()) > 4 ) {
					deleteImage.add(oldImages.remove(0));
				}
			}
		}
		this.imageDao.deleteAll(deleteImage);
		this.imageDao.saveAll(images);
		//	
		images.addAll(oldImages);
		oriPro.setProductImages(images);
		return oriPro;
	}
	
	@PostMapping("delete")
	boolean delete(@RequestParam("idProduct") Integer idProduct) {
		try {
			productDao.deleteById(idProduct);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
