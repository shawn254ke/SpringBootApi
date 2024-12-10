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
public class StdAttendanceDTO {

    private Long attendanceId;
    private Integer studentId;
    private String studentName; // Concatenated first and last name
    private LocalDate date;
    private String status;
}
