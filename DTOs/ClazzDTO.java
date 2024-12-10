package app.Api.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClazzDTO {
	 private String classIdentifier;
	 private String gradeLevel;
	 private String section;
	 private List<StudentDTO> students; 

}
