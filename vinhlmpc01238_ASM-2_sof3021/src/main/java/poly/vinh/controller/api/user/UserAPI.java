package poly.vinh.controller.api.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poly.vinh.module.service.ParamService;
import poly.vinh.repository.AccountRepository;
import poly.vinh.repository.entity.Account;

@RequestMapping("/api/user")
@RestController
public class UserAPI {
	
	private final String TOKEN_JSON = "{\"token\": \"%s\"}";
	
	@Autowired
	private AccountRepository accountDao;
	
	@Autowired
	private ParamService paramService;

	@PostMapping("repass")
	String repass(@RequestBody HashMap<String, String> body) {
		String username = body.get("username");
		String pwd = body.get("password");
		String pwd1 = body.get("passwordN1");
		String pwd2 = body.get("passwordN2");
		if (!pwd1.equals(pwd2)) {
			return null;
		}
		Account oriAcc = accountDao.findByUsernameLikeAndPasswordLike(username, pwd);
		if (null == oriAcc) {
			return null;
		}
		oriAcc.setPassword(pwd1);		
		accountDao.save(oriAcc);
		return String.format(this.TOKEN_JSON, oriAcc);
	}

	@PostMapping("change-profile")
	boolean changeProfile(@RequestParam HashMap<String, String> param, @RequestPart(name = "file", required = false) MultipartFile file) {
		Account oriAcc = accountDao.findByUsernameLikeAndPasswordLike(param.get("username"), param.get("password"));
		if (null == oriAcc) {
			return false;
		}
		
		oriAcc.setFullname(param.get("fullname"));
		oriAcc.setMan(param.get("man").equalsIgnoreCase("true"));
		oriAcc.setEmail(param.get("email"));
		oriAcc.setPhone(param.get("phone"));
		oriAcc.setAddress(param.get("address"));
		
		String avatar = null;
		
		if (null != file && !file.getOriginalFilename().isBlank()) { 
			File fdesk = this.paramService.save(file, "/images/accounts");
			avatar = fdesk.getName();
			if (null != oriAcc.getAvatar() && !oriAcc.getAvatar().isBlank() && !oriAcc.getAvatar().equals(avatar)) {
				try {
					Files.delete(new File(fdesk.getParentFile().getAbsolutePath() + "/" + oriAcc.getAvatar()).toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (null == avatar || avatar.isBlank()) {
			avatar = oriAcc.getAvatar();
		}
		
		oriAcc.setAvatar(avatar);
		
		try {
			accountDao.save(oriAcc);
		} catch(Exception ex) {
			return false;
		}

		return true;
	}
	
}
