
  package com.SCMXPert.sbmongodb.controller;
  
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.boot.web.servlet.FilterRegistrationBean;
  import org.springframework.context.annotation.Bean; 
  import org.springframework.context.annotation.Configuration; 
  import org.springframework.core.Ordered; 
  import org.springframework.web.cors.CorsConfiguration; 
  import org.springframework.web.cors.UrlBasedCorsConfigurationSource; 
  import org.springframework.web.filter.CorsFilter;
  
  @Configuration public class CorsConfig { 
	  //IMPORTANT: it has to be a normal configuration class, 
	  //not extending WebMvcConfigurerAdapter or other Spring Security class 

//		@Value("${corsIp}")
//		private String corsIp;
//
//		@Value("${corsPort}")
//		private String corsPort;
	  
	  @Bean public FilterRegistrationBean customCorsFilter() 
	  { 
		  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		  CorsConfiguration config = new CorsConfiguration(); 
		  //config.setAllowCredentials(true);
///         config.addAllowedOrigin("*");
///		 config.addAllowedOrigin("http://" + corsIp + ":" + corsPort);
		  //config.addAllowedOrigin("corsIp:corsPort");
		  config.addAllowedOrigin("http://127.0.0.1");
		  config.addAllowedOrigin("https://smaas.live");
		  config.addAllowedOrigin("https://smaas.live:8080");
		  config.addAllowedOrigin("http://localhost:8081");
		  config.addAllowedOrigin("https://smaas.live:8081");
		  config.addAllowedOrigin("https://www.smaas.org:8000");
		  config.addAllowedOrigin("https://www.smaas.org");
		  config.addAllowedOrigin("http://localhost:8080");
		  config.addAllowedOrigin("http://127.0.0.1:8080");
		  config.addAllowedOrigin("http://127.0.0.1:8081");
		  config.addAllowedOrigin("http://192.168.19.134:8080");
		  config.addAllowedOrigin("http://192.168.11.47:8080");
		  config.addAllowedOrigin("*");
		  config.addAllowedOrigin("http://localhost:8085");
		  config.addAllowedOrigin("http://192.168.11.48:8085");
		  config.addAllowedOrigin("http://192.168.11.47:8080");
		  config.addAllowedOrigin("http://52.15.99.31:8081");
		  config.addAllowedOrigin("http://52.15.99.31:8080");
		  config.addAllowedOrigin("https://18.218.150.152");
		  config.addAllowedOrigin("https://192.168.0.10:8080");
		  config.addAllowedOrigin("https://136.233.44.146:8081");
		  config.addAllowedOrigin("https://136.233.44.146:8080");
		  config.addAllowedOrigin("https://www.smaas-lb.de:8000");
		  config.addAllowedOrigin("http://206.189.129.91:3000");
		  config.addAllowedHeader("Authorization,content-type"); 
      //comment upto above for deployment
		  config.addAllowedHeader("*"); 
		  config.addAllowedMethod("*"); 
		  
		  source.registerCorsConfiguration("/**", config); 
		  FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source)); 
		  //IMPORTANT #2: I didn't stress enough the importance of this line in my original answer, 
		  //but it's here where we tell Spring to load this filter at the right point in the chain 
		  //(with an order of precedence higher than oauth2's filters) 
		  bean.setOrder(Ordered.HIGHEST_PRECEDENCE); 
		  return bean; 
		  } 
	  }
  
 