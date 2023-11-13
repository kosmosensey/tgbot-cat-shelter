package pro.sky.tgbotcatshelter.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.repository.UserRepository;
import pro.sky.tgbotcatshelter.service.ValidationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    public static final long telegramId = 987654321L;
    public static final String userName = "TestUser";
    public static final UserType userType_Def = UserType.DEFAULT;
    public static final UserStatus userStatus_App = UserStatus.APPROVE;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserByTelegramId() {
        when(userRepository.findByTelegramId(telegramId)).thenReturn(new User(telegramId, userType_Def, userStatus_App));

        User foundUser = userService.findUserByTelegramId(telegramId);

        assertNotNull(foundUser);
        assertEquals(telegramId, foundUser.getTelegramId());
        assertEquals(userType_Def, foundUser.getUserType());
        assertEquals(userStatus_App, foundUser.getUserStatus());

        verify(userRepository, times(1)).findByTelegramId(telegramId);
    }

    @Test
     void testAddUser() {
        User existingUser = null;
        User newUser = new User(telegramId, userName, userType_Def, userStatus_App);

        when(userRepository.findByTelegramId(telegramId)).thenReturn(existingUser);
        when(validationService.validate(newUser)).thenReturn(true);

        userService.addUser(telegramId, userName, userType_Def, userStatus_App);

        verify(userRepository, Mockito.times(1)).findByTelegramId(telegramId);
        verify(validationService, Mockito.times(1)).validate(newUser);
        verify(userRepository, Mockito.times(1)).save(newUser);
    }


    @Test
    void testSaveUser() {
        User user = new User(telegramId, userType_Def, userStatus_App);

        when(validationService.validate(user)).thenReturn(true);

        userService.saveUser(user);

        verify(validationService, times(1)).validate(user);
        verify(userRepository, times(1)).save(user);
    }
}

