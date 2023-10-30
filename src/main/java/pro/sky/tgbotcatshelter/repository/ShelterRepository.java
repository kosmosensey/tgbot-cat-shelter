package pro.sky.tgbotcatshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.tgbotcatshelter.entity.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
