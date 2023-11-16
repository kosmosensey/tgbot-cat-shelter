package pro.sky.tgbotcatshelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.tgbotcatshelter.constants.AnimalSex;
import pro.sky.tgbotcatshelter.constants.PetType;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.repository.AnimalRepository;
import pro.sky.tgbotcatshelter.service.AnimalService;
import pro.sky.tgbotcatshelter.service.impl.AnimalServiceImpl;

import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalController.class)
public class AnimalControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
  AnimalRepository animalRepository;
    @SpyBean
    AnimalService animalService;
    public static final PetType DOG = PetType.DOG;
    public static final AnimalSex GIRL = AnimalSex.GIRL;

    @Test
    public void getById() throws Exception {
        Animal animal = new Animal(1L, "Bobik", DOG, "white", GIRL);
        when(AnimalRepository.findById(1L)).thenReturn(Optional.of(animal));
        mockMvc.perform(MockMvcRequestBuilders.get("/animals/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bobik"))
                .andExpect(jsonPath("$.color").value("white"));
}

@Test
public void testCreate() {
        Animal animal = new Animal();
        when(animalRepository.save(animal)).thenReturn(animal);

        Animal result = animalService.create(animal);

        assertEquals(animal, result);
    }
    @Test
   void create() throws Exception {
        Animal animal = new Animal(1L, "Bobik", DOG, "white", GIRL);
        when(AnimalRepository.save(ArgumentMatchers.any(Animal.class))).thenReturn(animal);
        mockMvc.perform(MockMvcRequestBuilders.get("/animals")
                        .content(objectMapper.writeValueAsString(animal))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bobik"))
                .andExpect(jsonPath("$.color").value("white"));
    }

}
