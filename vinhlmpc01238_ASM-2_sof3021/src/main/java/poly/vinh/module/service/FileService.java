package poly.vinh.module.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class FileService {
	
	@Setter
	@Getter
	private String path;
	
	@Autowired
	HttpServletRequest request;
	
	public String read() {
		String realPath = this.request.getServletContext().getRealPath(this.getPath());
		File file = new File(realPath);
		try {
			return Files.readString(file.toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.err.println(e);
			return "";
		}
	}
}
