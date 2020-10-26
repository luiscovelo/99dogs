package br.fai.dogs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.fai.dogs.config.providers.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/home").permitAll()
				.antMatchers("/quero-encontrar-dogwalkers").permitAll()
				.antMatchers("/quero-ser-um-dogwalker").permitAll()
				.antMatchers("/**/cliente/**").hasRole("CLIENTE")
				.antMatchers("/**/profissional/**").hasRole("PROFISSIONAL")
				.antMatchers("/token").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/criar-conta-cliente").permitAll()
				.antMatchers("/criar-conta-profissional").permitAll()
				.antMatchers("/configuracao-da-agenda/cliente/horarios-disponveis-por-data/**").permitAll()
				.antMatchers("/profissionais").permitAll()
				.antMatchers("/passeio/cliente/verificar-disponibilidade/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/redirect-after-login")
			.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID");
		
		http.exceptionHandling().accessDeniedPage("/403");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				
		auth.authenticationProvider(customAuthenticationProvider);
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
		
}
