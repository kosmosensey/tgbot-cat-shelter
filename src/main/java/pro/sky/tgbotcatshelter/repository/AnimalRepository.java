package pro.sky.tgbotcatshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.constants.PetType;
import pro.sky.tgbotcatshelter.entity.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByPetType(PetType petType);

}
