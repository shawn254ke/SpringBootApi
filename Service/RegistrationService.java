package app.Api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.Api.DTOs.RegistrationDTO;
import app.Api.Entity.Role;
import app.Api.Entity.School;
import app.Api.Entity.User;
import app.Api.Repository.RoleRepository;
import app.Api.Repository.SchoolRepository;
import app.Api.Repository.UserRepository;
import app.Api.Security.AuthenticationResponse;
import app.Api.Security.jwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {
	
	@Autowired
	private final UserRepository userRepo;
	@Autowired
	private final SchoolRepository schRepo;
	private final PasswordEncoder pass;
	private final jwtService jwt;
	private final RoleRepository roleRepo;
	private final SchemaService schemaService;
	@Transactional(transactionManager = "defaultTransactionManager")
	public AuthenticationResponse register(RegistrationDTO request) {
		var sch = School.builder()
				.schoolName(request.getSchDto().getSchoolName())
				.address(request.getSchDto().getSchoolAddress())
				.email(request.getSchDto().getSchoolEmail())
				.motto(request.getSchDto().getSchoolMotto())
				.billingStatus(false)
				.build();
		schRepo.save(sch);
		
		var role = roleRepo.findById(112)
				.orElseThrow();
		var user = User.builder()
				.username(request.getUserDto().getUsername())
				.passwordHash(pass.encode(request.getUserDto().getPassword()))
				.role(role)
				.school(sch)
				.build();
				userRepo.save(user);
				schemaService.createSchemaForUser(sch.getSchemaName());		
				
		var jwtToken = jwt.generateToken(user);	
				return AuthenticationResponse.builder()
						.token(jwtToken)
						.build();
	}
	
	
	
	

}
