package pro.sky.tgbotcatshelter.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.service.AnimalService;
import java.util.Collection;


/**
 * Контроллер для управления животными.
 * Предоставляет методы для получения, обновления, создания и удаления информации о животных.
 */
@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    /**
     * Конструктор класса AnimalController.
     * @param animalService сервис для работы с животными
     */
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Получение списка всех животных.
     * @return список всех животных
     */
    @GetMapping
    public Collection<Animal> getAll() {
        return animalService.getAll();
    }

    /**
     * Получение информации о животном по его идентификатору.
     * @param id идентификатор животного
     * @return информация о животном с указанным идентификатором
     */
    @GetMapping("/getById/{id}")
    public Animal getById(@PathVariable("id") Long id) {
        return animalService.getById(id);
    }

    /**
     * Обновление информации о животном по его идентификатору.
     * @param id идентификатор животного
     * @param animal новая информация о животном
     * @return обновленная информация о животном
     */
    @PutMapping("/{id}")
    public Animal update(@PathVariable("id") Long id, @RequestBody Animal animal) {
        return animalService.update(id, animal);
    }

    /**
     * Создание новой записи о животном.
     * @param animal информация о новом животном
     * @return информация о созданном животном
     */
    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return animalService.create(animal);
    }

    /**
     * Удаление информации о животном по его идентификатору.
     * @param id идентификатор животного для удаления
     * @return удаленная информация о животном
     */
    @DeleteMapping("/{id}")
    public Animal remove(@PathVariable("id") Long id) {
        return animalService.remove(id);
    }
}

