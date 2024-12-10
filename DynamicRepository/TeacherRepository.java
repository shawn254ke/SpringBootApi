package app.Api.DynamicRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.DynamicEntity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
