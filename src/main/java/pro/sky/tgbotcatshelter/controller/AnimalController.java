package pro.sky.tgbotcatshelter.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.service.AnimalService;
import pro.sky.tgbotcatshelter.service.impl.AnimalServiceImpl;
import org.springframework.web.bind.annotation.*;import java.util.Collection;


/**
 * Контроллер животных
 */
@RestController
@RequestMapping( "/animals")

public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public Collection<Animal> getAll() {
        return animalService.getAll();
    }

    @GetMapping(params = "/{id}")
    public Animal getById(@PathVariable("id") Long id) {
        return animalService.getById(id);
    }

    @PutMapping(params = "/{id}")
    public Animal update(@PathVariable("id") Long id, @RequestBody Animal animal) {
        return animalService.update(id, animal);
    }

    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return animalService.create(animal);
    }

    @DeleteMapping("/{id}")
    public Animal remove(@PathVariable("id") Long id) {
        return animalService.remove(id);
    }
}

