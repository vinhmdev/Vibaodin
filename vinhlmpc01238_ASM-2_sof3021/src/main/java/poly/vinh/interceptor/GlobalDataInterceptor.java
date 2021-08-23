package poly.vinh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import poly.vinh.repository.ProductTypeRepository;

@Controller
public class GlobalDataInterceptor implements HandlerInterceptor{
	
	@Autowired
	private ProductTypeRepository productTypeDao;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("productTypes", productTypeDao.findTypes());
	}
	
}
