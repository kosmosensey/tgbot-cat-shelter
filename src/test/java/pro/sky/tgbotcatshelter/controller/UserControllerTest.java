package pro.sky.tgbotcatshelter.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import pro.sky.tgbotcatshelter.constants.ShelterType;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.service.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.tgbotcatshelter.constants.ShelterType.CAT_SHELTER;
import static pro.sky.tgbotcatshelter.constants.ShelterType.DOG_SHELTER;
import static pro.sky.tgbotcatshelter.constants.UserType.*;
import static pro.sky.tgbotcatshelter.constants.UserStatus.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    private final MockMvc mockMvc;
    public UserControllerTest() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    @Test
    public void getAllUsers() throws Exception {

       User User1 = new User(1L, 2L,"Ivan",
                "Улица1","1236547893",
                "15114",DOG_SHELTER,
               DEFAULT,APPROVE, true);

        User User2 = new User(2L, 3L,"Ivan2",
                "Улица1","1236547893",
                "15114",DOG_SHELTER,
                DEFAULT,APPROVE, false);
        List<User> users = Arrays.asList(User1, User2);
        when(userService.getAll()).thenReturn(users);
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].telegramId").value(2))
                .andExpect(jsonPath("$[0].name").value("Ivan"))
                .andExpect(jsonPath("$[0].address").value("Улица1"))
                .andExpect(jsonPath("$[0].phoneNumber").value("1236547893"))
                .andExpect(jsonPath("$[0].carNumber").value("15114"))
                .andExpect(jsonPath("$[0].shelterType").value("DOG_SHELTER"))
                .andExpect(jsonPath("$[0].userType").value("DEFAULT"))
                .andExpect(jsonPath("$[0].userStatus").value("APPROVE"))
                .andExpect(jsonPath("$[0].trialPeriod").value("true"))

                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].telegramId").value(3))
                .andExpect(jsonPath("$[1].name").value("Ivan2"))
                .andExpect(jsonPath("$[1].address").value("Улица1"))
                .andExpect(jsonPath("$[1].phoneNumber").value("1236547893"))
                .andExpect(jsonPath("$[1].carNumber").value("15114"))
                .andExpect(jsonPath("$[1].shelterType").value("DOG_SHELTER"))
                .andExpect(jsonPath("$[1].userType").value("DEFAULT"))
                .andExpect(jsonPath("$[1].userStatus").value("APPROVE"))
                .andExpect(jsonPath("$[1].trialPeriod").value("false"));
    }

   @Test
    void updateUser() throws Exception {
        Long userId = 1L;
        User updatedUser = new User(userId,2L,"Ivan",
                "Улица1","1236547893",
                "11311",DOG_SHELTER,
                DEFAULT,APPROVE, false);
        when(userService.update(eq(userId), any(User.class))).thenReturn(updatedUser);
         mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                         .content("{\"telegramId\":\"2\"," +
                                "\"name\":\"Ivan\"," +
                                "\"address\":\"Улица1\"," +
                                "\"phoneNumber\":\"1236547893\"," +
                                "\"carNumber\":\"11311\"," +
                                "\"shelterType\":\"DOG_SHELTER\"," +
                                "\"userType\":\"DEFAULT\"," +
                                "\"userStatus\":\"APPROVE\"," +
                                "\"trialPeriod\":\"false\"}"))
                .andExpect(status().isOk())
                 .andExpect(jsonPath("$.id").value(1))
                 .andExpect(jsonPath("$.telegramId").value(2))
                 .andExpect(jsonPath("$.name").value("Ivan"))
                 .andExpect(jsonPath("$.address").value("Улица1"))
                 .andExpect(jsonPath("$.phoneNumber").value("1236547893"))
                 .andExpect(jsonPath("$.carNumber").value("11311"))
                 .andExpect(jsonPath("$.shelterType").value("DOG_SHELTER"))
                 .andExpect(jsonPath("$.userType").value("DEFAULT"))
                 .andExpect(jsonPath("$.userStatus").value("APPROVE"))
                 .andExpect(jsonPath("$.trialPeriod").value("false"));
    }
   @Test
    void createUser() throws Exception {

       User newUser = new User(1L, 2L,"Ivan",
               "Улица1","1236547893",
               "15114",DOG_SHELTER,
               DEFAULT,APPROVE, true);
        when(userService.create(any(User.class))).thenReturn(new User(1L, 2L,"Ivan",
                "Улица1","1236547893",
                "15114",DOG_SHELTER,
                DEFAULT,APPROVE, true));
          mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                          .content("{\"telegramId\":\"2\"," +
                                  "\"name\":\"Ivan\"," +
                                  "\"address\":\"Улица1\"," +
                                  "\"phoneNumber\":\"1236547893\"," +
                                  "\"carNumber\":\"15114\"," +
                                  "\"shelterType\":\"DOG_SHELTER\"," +
                                  "\"userType\":\"DEFAULT\"," +
                                  "\"userStatus\":\"APPROVE\"," +
                                  "\"trialPeriod\":\"true\"}"))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(1))
                  .andExpect(jsonPath("$.telegramId").value(2))
                  .andExpect(jsonPath("$.name").value("Ivan"))
                  .andExpect(jsonPath("$.address").value("Улица1"))
                  .andExpect(jsonPath("$.phoneNumber").value("1236547893"))
                  .andExpect(jsonPath("$.carNumber").value("15114"))
                  .andExpect(jsonPath("$.shelterType").value("DOG_SHELTER"))
                  .andExpect(jsonPath("$.userType").value("DEFAULT"))
                  .andExpect(jsonPath("$.userStatus").value("APPROVE"))
                  .andExpect(jsonPath("$.trialPeriod").value("true"));
    }

    @Test
    void removeUser() throws Exception {
        Long userId = 1L;
        User removedUser =  new User(1L, 2L,"Ivan",
                "Улица1","1236547893",
                "15114",DOG_SHELTER,
                DEFAULT,APPROVE, true);
        when(userService.delete(userId)).thenReturn(removedUser);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).delete(userId);
    }
}
