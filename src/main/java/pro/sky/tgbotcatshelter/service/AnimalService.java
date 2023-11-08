package pro.sky.tgbotcatshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.entity.Animal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Сервис по работе с животными.
 */
public interface AnimalService {

    public Animal getById(Long id);

    Collection<Animal> getAll();

    Animal create(Animal animal);

    Animal update(Long id, Animal animal);

    Animal remove(long id);
}
