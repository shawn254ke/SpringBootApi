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
@Table(name = "SectionTable")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SectionId")
    private int sectionId;

    @Column(name = "SectionName")
    private String sectionName;

    @OneToMany(mappedBy = "section")
    private List<Student> students;

    @OneToMany(mappedBy = "section")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "section")
    private List<ClazzEntity> classes;

    @OneToMany(mappedBy = "section")
    private List<ExamList> exams;
}
