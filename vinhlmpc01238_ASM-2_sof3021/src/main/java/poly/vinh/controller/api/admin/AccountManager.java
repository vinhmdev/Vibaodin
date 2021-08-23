package poly.vinh.controller.api.admin;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poly.vinh.module.service.ParamService;
import poly.vinh.repository.AccountRepository;
import poly.vinh.repository.entity.Account;
import poly.vinh.repository.entity.Basket;
import poly.vinh.repository.entity.Bill;

@RequestMapping("/api/admin/account-manager")
@RestController
public class AccountManager {
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private AccountRepository accountDao;
	
	@GetMapping("get-all")
	List<Account> getAll() {
		return accountDao.findAll();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@PostMapping("add")
	Account add(@RequestPart(name = "file", required = false) MultipartFile file, @RequestParam HashMap<String, String> param) {
		String username = param.get("username");
		String email = param.get("email");
		String password = param.get("password");
		String avatar = param.get("avatar");
		String phone = param.get("phone");
		String fullname = param.get("fullname");
		String address = param.get("address");
		Boolean man = param.get("man").toString().equalsIgnoreCase("true");
		Boolean enable = param.get("enable").toString().equalsIgnoreCase("true");
		Boolean admin = param.get("admin").toString().equalsIgnoreCase("true");
		
		Account account = new Account(null, username, email, password, avatar, phone, fullname, address, man, enable, admin, null, null);

		if (null != file && !file.getOriginalFilename().isBlank()) {
			File fDesk = paramService.save(file, "/images/accounts");
			account.setAvatar(fDesk.getName());
		}
		
		accountDao.save(account);
		return account;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@PostMapping("update")
	Account update(@RequestPart(name = "file", required = false) MultipartFile file, @RequestParam HashMap<String, String> param) {
		Integer idAccount = -1;
		try {
			idAccount = Integer.parseInt(param.getOrDefault("idAccount", "-1"));
		} catch (Exception e) {
			return null;
		}
		if (-1 == idAccount) {
			return null;
		}
		String username = param.get("username");
		String email = param.get("email");
		String password = param.get("password");
		String avatar = param.get("avatar");
		String phone = param.get("phone");
		String fullname = param.get("fullname");
		String address = param.get("address");
		Boolean man = param.get("man").toString().equalsIgnoreCase("true");
		Boolean enable = param.get("enable").toString().equalsIgnoreCase("true");
		Boolean admin = param.get("admin").toString().equalsIgnoreCase("true");
		
		Account account = new Account(idAccount, username, email, password, avatar, phone, fullname, address, man, enable, admin, null, null);
		if (null != file && !file.getOriginalFilename().isBlank()) {
			File fDesk = paramService.save(file, "/images/accounts");
			account.setAvatar(fDesk.getName());
		}
		
		accountDao.save(account);
		return account;
	}
	
	@PostMapping("delete")
	boolean delete(@RequestParam("idAccount") Integer idAccount) {
		try {
			accountDao.deleteById(idAccount);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
