package app.Api.DynamicRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.Api.DynamicEntity.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {
	Optional<Section> findBySectionName(String section);

}
