package app.Api.DynamicEntity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ExamResults")
public class ExamResults {
	 @EmbeddedId
	    private ExamResultsId id;

	  
	    
	    @ManyToOne
	    @JoinColumn(name = "ExamId", insertable = false, updatable = false)
	    private ExamList exam;

	    @ManyToOne
	    @JoinColumn(name = "StudentId", insertable = false, updatable = false)
	    private Student student;

	    @ManyToOne
	    @JoinColumn(name = "SubjectId", insertable = false, updatable = false)
	    private Subject subject;

	    @Column(name = "Score")
	    private Integer score;
}
