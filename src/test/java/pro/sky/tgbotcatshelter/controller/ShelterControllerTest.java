package pro.sky.tgbotcatshelter.controller;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.tgbotcatshelter.constants.PetType;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.entity.Shelter;
import pro.sky.tgbotcatshelter.service.AnimalService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pro.sky.tgbotcatshelter.service.ShelterService;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static pro.sky.tgbotcatshelter.constants.ShelterType.DOG_SHELTER;
import static pro.sky.tgbotcatshelter.constants.ShelterType.CAT_SHELTER;

public class ShelterControllerTest {

    @Mock
    private ShelterService shelterService;

    @InjectMocks
    private ShelterController shelterController;


    private MockMvc mockMvc;
    public ShelterControllerTest() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(shelterController).build();
    }
    @Test
    public void getAllShelters() throws Exception {

        Shelter shelter1 = new Shelter(1L, "Улица 1",
                "с 8-17","Схема1",
                "1511454164","11311313",
                DOG_SHELTER);
        Shelter shelter2 = new Shelter(2L,"Улица 2",
                "с 8-16","Схема2",
                "1511454165","11311314",
                CAT_SHELTER);
        Collection<Shelter> shelters = Arrays.asList(shelter1, shelter2);
        when(shelterService.getAllShelters()).thenReturn(shelters);
        mockMvc.perform(get("/shelters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect( jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].addressShelter").value("Улица 1"))
                .andExpect(jsonPath("$[0].timeWork").value("с 8-17"))
                .andExpect(jsonPath("$[0].drivingDirections").value("Схема1"))
                .andExpect(jsonPath("$[0].phoneShelter").value("1511454164"))
                .andExpect(jsonPath("$[0].phoneSecurity").value("11311313"))
                .andExpect(jsonPath("$[0].shelterType").value("DOG_SHELTER"))

                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].addressShelter").value("Улица 2"))
                .andExpect(jsonPath("$[1].timeWork").value("с 8-16"))
                .andExpect(jsonPath("$[1].drivingDirections").value("Схема2"))
                .andExpect(jsonPath("$[1].phoneShelter").value("1511454165"))
                .andExpect(jsonPath("$[1].phoneSecurity").value("11311314"))
                .andExpect(jsonPath("$[1].shelterType").value("CAT_SHELTER"));
    }

   @Test
    void getShelterById() throws Exception {
        Long shelterId = 1L;
        Shelter shelter = new Shelter(shelterId, "Улица 1",
                "с 8-17","Схема1",
                "1511454164","11311313",
                DOG_SHELTER);
        when(shelterService.getShelterById(shelterId)).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders.get("/shelters/{id}", shelterId)
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.addressShelter").value("Улица 1"))
               .andExpect(jsonPath("$.timeWork").value("с 8-17"))
               .andExpect(jsonPath("$.drivingDirections").value("Схема1"))
               .andExpect(jsonPath("$.phoneShelter").value("1511454164"))
               .andExpect(jsonPath("$.phoneSecurity").value("11311313"))
               .andExpect(jsonPath("$.shelterType").value("DOG_SHELTER"));
    }

    @Test
    void updateShelter() throws Exception {
        Long shelterId = 1L;
        Shelter updatedShelter = new Shelter(shelterId,"Улица 2",
                "с 8-16","Схема2",
                "1511454165","11311314",
                CAT_SHELTER);
        when(shelterService.updateShelter(eq(shelterId), any(Shelter.class))).thenReturn(updatedShelter);
         mockMvc.perform(MockMvcRequestBuilders.put("/shelters/{id}", shelterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"addressShelter\":\"Улица 2\"," +
                                "\"timeWork\":\"с 8-16\"," +
                                "\"drivingDirections\":\"Схема2\"," +
                                "\"phoneShelter\":\"1511454165\"," +
                                "\"phoneSecurity\":\"11311314\"," +
                                "\"shelterType\":\"CAT_SHELTER\"}"))
                .andExpect(status().isOk())
                 .andExpect(jsonPath("$.id").value(1))
                 .andExpect(jsonPath("$.addressShelter").value("Улица 2"))
                 .andExpect(jsonPath("$.timeWork").value("с 8-16"))
                 .andExpect(jsonPath("$.drivingDirections").value("Схема2"))
                 .andExpect(jsonPath("$.phoneShelter").value("1511454165"))
                 .andExpect(jsonPath("$.phoneSecurity").value("11311314"))
                 .andExpect(jsonPath("$.shelterType").value("CAT_SHELTER"));
    }
    @Test
    void createShelter() throws Exception {

        Shelter newShelter = new Shelter(1L, "Улица 1",
                "с 8-17","Схема1",
                "1511454164","11311313",
                DOG_SHELTER);
        when(shelterService.createShelter(any(Shelter.class))).thenReturn(new Shelter(1L, "Улица 1",
                "с 8-17", "Схема1",
                "1511454164", "11311313",
                DOG_SHELTER));
          mockMvc.perform(MockMvcRequestBuilders.post("/shelters")
                        .contentType(MediaType.APPLICATION_JSON)
                          .content("{\"addressShelter\":\"Улица 1\"," +
                                  "\"timeWork\":\"с 8-17\"," +
                                  "\"drivingDirections\":\"Схема1\"," +
                                  "\"phoneShelter\":\"1511454164\"," +
                                  "\"phoneSecurity\":\"11311313\"," +
                                  "\"shelterType\":\"DOG_SHELTER\"}"))
                .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(1))
                  .andExpect(jsonPath("$.addressShelter").value("Улица 1"))
                  .andExpect(jsonPath("$.timeWork").value("с 8-17"))
                  .andExpect(jsonPath("$.drivingDirections").value("Схема1"))
                  .andExpect(jsonPath("$.phoneShelter").value("1511454164"))
                  .andExpect(jsonPath("$.phoneSecurity").value("11311313"))
                  .andExpect(jsonPath("$.shelterType").value("DOG_SHELTER"));
    }

    @Test
    void removeShelter() throws Exception {
        Long shelterId = 1L;
        Shelter removedShelter = new Shelter(shelterId,"Улица 2",
                "с 8-16","Схема2",
                "1511454165","11311314",
                CAT_SHELTER);
        when(shelterService.removeShelter(shelterId)).thenReturn(removedShelter);

       mockMvc.perform(MockMvcRequestBuilders.delete("/shelters/{id}", shelterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.addressShelter").value("Улица 2"))
               .andExpect(jsonPath("$.timeWork").value("с 8-16"))
               .andExpect(jsonPath("$.drivingDirections").value("Схема2"))
               .andExpect(jsonPath("$.phoneShelter").value("1511454165"))
               .andExpect(jsonPath("$.phoneSecurity").value("11311314"))
               .andExpect(jsonPath("$.shelterType").value("CAT_SHELTER"));
    }
}
