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
@Table(name = "ClassTable")
public class ClazzEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassId")
    private int classId;

    @Column(name = "ClassIdentifier")
    private String classIdentifier;

    @Column(name = "GradeLevel")
    private String gradeLevel;

    @ManyToOne
    @JoinColumn(name = "SectionId")
    private Section section;

    @OneToMany(mappedBy = "classEntity")
    private List<Student> students;
}


