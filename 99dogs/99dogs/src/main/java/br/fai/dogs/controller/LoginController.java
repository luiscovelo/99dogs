package br.fai.dogs.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.PessoaService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping("/autenticacao")
	public String autenticacao(Pessoa dadosForm, HttpSession session) {
		
		Pessoa pessoa = new Pessoa();   
		
		pessoa = pessoaService.validarLogin(dadosForm);

		if(pessoa != null) {
			
			pessoaService.gravarSessao(session, pessoa);
			
			if(pessoa.getTipo().equals("CLIENTE")) {
				
				return "redirect:/dashboard/cliente";
				
			}else if(pessoa.getTipo().equals("PROFISSIONAL")) {
				
				return "redirect:/dashboard/profissional";
				
			}
			
			return "redirect:/login";
			
		}
		
		return "redirect:/login";
		
	}
	
	@Value("${jwt.secret}")
	private String SECRET_KEY_JWT;
	
	@GetMapping("/token")
	public String geraTokenJwt(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			String username = auth.getName();
						
			String jwt = JWT.create()
					.withClaim("username", username)
					.sign(Algorithm.HMAC256(SECRET_KEY_JWT));

			Cookie cookie = new Cookie("token", jwt);
			
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			cookie.setMaxAge(60 * 30);
			
			httpResponse.addCookie(cookie);
			
			if (httpRequest.isUserInRole("CLIENTE")) {
			    return "redirect:/dashboard/cliente";
			}else if(httpRequest.isUserInRole("PROFISSIONAL")) {
				return "redirect:/dashboard/profissional";
			}
			
		} catch (Exception e) {
			return "redirect:/logout?error=" + e.getMessage();
		}
		
		return "redirect:/logout";
		
	}
	
}
