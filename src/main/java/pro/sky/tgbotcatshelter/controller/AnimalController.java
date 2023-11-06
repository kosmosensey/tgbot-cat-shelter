package pro.sky.tgbotcatshelter.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.service.AnimalService;
import pro.sky.tgbotcatshelter.service.impl.AnimalServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 * Контроллер животных
 */
@RestController
@RequestMapping ("/animal")
public class AnimalController {
    private final AnimalServiceImpl animalServiceimpl;

    public AnimalController(AnimalServiceImpl animalServiceimpl) {
        this.animalServiceimpl = animalServiceimpl;
    }
    @GetMapping
    public Collection<Animal> getAll(){
        return animalServiceimpl.getAll();
    }
    @GetMapping("/{id}")
    public Animal getById (@PathVariable("id") Long id){
        return animalServiceimpl.getById(id);
    }

    @PutMapping("/{id}")
    public Animal update (@PathVariable("id") Long id,@RequestBody Animal animal){
        return animalServiceimpl.update(id, animal);
    }
    @PostMapping
    public  Animal create(@RequestBody Animal animal){
        return animalServiceimpl.create(animal);
    }

    @DeleteMapping("/{id}")
    public Animal remove(@PathVariable("id") Long id){
        return animalServiceimpl.remove(id);}
    }

