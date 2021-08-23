package poly.vinh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import poly.vinh.interceptor.GlobalDataInterceptor;
import poly.vinh.interceptor.RoleAdminInterceptor;
import poly.vinh.interceptor.RoleUserInterceptor;

@Configuration
public class ConfigApplication implements WebMvcConfigurer {
	
	@Autowired
	RoleAdminInterceptor roleAdminInterceptor;
	
	@Autowired
	RoleUserInterceptor roleUserInterceptor;
	
	@Autowired
	GlobalDataInterceptor globalDataInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.roleAdminInterceptor)
		.addPathPatterns("/admin/**");
		
		registry.addInterceptor(this.roleUserInterceptor)
		.addPathPatterns("/user/**");
		
		registry.addInterceptor(this.globalDataInterceptor)
		.addPathPatterns("/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		.allowedOrigins("*")
		.allowedHeaders("*");
	}
	
}
