package app.Api.DynamicEntity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "StdAttendanceTable")
public class StdAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttendanceID")
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "Status")
    private String status;
}
