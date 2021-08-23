package poly.vinh.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.vinh.module.service.SessionService;
import poly.vinh.repository.AccountRepository;
import poly.vinh.repository.entity.Account;

@RequestMapping("/api")
@RestController
public class GlobalAPI {
	
	@Autowired
	private JsonWebToken jwtService;
	
	@Autowired
	private AccountRepository accountDao;
	
	@GetMapping("get-account-login")
	Account getAccountLogin(@RequestParam("token") String token) {
		System.err.println(token);
		if (!jwtService.validateToken(token)) {
			return null;
		}
		Integer idAccount = jwtService.getIdAccountFromToken(token);
		return accountDao.findById(idAccount).orElse(null);
	}
	
}
