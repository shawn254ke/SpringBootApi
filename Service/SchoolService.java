package app.Api.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.Api.DTOs.ClazzDTO;
import app.Api.DTOs.ExamListDTO;
import app.Api.DTOs.SectionDTO;
import app.Api.DTOs.StdAttendanceDTO;
import app.Api.DTOs.StudentDTO;
import app.Api.DTOs.SubjectDTO;
import app.Api.Datasource.TenantContext;
import app.Api.DynamicEntity.ClazzEntity;
import app.Api.DynamicEntity.Section;
import app.Api.DynamicEntity.Student;
import app.Api.DynamicEntity.Subject;
import app.Api.DynamicRepository.ClazzRepository;
import app.Api.DynamicRepository.SectionRepository;
import app.Api.DynamicRepository.StudentRepository;
import app.Api.DynamicRepository.SubjectRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {
	@Autowired
	private final StudentRepository stdRepo;
	@Autowired
	private final SectionRepository sectionRepo;
	@Autowired
	private final ClazzRepository clzRepo;
	@Autowired
	private final SubjectRepository subRepo;
	@Transactional(transactionManager = "dynamicTransactionManager")
	public void addStudent(StudentDTO stdDto) {
		Student std = Student.builder()
				.firstName(stdDto.getFirstName())
				.middleName(stdDto.getMiddleName())
				.surname(stdDto.getSurname())
				.dateOfBirth(LocalDate.now())
				.section(sectionRepo.findBySectionName(stdDto.getSectionName()).orElseThrow())
				.classEntity(clzRepo.findByClassIdentifier(stdDto.getClassIdentifier()).orElseThrow())
				.build();
		stdRepo.save(std);
		TenantContext.clear();
	}
	@Transactional(transactionManager = "dynamicTransactionManager")
	public void addSection(SectionDTO sectionDto) {
		Section sec = Section.builder()
				.sectionName(sectionDto.getSectionName())
				.build();
		sectionRepo.save(sec);
		TenantContext.clear();
	}
	@Transactional(transactionManager = "dynamicTransactionManager")
	public void addSubject(SubjectDTO dto) {
		Subject sub = Subject.builder()
				.subjectName(dto.getSubjectName())
				.section(sectionRepo.findBySectionName(dto.getSectionName()).orElseThrow())
				.build();
		subRepo.save(sub);
		TenantContext.clear();
	}
	@Transactional(transactionManager = "dynamicTransactionManager")
	public void addClasses(ClazzDTO dto) {
		ClazzEntity clz = ClazzEntity.builder()
				.classIdentifier(dto.getClassIdentifier())
				.section(sectionRepo.findBySectionName(dto.getSection()).orElseThrow())
				.gradeLevel("k")
				.build();
		clzRepo.save(clz);
		TenantContext.clear();
	}
	@Transactional(transactionManager = "dynamicTransactionManager")
	public List<SectionDTO> getAllSections() {
		List<SectionDTO> sections =sectionRepo.findAll().stream()
				.map(this::mapToDTO).collect(Collectors.toList());
		TenantContext.clear();
		return sections;
	}
	@Transactional(transactionManager = "dynamicTransactionManager")
	public List<StudentDTO> getAllStudents(){
		List<StudentDTO> students = stdRepo.findAll().stream().map(std->StudentDTO.builder()
				.classIdentifier(std.getClassEntity().getClassIdentifier())
				.firstName(std.getFirstName())
				.studentId(std.getStudentId())
				.attendanceRecords((List<StdAttendanceDTO>) std.getAttendanceRecords().stream()
						.map(att->StdAttendanceDTO.builder()
								.status(att.getStatus())
								.build()).collect(Collectors.toList()))
				.build()).collect(Collectors.toList());
		TenantContext.clear();
		return students;
	}
	 /**
     * Maps a Section entity to a SectionDTO.
     *
     * @param section the Section entity
     * @return SectionDTO
     */
    private SectionDTO mapToDTO(Section section) {
        return SectionDTO.builder()
                .sectionId(section.getSectionId())
                .sectionName(section.getSectionName())
                .students(section.getStudents() != null ? section.getStudents().stream()
                        .map(student -> StudentDTO.builder()
                                .studentId(student.getStudentId())
                                .firstName(student.getFirstName())
                                .build())
                        .collect(Collectors.toList()) : null)
                .classes(section.getClasses() != null ? section.getClasses().stream()
                        .map(clazz -> ClazzDTO.builder()
                                .classIdentifier(clazz.getClassIdentifier())
                                .section(clazz.getSection().getSectionName())
                                .build())
                        .collect(Collectors.toList()) : null)
                .exams(section.getExams() != null ? section.getExams().stream()
                        .map(exam -> ExamListDTO.builder()
                                .examId(exam.getExamId())
                                .examName(exam.getExamName())
                                .build())
                        .collect(Collectors.toList()) : null)
                .build();
    }
	

}
