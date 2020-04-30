package com.julianfm.wvctf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@SpringBootApplication
public class WvctfApplication extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST,"/user").permitAll()
        .antMatchers(HttpMethod.POST,"/product").permitAll()
        .antMatchers(HttpMethod.POST,"/category").permitAll()
        .antMatchers(HttpMethod.DELETE, "/user/*").permitAll()
        .antMatchers(HttpMethod.DELETE,"/product/*").permitAll()
        .antMatchers(HttpMethod.DELETE,"/category/*").permitAll()
        .antMatchers(HttpMethod.GET,"/user/*").permitAll()
        .antMatchers(HttpMethod.GET, "/user").permitAll()
        .antMatchers(HttpMethod.GET,"/product/*").permitAll()
        .antMatchers(HttpMethod.GET, "/product").permitAll()
        .antMatchers(HttpMethod.GET,"/category/*").permitAll()
        .antMatchers(HttpMethod.GET, "/category").permitAll()
        .anyRequest().authenticated();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(WvctfApplication.class, args);
	}

}
