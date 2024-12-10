package app.Api.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private int studentId;
    private String firstName;
    private String middleName;
    private String surname;
    private String dateOfBirth;
    private String classIdentifier; // Simplified ClassEntity
    private String sectionName; // Simplified Section
    private List<StdAttendanceDTO> attendanceRecords;
}
