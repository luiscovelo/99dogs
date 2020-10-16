package br.fai.dogs.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
	
	@Value("${jwt.secret}")
	private String SECRET_KEY_JWT;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	HttpSession session;
	
	@PostMapping("/criar-conta-cliente")
	public String criarContaCliente(HttpServletResponse httpResponse, Pessoa pessoa) {
		
		try {
			
			String tokenTemporario = tokenJwt(httpResponse,pessoa.getEmail(), 30);

			if(tokenTemporario != null) {
				
				Pessoa pessoaExistente = pessoaService.readByEmail(pessoa.getEmail(), tokenTemporario);
				
				if(pessoaExistente != null && pessoaExistente.getId() > 0) {
					throw new Exception("email_in_use");
				}
				
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
			
			String tokenTemporario = tokenJwt(httpResponse,pessoa.getEmail(), 30);

			if(tokenTemporario != null) {
				
				Pessoa pessoaExistente = pessoaService.readByEmail(pessoa.getEmail(), tokenTemporario);
				
				if(pessoaExistente != null && pessoaExistente.getId() > 0) {
					throw new Exception("email_in_use");
				}
				
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
	
	@GetMapping("/redirect-after-login")
	public String redirectAfterLogin() {
		
		try {
						
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			String role = null;
			for(GrantedAuthority authority :auth.getAuthorities()) {
				role = authority.toString();
			}
			
			if(role.equals("ROLE_CLIENTE")) {
				return "redirect:/dashboard/cliente";
			}else if(role.equals("ROLE_PROFISSIONAL")) {
				return "redirect:/dashboard/profissional";
			}
			
		} catch (Exception e) {
			return "redirect:/logout?error=" + e.getMessage();
		}
		
		return "redirect:/logout";
		
	}
	
	public String tokenJwt(HttpServletResponse httpResponse, String username, int limiteExpiracaoEmSegundos) {
		
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
						
		} catch (Exception e) {
			e.getMessage();
		}

		return jwt;
		
	}
	
}
