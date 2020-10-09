package br.fai.dogs.api.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class RequestInterceptor implements HandlerInterceptor {

	@Value("${jwt.secret}")
	private String secret;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean tokenValido = validaTokenJwt(request.getHeader("Authorization"));
		
		if(tokenValido) {
			return true;
		}
		
		response.sendError(401);
		return false;
		
	}

	public boolean validaTokenJwt(String token) {
				
		boolean valido = false;
		
		try {
			
			Calendar calendario = Calendar.getInstance();
			calendario.setTimeInMillis(System.currentTimeMillis());
			
			JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
			
			valido = true;
			
		} catch (JWTVerificationException eJwt) {
			System.out.println(eJwt.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return valido;
		
	}

}