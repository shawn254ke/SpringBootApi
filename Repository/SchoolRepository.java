package app.Api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.Entity.School;

public interface SchoolRepository extends JpaRepository<School, Integer> {

}
