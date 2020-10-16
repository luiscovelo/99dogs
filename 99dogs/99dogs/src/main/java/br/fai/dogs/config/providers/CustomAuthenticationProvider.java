package br.fai.dogs.config.providers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.PessoaService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Value("${jwt.secret}")
	private String SECRET_KEY_JWT;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		
		String tokenTemporario = tokenJwt(username, true);
		
		Pessoa usuario = pessoaService.readByEmail(username, tokenTemporario);
		
		if(usuario == null) {
			throw new AuthenticationCredentialsNotFoundException("Usuário não encontrado.");
		}
		
		if(!passwordEncoder.matches(password, usuario.getSenha())) {
			throw new AuthenticationCredentialsNotFoundException("Email e/ou senha estão inválidos");
		}
		
		String token = tokenJwt(username, false);
		
		httpSession.setAttribute("usuario", usuario);
		httpSession.setAttribute("token", token);
		
		grantedAuthorityList.add( new SimpleGrantedAuthority("ROLE_" + usuario.getTipo()) );
				
		return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorityList);
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private String tokenJwt(String username, boolean temp) {
		
		Calendar calendario = Calendar.getInstance();
		
		calendario.setTimeInMillis(System.currentTimeMillis());
		calendario.add(Calendar.SECOND, 5);
		
		String jwt = "";
		
		if(temp) {
			
			jwt = JWT.create()
					.withClaim("username", username)
					.withExpiresAt(calendario.getTime())
					.sign(Algorithm.HMAC256(SECRET_KEY_JWT));
			
		}else {
			
			 jwt = JWT.create()
					.withClaim("username", username)
					.sign(Algorithm.HMAC256(SECRET_KEY_JWT));
			
		}
		
		return jwt;
		
	}
	
}
