package app.Api.DynamicRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.DynamicEntity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
