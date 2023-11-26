package pro.sky.tgbotcatshelter.service;

import pro.sky.tgbotcatshelter.constants.PetType;
import pro.sky.tgbotcatshelter.entity.Animal;

import java.util.Collection;
import java.util.List;

/**
 * Сервис по работе с животными.
 */
public interface AnimalService {

    public Animal getById(Long id);

    Collection<Animal> getAll();

    Animal create(Animal animal);

    Animal update(Long id, Animal animal);

    Animal remove(long id);

    List<Animal> getAllAnimalsByType(PetType petType);
}
