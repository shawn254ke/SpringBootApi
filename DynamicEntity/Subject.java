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
@Table(name = "SubjectTable")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubjectId")
    private int subjectId;

    @Column(name = "subjectName")
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "SectionID")
    private Section section;

    @OneToMany(mappedBy = "subject")
    private List<Teacher> teachers;  // Assuming a subject can have multiple teachers
}
