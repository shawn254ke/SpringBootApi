package app.Api.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import app.Api.Datasource.DataSourceManager;
import app.Api.Datasource.TenantContext;
import app.Api.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final jwtService jwtService;
	private final UserDetailsService userDetailsServices;
	private final UserRepository userRepo;
	@Autowired
    private final DataSourceManager dataSourceManager;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userName;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);
		System.out.println("Token received: " + jwt);
		userName = jwtService.extractName(jwt);	
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() ==null) {
			UserDetails userDetails = this.userDetailsServices.loadUserByUsername(userName);
			if(jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				token.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
				
				var user = userRepo.findByUsername(userName).orElseThrow();
				String s = user.getSchool().getSchemaName();
				dataSourceManager.addTenant(s);
				TenantContext.setCurrentTenant(s);
				
				
			}
		}
		filterChain.doFilter(request, response);
		
	}

}

