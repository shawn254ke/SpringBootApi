package app.Api.DynamicEntity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultsId {
	@Column(name = "ExamId")
    private Integer examId;
    
    @Column(name = "StudentId")
    private Integer studentId;
    
    @Column(name = "SubjectId")
    private Integer subjectId;

}
