package br.com.brasilprev.desafio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
			"/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**", "/h2/**"
    };
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
        auth.inMemoryAuthentication()
            .withUser("admin").password(encoder.encode("admin")).roles("ADMIN", "USER")
            .and()
            .withUser("user").password(encoder.encode("123456")).roles("USER");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
        http.authorizeRequests()
	        	.antMatchers(AUTH_WHITELIST).permitAll()
	            .anyRequest().authenticated()
	            .and()
            .httpBasic().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .csrf().disable();
    }
}
