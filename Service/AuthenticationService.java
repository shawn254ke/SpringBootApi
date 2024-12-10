package app.Api.Service;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.Api.DTOs.UserDTO;
import app.Api.Repository.UserRepository;
import app.Api.Security.AuthenticationResponse;
import app.Api.Security.jwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepo;
	private final jwtService jwt;
	private final AuthenticationManager authmanager;
	


	
	
	public AuthenticationResponse authenticate(UserDTO request) {
		authmanager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()
						)
				);
		var user = userRepo.findByUsername(request.getUsername())
				.orElseThrow();
		var jwtToken = jwt.generateToken(user);	
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

}

