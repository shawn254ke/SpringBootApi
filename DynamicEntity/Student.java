package app.Api.DynamicEntity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "ClassId")
    private ClazzEntity classEntity;

    @ManyToOne
    @JoinColumn(name = "SectionId")
    private Section section;

    @OneToMany(mappedBy = "student")
    private List<StdAttendance> attendanceRecords;
}
