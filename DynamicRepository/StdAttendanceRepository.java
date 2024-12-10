package app.Api.DynamicRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.DynamicEntity.StdAttendance;

public interface StdAttendanceRepository extends JpaRepository<StdAttendance, Integer> {

}
