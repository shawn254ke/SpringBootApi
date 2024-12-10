package app.Api.DynamicEntity;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "TchAttendanceTable")
public class TchAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttendanceID")
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "TeacherID")
    private Teacher teacher;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "Status")
    private String status;
}

