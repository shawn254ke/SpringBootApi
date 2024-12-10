package app.Api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTO {
	private String schoolName;
	private String schoolAddress;
	private String schoolEmail;
	private String schoolMotto;
	private String academicYear;

}
