package poly.vinh.controller.api.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.vinh.repository.AccountRepository;
import poly.vinh.repository.BasketRepository;
import poly.vinh.repository.entity.Account;
import poly.vinh.repository.entity.Basket;
import poly.vinh.repository.entity.BasketId;

@RequestMapping("/api/user/basket")
@RestController
public class BasketAPI {
	
	@Autowired
	private BasketRepository basketDao;
	
	@Autowired
	private AccountRepository accountDao;
	
	@PostMapping("add")
	boolean add(@RequestBody() HashMap<String, Integer> body) {
		Integer sidProduct = body.getOrDefault("idProduct", -1);
		Integer sidAccount = body.getOrDefault("idAccount", -1);
		
		if (-1 == sidProduct  || -1 == sidAccount) {
			return false;
		}
		
		Basket bk = new Basket();
		bk.setIdAccount(sidAccount);
		bk.setIdProduct(sidProduct);
		bk.setQuantity(1);
		basketDao.save(bk);
		return true;
	}
	
	@PostMapping("remove")
	boolean remove(@RequestBody HashMap<String, Integer> body) {
		Integer sidProduct = body.getOrDefault("idProduct", -1);
		Integer sidAccount = body.getOrDefault("idAccount", -1);
		if (-1 == sidProduct  || -1 == sidAccount) {
			return false;
		}
		basketDao.deleteById(new BasketId(sidAccount, sidProduct));
		return true;
	}
	
	@GetMapping("get-by-account")
	List<Basket> getBaskets(@RequestParam(name = "idAccount") Integer idAccount) {
		Account account = accountDao.findById(idAccount).orElse(null);
		if (null == account || null == account.getBaskets() || account.getBaskets().isEmpty()) {
			return null;
		}		
		return account.getBaskets();
	}
	
}
