package app.Api.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {

    private int sectionId;
    private String sectionName;
    private List<StudentDTO> students;
    private List<ClazzDTO> classes;
    private List<ExamListDTO> exams;
}
