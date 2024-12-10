package app.Api.DynamicRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.DynamicEntity.ExamList;

public interface ExamListRepository extends JpaRepository<ExamList, Integer> {

}
