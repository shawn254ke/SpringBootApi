package app.Api.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultsDTO {

    private Integer examId;
    private Integer studentId;
    private Integer subjectId;
    private String examName;
    private String studentName; // FirstName + LastName
    private String subjectName;
    private Integer score;
}
