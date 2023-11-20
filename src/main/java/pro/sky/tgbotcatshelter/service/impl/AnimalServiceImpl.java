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
 * Сервис, предоставляющий бизнес-логику для работы с данными о животных.
 */
@Service
public class AnimalServiceImpl implements AnimalService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);
    private final AnimalRepository animalRepository;

    // Конструктор, внедряющий зависимость от AnimalRepository
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    /**
     * Получает животное по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор животного.
     * @return Найденное животное.
     * @throws AnimalNotFoundException Если животное не найдено.
     */
    @Override
    public Animal getById(Long id) {
        logger.info("invoked method getById");
        return animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
    }

    /**
     * Возвращает коллекцию всех животных.
     *
     * @return Коллекция всех животных.
     */
    @Override
    public Collection<Animal> getAll() {
        logger.info("invoked method getAll");
        return animalRepository.findAll();
    }

    /**
     * Создает новое животное.
     *
     * @param animal Данные нового животного.
     * @return Созданное животное.
     */
    @Override
    public Animal create(Animal animal) {
        logger.info("invoked method create");
        return animalRepository.save(animal);
    }

    /**
     * Обновляет данные о существующем животном.
     *
     * @param id     Уникальный идентификатор животного.
     * @param animal Обновленные данные о животном.
     * @return Обновленное животное.
     * @throws AnimalNotFoundException Если животное не найдено.
     */
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

    /**
     * Удаляет животное по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор животного.
     * @return Удаленное животное.
     * @throws AnimalNotFoundException Если животное не найдено.
     */
    @Override
    public Animal remove(long id) {
        logger.info("invoked method remove");
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
        animalRepository.delete(existingAnimal);
        return existingAnimal;
    }
}
