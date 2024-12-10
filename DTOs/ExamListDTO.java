package app.Api.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamListDTO {

    private int examId;
    private String examName;
    private String sectionName; // To simplify the Section representation
    private LocalDate date;
    private String status;
}
