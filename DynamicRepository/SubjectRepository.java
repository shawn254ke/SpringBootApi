package app.Api.DynamicRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.DynamicEntity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{

}
