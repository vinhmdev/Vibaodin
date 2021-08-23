package poly.vinh.module.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

@Service
public class SessionService {
	
	@Autowired
	private HttpServletRequest _request;
	
	@Autowired
	private HttpServletResponse _response;

	@Autowired
	private HttpSession _session;

	/**
	* Đọc giá trị của attribute trong session
	* @param name tên attribute
	* @return giá trị đọc được hoặc null nếu không tồn tại
	*/
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) _session.getAttribute(name);
	}

	/**
	* Thay đổi hoặc tạo mới attribute trong session
	* @param name tên attribute
	* @param value giá trị attribute
	*/
	public void set(String name, Object value) {
		_session.setAttribute(name, value);
	}

	public void set(String name, Object value, int timeLive) {
		Cookie cookie = WebUtils.getCookie(_request, "JSESSIONID");
		cookie.setMaxAge(timeLive);
		_response.addCookie(cookie);
		_session.setMaxInactiveInterval(timeLive);
		_session.setAttribute(name, value);
	}

	/**
	* Xóa attribute trong session
	* @param name tên attribute cần xóa
	*/
	public void remove(String name) {
		_session.removeAttribute(name);
	}

}
