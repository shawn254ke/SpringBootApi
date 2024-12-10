package app.Api.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Long teacherId;
    private String firstName;
    private String middleName;
    private String surname;
    private String subjectName; // Simplified Subject
    private String sectionName; // Simplified Section
}
