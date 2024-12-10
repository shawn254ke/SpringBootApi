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
public class TchAttendanceDTO {

    private Long attendanceId;
    private Long teacherId;       // Reference to Teacher
    private String teacherName;   // Concatenated first, middle, and surname
    private LocalDate date;
    private String status;
}
