package pro.sky.tgbotcatshelter.entity;

import org.junit.jupiter.api.Test;
import pro.sky.tgbotcatshelter.constants.ShelterType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ShelterTest {

    private final long SHELTER_ID = 1L;
    private final String VALID_ADDRESS = "123 Main Street";
    private final String VALID_TIME_WORK = "9 AM - 5 PM";
    private final String VALID_DRIVING_DIRECTIONS = "Take Route 66";
    private final String VALID_PHONE_SHELTER = "123-456-7890";
    private final String VALID_PHONE_SECURITY = "987-654-3210";
    private final ShelterType VALID_SHELTER_TYPE = ShelterType.CAT_SHELTER;

    private final Shelter defaultShelter = new Shelter(
            SHELTER_ID,
            VALID_ADDRESS,
            VALID_TIME_WORK,
            VALID_DRIVING_DIRECTIONS,
            VALID_PHONE_SHELTER,
            VALID_PHONE_SECURITY,
            VALID_SHELTER_TYPE
    );

    @Test
    public void getAddressShelter_ShouldReturnAddress() {
        assertEquals(VALID_ADDRESS, defaultShelter.getAddressShelter());
    }

    @Test
    public void getTimeWork_ShouldReturnTimeWork() {
        assertEquals(VALID_TIME_WORK, defaultShelter.getTimeWork());
    }

    @Test
    public void getDrivingDirections_ShouldReturnDrivingDirections() {
        assertEquals(VALID_DRIVING_DIRECTIONS, defaultShelter.getDrivingDirections());
    }

    @Test
    public void getPhoneShelter_ShouldReturnPhoneShelter() {
        assertEquals(VALID_PHONE_SHELTER, defaultShelter.getPhoneShelter());
    }

    @Test
    public void getPhoneSecurity_ShouldReturnPhoneSecurity() {
        assertEquals(VALID_PHONE_SECURITY, defaultShelter.getPhoneSecurity());
    }

    @Test
    public void getShelterType_ShouldReturnShelterType() {
        assertSame(VALID_SHELTER_TYPE, defaultShelter.getShelterType());
    }
}
