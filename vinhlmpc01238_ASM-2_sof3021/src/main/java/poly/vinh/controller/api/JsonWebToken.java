package poly.vinh.controller.api;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import poly.vinh.repository.entity.Account;

@Service
public class JsonWebToken {
	
	private final String JWT_SECRET = "key_secret_of_vinh_5y~Y}&?8";
	private final long JWT_EXPIRATION = 1_800_000L; // 30 minutes
	
	public String generateToken(Account account) {
		Date now = new Date();
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET)
				.setSubject(String.valueOf(account.getIdAccount()))
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + JWT_EXPIRATION))
				.compact();
	}
	
	public Integer getIdAccountFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(JWT_SECRET)
				.parseClaimsJws(token)
				.getBody();
		return Integer.parseInt(claims.getSubject());
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
			return false;
		}
	}
	
}
