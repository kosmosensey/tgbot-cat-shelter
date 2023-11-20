package pro.sky.tgbotcatshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import pro.sky.tgbotcatshelter.entity.Shelter;
import pro.sky.tgbotcatshelter.service.ShelterService;

import java.util.Collection;

@RestController
@RequestMapping("/shelters")
public class ShelterController {
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @Operation(summary = "Get all shelters", description = "Retrieve a collection of all shelters.")
    @GetMapping
    public Collection<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @Operation(summary = "Get shelter by ID", description = "Retrieve information about a shelter by its ID.")
    @GetMapping("/{id}")
    public Shelter getShelterById(@PathVariable("id") Long id) {
        return shelterService.getShelterById(id);
    }

    @Operation(summary = "Update shelter information", description = "Update information about a shelter by its ID.")
    @PutMapping("/{id}")
    public Shelter updateShelter(@PathVariable("id") Long id, @RequestBody Shelter shelter) {
        return shelterService.updateShelter(id, shelter);
    }

    @Operation(summary = "Create a shelter", description = "Create a new record for a shelter.")
    @PostMapping
    public Shelter createShelter(@RequestBody Shelter shelter) {
        return shelterService.createShelter(shelter);
    }

    @Operation(summary = "Remove a shelter", description = "Delete information about a shelter by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shelter removed"),
            @ApiResponse(responseCode = "404", description = "Shelter not found")
    })
    @DeleteMapping("/{id}")
    public Shelter removeShelter(@PathVariable("id") Long id) {
        return shelterService.removeShelter(id);
    }
}
