package app.Api.DynamicRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.Api.DynamicEntity.ClazzEntity;
@Repository
public interface ClazzRepository extends JpaRepository<ClazzEntity, Integer>{
	Optional <ClazzEntity> findByClassIdentifier(String ClassId);

}
