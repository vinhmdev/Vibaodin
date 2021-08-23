package poly.vinh.controller.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.vinh.repository.ProductRepository;

@Controller
public class IndexController {
	
	@Autowired
	ProductRepository productDao;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@RequestMapping("/")
	String index() {
		return "index.html";
	}
	
}
