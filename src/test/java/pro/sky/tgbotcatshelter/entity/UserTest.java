package pro.sky.tgbotcatshelter.entity;

import org.junit.jupiter.api.Test;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;

import static org.junit.jupiter.api.Assertions.*;
public class UserTest {

    private final Long USER_ID = 1L;
    private final String VALID_NAME = "John";
    private final String INVALID_NAME = "john";
    private final String VALID_PHONE_NUMBER = "123-456-7890";
    private final String NULL_PHONE_NUMBER = null;
    private final String VALID_CAR_NUMBER = "ABC123";
    private final String NULL_CAR_NUMBER = null;

    private final User defaultUser = new User(USER_ID, VALID_NAME, UserType.VOLUNTEER, UserStatus.APPROVE);

    @Test
    public void setUserName_ValidName_ShouldSetUserName() {
        User user = new User(USER_ID, VALID_NAME, UserType.VOLUNTEER, UserStatus.APPROVE);
        user.setUserName(VALID_NAME);
        assertEquals(VALID_NAME, user.getName());
    }

    @Test
    public void setUserName_InvalidName_ShouldThrowException() {
        assertThrows(RuntimeException.class, () -> defaultUser.setUserName(INVALID_NAME));
    }

    @Test
    public void setPhoneNumber_ValidNumber_ShouldFormatAndSetPhoneNumber() {
        defaultUser.setPhoneNumber(VALID_PHONE_NUMBER);
        assertEquals("(+997)1234567890", defaultUser.getPhoneNumber());
    }

    @Test
    public void setPhoneNumber_NullNumber_ShouldSetDefault() {
        defaultUser.setPhoneNumber(NULL_PHONE_NUMBER);
        assertEquals("Без телефона", defaultUser.getPhoneNumber());
    }

    @Test
    public void setCarNumber_ValidNumber_ShouldSetCarNumber() {
        defaultUser.setCarNumber(VALID_CAR_NUMBER);
        assertEquals(VALID_CAR_NUMBER, defaultUser.getCarNumber());
    }

    @Test
    public void setCarNumber_NullNumber_ShouldSetDefault() {
        defaultUser.setCarNumber(NULL_CAR_NUMBER);
        assertEquals("Без автомобиля", defaultUser.getCarNumber());
    }
}