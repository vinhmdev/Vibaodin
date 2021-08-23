package poly.vinh.controller.api.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.vinh.repository.AccountRepository;
import poly.vinh.repository.BasketRepository;
import poly.vinh.repository.BillRepository;
import poly.vinh.repository.DetailBillRepository;
import poly.vinh.repository.ProductRepository;
import poly.vinh.repository.entity.Account;
import poly.vinh.repository.entity.Basket;
import poly.vinh.repository.entity.Bill;
import poly.vinh.repository.entity.DetailBill;
import poly.vinh.repository.entity.Product;

@RequestMapping("api/user/buy")
@RestController
public class BuyAPI {
	
	@Autowired
	private BasketRepository basketDao;
	
	@Autowired
	private AccountRepository accountDao;
	
	@Autowired
	private ProductRepository productDao;
	
	@Autowired
	private BillRepository billDao;
	
	@Autowired
	private DetailBillRepository detailDao;
	
	@Transactional(rollbackFor = Exception.class)
	@PostMapping("add")
	boolean add(@RequestBody List<Basket> baskets, @RequestParam("idAccount") Integer idAccount) {
		
		if (baskets.isEmpty()) {
			return false;
		}

		System.err.println(idAccount);
		
		Account account = accountDao.findById(idAccount).orElse(null);
		if (null == account) {
			return false;
		}
		
		account.setBills(null);
		account.setBaskets(null);
		account.setIdAccount(idAccount);
		
		Bill bill = new Bill(null, new Date(), idAccount, account, null);
		billDao.save(bill);
		
		List<DetailBill> details = new ArrayList<DetailBill>();
		baskets.forEach(basket -> {
			Product product = this.productDao.findById(basket.getIdProduct()).orElse(null);
			if(null != product) {
				DetailBill db = new DetailBill();
				
				db.setIdBill(bill.getIdBill());
				db.setBill(bill);
				db.setIdProduct(product.getIdProduct());
				db.setProduct(product);
				db.setQuantity(basket.getQuantity());
				db.setUnitPrice(product.getPrice());
				
				details.add(db);
			}
		});
		
		detailDao.saveAll(details);

		basketDao.deleteAll(baskets);
		
		return true;
	}
	
	
}
