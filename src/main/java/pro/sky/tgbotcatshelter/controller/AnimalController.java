package pro.sky.tgbotcatshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.service.AnimalService;

import java.util.Collection;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Operation(summary = "Get all animals", description = "Retrieve a collection of all animals.")
    @GetMapping
    public Collection<Animal> getAll() {
        return animalService.getAll();
    }

    @Operation(summary = "Get animal by ID", description = "Retrieve information about an animal by its ID.")
    @GetMapping("/getById/{id}")
    public Animal getById(@PathVariable("id") Long id) {
        return animalService.getById(id);
    }

    @Operation(summary = "Update animal information", description = "Update information about an animal by its ID.")
    @PutMapping("/{id}")
    public Animal update(@PathVariable("id") Long id, @RequestBody Animal animal) {
        return animalService.update(id, animal);
    }

    @Operation(summary = "Create an animal", description = "Create a new record for an animal.")
    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return animalService.create(animal);
    }

    @Operation(summary = "Remove an animal", description = "Delete information about an animal by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal removed"),
            @ApiResponse(responseCode = "404", description = "Animal not found")
    })
    @DeleteMapping("/{id}")
    public Animal remove(@PathVariable("id") Long id) {
        return animalService.remove(id);
    }
}
