package poly.vinh.module.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	public void addMail(String to, String title, String text) {
		HashMap<String, String> value = new HashMap<String, String>();
		value.put("to", to);
		value.put("subject", title);
		value.put("text", text);
		this.queueList.add(value);
	}

	@Autowired
	private JavaMailSender sender;
	
	private Queue<Map<String, String>> queueList = new LinkedList<>();

	private void send(Map<String, String> info) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		
		helper.setFrom("Vibaodin <noreply.vinh.bot@gmail.com>");
		helper.setTo(info.get("to"));
		helper.setSubject(info.getOrDefault("subject", "Tin nhắn tự động từ Vibaodin"));
		helper.setText(info.getOrDefault("text", "Hệ thống lỗi, xin lỗi quý khách hàng"), true);
		helper.setReplyTo("vinhlmpc01238@fpt.edu.vn");

		this.sender.send(message);

	}

	@Scheduled(fixedDelay = 5000)
	private void run() {
		try {
			if (!this.queueList.isEmpty()) {
				this.send(queueList.poll());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
