package poly.vinh.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.vinh.repository.ProductRepository;
import poly.vinh.repository.ProductTypeRepository;
import poly.vinh.repository.entity.Product;

@RequestMapping("/api")
@RestController
public class ProductAPI {
	
	@Autowired
	private ProductRepository productDao;
	
	@Autowired
	private ProductTypeRepository productTypeDao;
	
	@RequestMapping("product/get-products")
	List<Product> getProducts() {
		return productDao.findAll();
	}
	
	@RequestMapping("get-product-types")
	List<String> getProductTypes() {
		return productTypeDao.findTypes();
	}
	
	@RequestMapping("/product/get-one") 
	Product getProduct(@RequestParam("idProduct") Integer idProduct) {
		return productDao.findById(idProduct).orElse(null);
	}
	
}
