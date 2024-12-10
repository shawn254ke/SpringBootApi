package app.Api.DTOs;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    private int subjectId;
    private String subjectName;
    private String sectionName;
    private List<TeacherDTO> teachers;
}
