package pro.sky.tgbotcatshelter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.exception.AnimalNotFoundException;
import pro.sky.tgbotcatshelter.repository.AnimalRepository;
import pro.sky.tgbotcatshelter.service.AnimalService;

import java.util.Collection;
import java.util.Optional;

/**
 * Бизнес-логика по работе с животными.
 */
@Service
public class AnimalServiceImpl implements AnimalService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {

        this.animalRepository = animalRepository;
    }

    @Override
    public Animal getById(Long id) {
        logger.info("invoked method getById");
        return animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
    }

    @Override
    public Collection<Animal> getAll() {
        logger.info("invoked method getAll");
        return animalRepository.findAll();
    }

    @Override
    public Animal create(Animal animal) {
        logger.info("invoked method create");
        return animalRepository.save(animal);
    }

    @Override
    public Animal update(Long id, Animal animal) {
        logger.info("invoked method update");
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
        Optional.ofNullable(animal.getName()).ifPresent(existingAnimal::setName);
        Optional.ofNullable(animal.getPetType()).ifPresent(existingAnimal::setPetType);
        Optional.ofNullable(animal.getColor()).ifPresent(existingAnimal::setColor);
        Optional.ofNullable(animal.getSex()).ifPresent(existingAnimal::setSex);
        return animalRepository.save(existingAnimal);
    }

    @Override
    public Animal remove(long id) {
        logger.info("invoked method remove");
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
        animalRepository.delete(existingAnimal);
        return existingAnimal;
    }
}

