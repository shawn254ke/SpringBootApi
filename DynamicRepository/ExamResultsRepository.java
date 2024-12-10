package app.Api.DynamicRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.DynamicEntity.ExamResults;
import app.Api.DynamicEntity.ExamResultsId;

public interface ExamResultsRepository extends JpaRepository<ExamResults, ExamResultsId> {

}
