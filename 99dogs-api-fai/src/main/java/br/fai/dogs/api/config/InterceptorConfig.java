package br.fai.dogs.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.fai.dogs.api.interceptor.RequestInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport  {

	@Autowired
	RequestInterceptor requestInterceptor;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	            .addResourceLocations("classpath:/META-INF/resources/");
	    
	    registry.addResourceHandler("/webjars/**")
        		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		/*
		registry.addInterceptor(requestInterceptor).addPathPatterns("/**")
	    .excludePathPatterns("/v2/api-docs", "/configuration/ui", 
	            "/swagger-resources/**", "/configuration/**", "/swagger-ui.html"
	            , "/webjars/**", "/csrf", "/");
	    */		
	}
}
