package pro.sky.tgbotcatshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.model.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
