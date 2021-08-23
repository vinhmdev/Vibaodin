package poly.vinh.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.vinh.module.service.MailWelcomeService;
import poly.vinh.repository.AccountRepository;
import poly.vinh.repository.entity.Account;

@RequestMapping("/api/sign")
@RestController
public class SignAPI {
	
	private final String TOKEN_JSON = "{\"token\": \"%s\"}";
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private AccountRepository accountDao;
	
	@Autowired
	private JsonWebToken jwtService;
	
	@Autowired
	private MailWelcomeService welcomeService;
	
	@PostMapping("signin")
	String signin(@RequestBody Account account) {
		Account signin = accountDao.findByUsernameLikeAndPasswordLike(account.getUsername(), account.getPassword());
		if (null == signin) {
			return null;
		}
		this.request.getSession().setAttribute("loginAccount", signin);
		return String.format(this.TOKEN_JSON, jwtService.generateToken(signin));
	}
	
	@PostMapping("signup")
	String signup(@RequestBody Account account) {
		try {
			accountDao.save(account);
			welcomeService.send(account);
			this.request.getSession().removeAttribute("loginAccount");
			return String.format(this.TOKEN_JSON, jwtService.generateToken(account));
		} catch (Exception e) {
			String err = e.getCause().getCause().getMessage();
			System.err.println(err);
			return null;
		}
	}
	
}
