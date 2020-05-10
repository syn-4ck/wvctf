package com.julianfm.wvctf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@SpringBootApplication
public class WvctfApplication extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST,"/user").permitAll()
        .antMatchers(HttpMethod.POST,"/products").permitAll()
        .antMatchers(HttpMethod.POST,"/category").permitAll()
        .antMatchers(HttpMethod.POST,"/commentary").permitAll()
        .antMatchers(HttpMethod.POST,"/upload").permitAll()
        .antMatchers(HttpMethod.DELETE, "/user/*").permitAll()
        .antMatchers(HttpMethod.DELETE,"/products/*").permitAll()
        .antMatchers(HttpMethod.DELETE,"/category/*").permitAll()
        .antMatchers(HttpMethod.DELETE,"/commentary/*").permitAll()
        .antMatchers(HttpMethod.GET,"/user/*").permitAll()
        .antMatchers(HttpMethod.GET, "/user").permitAll()
        .antMatchers(HttpMethod.GET,"/products/*").permitAll()
        .antMatchers(HttpMethod.GET,"/products/search/*").permitAll()
        .antMatchers(HttpMethod.GET,"/products/search").permitAll()
        .antMatchers(HttpMethod.GET, "/products").permitAll()
        .antMatchers(HttpMethod.GET,"/category/*").permitAll()
        .antMatchers(HttpMethod.GET, "/category").permitAll()
        .antMatchers(HttpMethod.GET,"/commentary/*").permitAll()
        .antMatchers(HttpMethod.GET,"/image/*").permitAll()
        .anyRequest().authenticated();
    }
	
	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);
	    firewall.setAllowSemicolon(true);
	    return firewall;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WvctfApplication.class, args);
	}

}
