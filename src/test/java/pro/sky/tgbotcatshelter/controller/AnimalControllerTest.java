package pro.sky.tgbotcatshelter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.tgbotcatshelter.constants.PetType;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.service.AnimalService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static pro.sky.tgbotcatshelter.constants.AnimalSex.BOY;
import static pro.sky.tgbotcatshelter.constants.AnimalSex.GIRL;
import static pro.sky.tgbotcatshelter.constants.PetType.DOG;

public class AnimalControllerTest {

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private AnimalController animalController;

    private MockMvc mockMvc;

    public AnimalControllerTest() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(animalController).build();
    }

    @Test
    public void testGetAllAnimals() throws Exception {
        Animal animal1 = new Animal(1L, "Bobik", DOG, "white", GIRL);
        Animal animal2 = new Animal(2L, "Bobik2", DOG, "white1", BOY);
        Collection<Animal> animals = Arrays.asList(animal1, animal2);

        when(animalService.getAll()).thenReturn(animals);

        mockMvc.perform(get("/animals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Bobik"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Bobik2"));

        verify(animalService, times(1)).getAll();
        verifyNoMoreInteractions(animalService);
    }
    @Test
    public void testGetByIdAnimals() throws Exception {
        Long animalId = 1L;
        Animal animal = new Animal(animalId, "Bobik", DOG, "white", GIRL);

        when(animalService.getById(animalId)).thenReturn(animal);

        mockMvc.perform(get("/animals/getById/{id}", animalId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(animalId))
                .andExpect(jsonPath("$.name").value("Bobik"));

        verify(animalService, times(1)).getById(animalId);
        verifyNoMoreInteractions(animalService);
    }

    @Test
    public void testCreateAnimal() throws Exception {
        Animal animal1 = new Animal(1L, "Lion", DOG, "Yellow", BOY);
        Animal animal2 = new Animal(2L, "Tigra", PetType.DOG, "Orange", GIRL);

        Collection<Animal> animals = Arrays.asList(animal1, animal2);

        when(animalService.getAll()).thenReturn(animals);

        mockMvc.perform(get("/animals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Lion"))
                .andExpect(jsonPath("$[0].petType").value("DOG"))
                .andExpect(jsonPath("$[0].color").value("Yellow"))
                .andExpect(jsonPath("$[0].sex").value("BOY"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Tigra"))
                .andExpect(jsonPath("$[1].petType").value("DOG"))
                .andExpect(jsonPath("$[1].color").value("Orange"))
                .andExpect(jsonPath("$[1].sex").value("GIRL"));

        verify(animalService, times(1)).getAll();
        verifyNoMoreInteractions(animalService);
    }
    @Test
    public void testUpdateAnimal() throws Exception {
        Long animalId = 1L;
        Animal updatedAnimal = new Animal(animalId, "Updated Lion", DOG, "Yellow", GIRL);

        when(animalService.update(eq(animalId), any(Animal.class))).thenReturn(updatedAnimal);

        mockMvc.perform(put("/animals/{id}", animalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Lion\",\"petType\":\"DOG\",\"color\":\"Yellow\",\"sex\":\"GIRL\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(animalId))
                .andExpect(jsonPath("$.name").value("Updated Lion"))
                .andExpect(jsonPath("$.petType").value("DOG"))
                .andExpect(jsonPath("$.color").value("Yellow"))
                .andExpect(jsonPath("$.sex").value("GIRL"));

        verify(animalService, times(1)).update(eq(animalId), any(Animal.class));
        verifyNoMoreInteractions(animalService);
    }

    @Test
    public void testRemoveAnimal() throws Exception {
        Long animalId = 1L;
        Animal removedAnimal = new Animal(animalId, "Lion", DOG, "Yellow", GIRL);

        when(animalService.remove(animalId)).thenReturn(removedAnimal);

        mockMvc.perform(delete("/animals/{id}", animalId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(animalId))
                .andExpect(jsonPath("$.name").value("Lion"))
                .andExpect(jsonPath("$.petType").value("DOG"))
                .andExpect(jsonPath("$.color").value("Yellow"))
                .andExpect(jsonPath("$.sex").value("GIRL"));

        verify(animalService, times(1)).remove(animalId);
        verifyNoMoreInteractions(animalService);
    }
}


