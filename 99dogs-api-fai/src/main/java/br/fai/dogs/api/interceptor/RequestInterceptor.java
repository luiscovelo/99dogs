package br.fai.dogs.api.interceptor;

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
		
		if(request.getServletPath().equals("/api/v1/pessoa/validarLogin")) {
			return true;
		}
		
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

			JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
			valido = true;
			
		} catch (JWTVerificationException eJwt) {
			valido = false;
		} catch (Exception e) {
			valido = false;
		}
		
		return valido;
		
	}

}