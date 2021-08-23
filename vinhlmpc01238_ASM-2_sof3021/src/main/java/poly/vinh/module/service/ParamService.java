package poly.vinh.module.service;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {

	@Autowired
	private HttpServletRequest _request;

	public File save(MultipartFile mulFile, String pathSource) {
		try {

			String folder = _request.getServletContext().getRealPath(pathSource);
			String fileName = Integer.toHexString(mulFile.getBytes().hashCode()) + "_" + Long.toHexString(new Date().getTime()) + "." + mulFile.getOriginalFilename().substring(mulFile.getOriginalFilename().lastIndexOf(".") + 1);
			File file = new File(folder.concat("/").concat(fileName));
			
			if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
			
			mulFile.transferTo(file);
			
			return file;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
