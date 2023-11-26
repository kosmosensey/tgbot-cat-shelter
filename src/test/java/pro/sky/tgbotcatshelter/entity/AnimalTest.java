package pro.sky.tgbotcatshelter.entity;

import org.junit.jupiter.api.Test;
import pro.sky.tgbotcatshelter.constants.AnimalSex;
import pro.sky.tgbotcatshelter.constants.PetType;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    private final Long VALID_ID = 1L;
    private final String VALID_NAME = "Fluffy";
    private final PetType VALID_PET_TYPE = PetType.CAT;
    private final String VALID_COLOR = "White";
    private final AnimalSex VALID_SEX = AnimalSex.BOY;

    private final Animal defaultAnimal = new Animal(
            VALID_ID,
            VALID_NAME,
            VALID_PET_TYPE,
            VALID_COLOR,
            VALID_SEX
    );

    @Test
    public void getId_ShouldReturnId() {
        assertEquals(VALID_ID, defaultAnimal.getId());
    }

    @Test
    public void getName_ShouldReturnName() {
        assertEquals(VALID_NAME, defaultAnimal.getName());
    }

    @Test
    public void getPetType_ShouldReturnPetType() {
        assertEquals(VALID_PET_TYPE, defaultAnimal.getPetType());
    }

    @Test
    public void getColor_ShouldReturnColor() {
        assertEquals(VALID_COLOR, defaultAnimal.getColor());
    }

    @Test
    public void getSex_ShouldReturnSex() {
        assertEquals(VALID_SEX, defaultAnimal.getSex());
    }
}
