package app.Api.DynamicEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherId")
    private Long teacherId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "SectionId")
    private Section section;

    @OneToMany(mappedBy = "teacher")
    private List<TchAttendance> attendanceRecords;
}
