package pro.sky.tgbotcatshelter.service.impl;

import org.junit.jupiter.api.Test;
import pro.sky.tgbotcatshelter.constants.*;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.entity.Shelter;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.service.ValidationService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidationServiceImplTest {

    private ValidationService validationService = new ValidationServiceImpl();

    @Test
    void testValidateUser() {
        User user = new User();
        user.setTelegramId(12345678L);
        user.setUserName("John");
        user.setPhoneNumber("5559995555");
        user.setUserType(UserType.ADOPTER);
        user.setUserStatus(UserStatus.APPROVE);
        assertTrue(validationService.validate(user));
    }

    @Test
    void testValidateAnimal() {
        Animal animal = new Animal();
        animal.setColor("Black");
        animal.setName("Fido");
        animal.setPetType(PetType.DOG);
        animal.setSex(AnimalSex.BOY);
        assertTrue(validationService.validate(animal));
    }

    @Test
    void testValidateShelter() {
        Shelter shelter = new Shelter();
        shelter.setAddressShelter("123 Main St.");
        shelter.setTimeWork("8am-6pm");
        shelter.setDrivingDirections("Take the highway...");
        shelter.setPhoneShelter("555-1212");
        shelter.setPhoneSecurity("555-1313");
        shelter.setShelterType(ShelterType.DOG_SHELTER);
        assertTrue(validationService.validate(shelter));
    }

    @Test
    void testValidateInvalidObject() {
        assertFalse(validationService.validate(new Object()));
    }
}