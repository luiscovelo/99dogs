package br.fai.dogs.controller;

import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.PessoaService;

@Controller
public class ContaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/criar-conta-cliente")
	public String criarContaCliente(HttpServletResponse httpResponse, Pessoa pessoa) {
		
		try {
			
			String tokenTemporario = tokenJwt(httpResponse,pessoa.getEmail(), 30, false);

			if(tokenTemporario != null) {
				
				Pessoa pessoaExistente = pessoaService.readByEmail(pessoa.getEmail(), tokenTemporario);
				
				if(pessoaExistente != null && pessoaExistente.getId() > 0) {
					throw new Exception("email_in_use");
				}
				
				pessoa.setFoto("#");
				pessoa.setPais("Brasil");
				pessoa.setTipo("CLIENTE");
				pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
				
				Long pessoa_id = pessoaService.create(pessoa,tokenTemporario);
				
				if(pessoa_id != null && pessoa_id > 0) {
					
					return "redirect:/login?createdSuccess";
					
				}
				
			}else {
				throw new Exception("token_nao_foi_gerado");
			}
			
		} catch (Exception e) {

			return "redirect:/quero-encontrar-dogwalkers?error=" + e.getMessage();
			
		}
		
		return "redirect:/quero-encontrar-dogwalkers?createdFailed";
		
	}
	
	@PostMapping("/criar-conta-profissional")
	public String criarContaProfissional(HttpServletResponse httpResponse, Pessoa pessoa) {
		
		try {
			
			String tokenTemporario = tokenJwt(httpResponse,pessoa.getEmail(), 30, false);

			if(tokenTemporario != null) {
				
				Pessoa pessoaExistente = pessoaService.readByEmail(pessoa.getEmail(), tokenTemporario);
				
				if(pessoaExistente != null && pessoaExistente.getId() > 0) {
					throw new Exception("email_in_use");
				}
				
				pessoa.setFoto("#");
				pessoa.setPais("Brasil");
				pessoa.setTipo("PROFISSIONAL");
				pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
				
				Long pessoa_id = pessoaService.create(pessoa,tokenTemporario);
				
				if(pessoa_id != null && pessoa_id > 0) {
					
					return "redirect:/login?createdSuccess";
					
				}
				
			}else {
				throw new Exception("token_nao_foi_gerado");
			}
			
		} catch (Exception e) {

			return "redirect:/quero-ser-um-dogwalker?error=" + e.getMessage();
			
		}
		
		return "redirect:/quero-ser-um-dogwalker?createdFailed";
		
	}
	
	@Value("${jwt.secret}")
	private String SECRET_KEY_JWT;
	
	@GetMapping("/token")
	public String geraTokenJwt(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			String username = auth.getName();
						
			String token = tokenJwt(httpResponse, username, 0, true);
			
			if(token != null) {
				
				if (httpRequest.isUserInRole("CLIENTE")) {
				    return "redirect:/dashboard/cliente";
				}else if(httpRequest.isUserInRole("PROFISSIONAL")) {
					return "redirect:/dashboard/profissional";
				}
				
			}else {
				throw new Exception("token_nao_foi_gerado");
			}
			
		} catch (Exception e) {
			return "redirect:/logout?error=" + e.getMessage();
		}
		
		return "redirect:/logout";
		
	}
	
	public String tokenJwt(HttpServletResponse httpResponse, String username, int limiteExpiracaoEmSegundos, Boolean geraCookie) {
		
		String jwt = null;
		
		try {
			
			Calendar calendario = Calendar.getInstance();
			
			calendario.setTimeInMillis(System.currentTimeMillis());
			calendario.add(Calendar.SECOND, limiteExpiracaoEmSegundos);
			
			if(limiteExpiracaoEmSegundos == 0) {
				
				jwt = JWT.create()
						.withClaim("username", username)
						.sign(Algorithm.HMAC256(SECRET_KEY_JWT));
				
			}else {
				
				jwt = JWT.create()
						.withClaim("username", username)
						.withExpiresAt(calendario.getTime())
						.sign(Algorithm.HMAC256(SECRET_KEY_JWT));
				
			}
			
			if(geraCookie) {
				
				Cookie cookie = new Cookie("token", jwt);
				
				cookie.setHttpOnly(true);
				cookie.setPath("/");
				//cookie.setMaxAge(limiteExpiracaoEmSegundos);
				httpResponse.addCookie(cookie);
				
			}
			
		} catch (Exception e) {
			e.getMessage();
		}

		return jwt;
		
	}
	
}
