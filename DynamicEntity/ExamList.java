package app.Api.DynamicEntity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ExamList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExamID")
    private int examId;

    @ManyToOne
    @JoinColumn(name = "SectionID")
    private Section section;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

    @Column(name = "examname")
    private String examName;
}
