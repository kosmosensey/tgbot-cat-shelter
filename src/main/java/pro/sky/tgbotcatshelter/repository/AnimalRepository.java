package pro.sky.tgbotcatshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
