package poly.vinh.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.vinh.repository.entity.Account;

@Service
public class MailWelcomeService {
	
	private final String path = "/mail/welcome.html";
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private FileService fileService;
	
	public void send(Account account) {
		String name = account.getFullname();
		if (null == name || name.isBlank()) {
			name = account.getUsername();
		}
		fileService.setPath(this.path);
		String textMail = String.format(fileService.read(), name);
		mailService.addMail(account.getEmail(), "Thư chào mừng đến với Vibaodin", textMail);
		
	}
	
}
