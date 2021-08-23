package poly.vinh.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.vinh.module.service.SessionService;
import poly.vinh.repository.entity.Account;

@Controller
public class RoleUserInterceptor implements HandlerInterceptor {
	
	@Autowired
	SessionService sessionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		Account account = sessionService.<Account>get("loginAccount");
		if (null == account) {
			response.sendRedirect("/?lastUri="+request.getRequestURI());
			return false;
		}
		return true;
	}
	
	@RequestMapping("/signin-er-ur")
	String gogo(HttpServletRequest request, RedirectAttributes redirectAttribute) {
		redirectAttribute.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục sử dụng");
		redirectAttribute.addFlashAttribute("lastUri", request.getParameter("lastUri"));
		return "redirect:/signin";
	}

}
