package pro.sky.tgbotcatshelter.service.impl;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.repository.ReportUserRepository;
import pro.sky.tgbotcatshelter.repository.UserRepository;
import pro.sky.tgbotcatshelter.service.UserService;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRequestServiceImplTest {
    private static final String CORRECT_USER_NAME = "Nick";
    private static final long CORRECT_USER_ID = 123456789;
    private static final UserType CORRECT_USER_TYPE = UserType.DEFAULT;
    private static final UserStatus CORRECT_USER_STATUS = UserStatus.APPROVE;


    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private ReportUserRepository reportRepository;

    @InjectMocks
    private UserRequestServiceImpl userRequestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateButtonClick() {
        Update update = mock(Update.class);
        CallbackQuery callbackQuery = mock(CallbackQuery.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.callbackQuery()).thenReturn(callbackQuery);
        when(callbackQuery.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(1234L);
        when(callbackQuery.data()).thenReturn("CLICK_CAT_SHELTER");

        userRequestService.createButtonClick(update);
    }

    @Test
    void testSendMessageStartDefaultUser() throws URISyntaxException, IOException {
        pro.sky.tgbotcatshelter.entity.User user = null;

        when(userService.findUserByTelegramId(1L)).thenReturn(user);
    }

    @Test
    void testSendMessageStartNotNewUser() {
        pro.sky.tgbotcatshelter.entity.User user = new pro.sky.tgbotcatshelter.entity.User(
                CORRECT_USER_ID, CORRECT_USER_NAME, CORRECT_USER_TYPE, CORRECT_USER_STATUS);

        when(userService.findUserByTelegramId(1L)).thenReturn(user);
    }

    @Test
    void testSendMessageStartVolunteer() {
        pro.sky.tgbotcatshelter.entity.User user = new pro.sky.tgbotcatshelter.entity.User(
                CORRECT_USER_ID, CORRECT_USER_NAME, CORRECT_USER_TYPE, CORRECT_USER_STATUS);

        when(userService.findUserByTelegramId(1L)).thenReturn(user);
    }

    @Test
    void testSendMessageStartBlockedUser() {

        pro.sky.tgbotcatshelter.entity.User user = new pro.sky.tgbotcatshelter.entity.User(
                CORRECT_USER_ID, CORRECT_USER_NAME, CORRECT_USER_TYPE, CORRECT_USER_STATUS);

        when(userService.findUserByTelegramId(1L)).thenReturn(user);
    }

}
