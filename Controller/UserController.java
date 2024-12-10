package app.Api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.Api.DTOs.ClazzDTO;
import app.Api.DTOs.RegistrationDTO;
import app.Api.DTOs.SectionDTO;
import app.Api.DTOs.StudentDTO;
import app.Api.DTOs.SubjectDTO;
import app.Api.DTOs.UserDTO;
import app.Api.Security.AuthenticationResponse;
import app.Api.Service.AuthenticationService;
import app.Api.Service.RegistrationService;
import app.Api.Service.SchoolService;
import app.Api.Service.TenantService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class UserController {
	@Autowired 
	private final TenantService tenant;
	@Autowired
	private final RegistrationService regService;
	@Autowired
	private final AuthenticationService authService;
	@Autowired
	private final SchoolService schService;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegistrationDTO dto){
		return ResponseEntity.ok(regService.register(dto));
		
	}
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody UserDTO dto) {
		
		return ResponseEntity.ok(authService.authenticate(dto));
	}
	@PostMapping("/addstd")
	public ResponseEntity<String> addstd(
			@RequestBody StudentDTO dto) {
		
		schService.addStudent(dto);
		
		return ResponseEntity.ok("success");
	}
	@PostMapping("/addsection")
	public ResponseEntity<String> addsection(
			@RequestBody SectionDTO dto) {
		
		schService.addSection(dto);
		
		return ResponseEntity.ok("success");
	}
	@PostMapping("/addClasses")
	public ResponseEntity<String> addClasses(
			@RequestBody ClazzDTO dto) {
		
		schService.addClasses(dto);
		
		return ResponseEntity.ok("success");
	}
	@PostMapping("/addSubject")
	public ResponseEntity<String> addSubject(
			@RequestBody SubjectDTO dto) {
		
		schService.addSubject(dto);
		
		return ResponseEntity.ok("success");
	}
	@GetMapping("/getSections")
	public ResponseEntity<List<SectionDTO>> getMethodName() {
		List<SectionDTO> sections = schService.getAllSections();
		return ResponseEntity.ok(sections);
	}
	@GetMapping("/getStudents")
	public ResponseEntity<List<StudentDTO>> getStudents() {
		List<StudentDTO> students = schService.getAllStudents();
		return ResponseEntity.ok(students);
	}
	
	
	
	
	
	

}
