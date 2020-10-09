package br.fai.dogs.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
				.anyRequest().authenticated()
			.and()
				.formLogin().defaultSuccessUrl("/token").loginPage("/login")
			.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID");
		
		http.exceptionHandling().accessDeniedPage("/403");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery("select email, senha, 1 as ativo from pessoa where email = ?")
			.authoritiesByUsernameQuery("select email, CONCAT('ROLE_',tipo) as tipo from pessoa where email = ?");
				
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
		
}
