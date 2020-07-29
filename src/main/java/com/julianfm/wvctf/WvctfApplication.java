package com.julianfm.wvctf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.julianfm.wvctf.api.service.mng.MngUserService;

@Configuration
@SpringBootApplication
@EnableWebSecurity
public class WvctfApplication extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MngUserService mngUserService;
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
			  .antMatchers("/credentials").permitAll()
		  .antMatchers("/").authenticated()
		  .antMatchers(HttpMethod.POST,"/user").authenticated()
		  .antMatchers(HttpMethod.POST,"/mgruser").permitAll()
		  .antMatchers(HttpMethod.POST,"/user").authenticated()
		  .antMatchers(HttpMethod.POST,"/products").authenticated()
		  .antMatchers(HttpMethod.POST,"/category").authenticated()
		  .antMatchers(HttpMethod.POST,"/commentary").authenticated()
		  .antMatchers(HttpMethod.POST,"/upload").authenticated()
		  .antMatchers(HttpMethod.DELETE, "/user/*").authenticated()
		  .antMatchers(HttpMethod.DELETE,"/products/*").authenticated()
		  .antMatchers(HttpMethod.DELETE,"/category/*").authenticated()
		  .antMatchers(HttpMethod.DELETE,"/commentary/*").authenticated()
		  .antMatchers(HttpMethod.DELETE,"/orders/*").authenticated()
		  .antMatchers(HttpMethod.GET, "/reset").authenticated()
		  .antMatchers(HttpMethod.GET, "/flags/*").authenticated()
		  .antMatchers(HttpMethod.GET,"/user/*").authenticated()
		  .antMatchers(HttpMethod.GET, "/user").authenticated()
		  .antMatchers(HttpMethod.GET,"/products/*").authenticated()
		  .antMatchers(HttpMethod.GET,"/products/search/*").authenticated()
		  .antMatchers(HttpMethod.GET,"/products/search").authenticated()
		  .antMatchers(HttpMethod.GET, "/products").authenticated()
		  .antMatchers(HttpMethod.GET,"/contact/*").authenticated()
		  .antMatchers(HttpMethod.GET,"/category/*").authenticated()
		  .antMatchers(HttpMethod.GET, "/category").authenticated()
		  .antMatchers(HttpMethod.GET,"/commentary/*").authenticated()
		  .antMatchers(HttpMethod.GET,"/orders").authenticated()
		  .antMatchers(HttpMethod.GET,"/orders/list").authenticated()
		  .antMatchers(HttpMethod.GET,"/mgruser/*").authenticated()
		  .antMatchers(HttpMethod.GET,"/products/vendor/*").authenticated()
		  .antMatchers(HttpMethod.GET,"/sales").authenticated()
		  .antMatchers(HttpMethod.GET,"/orders/check").authenticated()
		  .antMatchers(HttpMethod.GET,"/image/*").authenticated().and().httpBasic();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);
	    firewall.setAllowSemicolon(true);
	    return firewall;
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
		daoAuthProvider.setPasswordEncoder(passwordEncoder());
		daoAuthProvider.setUserDetailsService(this.mngUserService);
		
		return daoAuthProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WvctfApplication.class, args);
	}

}
