package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
		http
		    .authorizeHttpRequests(auth -> auth
		    	.requestMatchers("/admin/signin","/admin/signup").permitAll()
		    	.anyRequest().authenticated() //それ以外は認証が必要
		    )
		    .formLogin(formLogin -> formLogin
		    	        .loginPage("/admin/signin")
		    		    .defaultSuccessUrl("/admin/contacts",true)
		    		    .permitAll()
		    		    .usernameParameter("email")
		    )
		    .logout(logout -> logout
		                .logoutUrl("/admin/signout")
		                .logoutSuccessUrl("/admin/signin")
		    		    .permitAll()
		    	);
		
	    return http.build();
	}
	
	//下のコードは パスワードを安全に暗号化（ハッシュ化）するため
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	//ユーザーが入力したパスワードと、データベースに保存してあるハッシュ化されたパスワードを比較するため
}
