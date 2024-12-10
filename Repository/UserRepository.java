package app.Api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String email);

}
