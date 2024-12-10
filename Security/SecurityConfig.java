package app.Api.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/auth/register**","/school/register","/school/authenticate**", "/login.html", "/resources/**", "/scripts.js/**", "/signup.html",
                        		"/signup.css","/scripts2.js/**","/myschool.html","/charts.js/**","/login.css/**", "/hat.png/**","/css/**", "/js/**", "/images/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        .loginPage("/login.html") // Custom login page
                        .permitAll())
                
   
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}

}
