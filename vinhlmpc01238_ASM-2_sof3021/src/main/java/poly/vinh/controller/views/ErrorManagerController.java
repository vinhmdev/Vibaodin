package poly.vinh.controller.views;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class ErrorManagerController implements ErrorController {
	
	@RequestMapping("/error")
	String getError() {
//		return "<a href=\"/\"><h1>Về Trang chủ</h1></a>";
		return "index.html";
	}
	
}
