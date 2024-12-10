package app.Api.DynamicRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.DynamicEntity.TchAttendance;

public interface TchAttendanceRepository extends JpaRepository<TchAttendance, Integer>{

}
