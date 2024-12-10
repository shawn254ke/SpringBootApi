package app.Api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
