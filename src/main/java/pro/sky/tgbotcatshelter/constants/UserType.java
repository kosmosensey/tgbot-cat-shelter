package pro.sky.tgbotcatshelter.constants;

/**
 * Константы типа пользователей.
 */
public enum UserType {

    DEFAULT("Пользователь"),
    GUEST("Гость"),
    ADOPTER("Усыновитель"),
    VOLUNTEER("Волонтер");
    private final String translationType;

    UserType(String translationType) {
        this.translationType = translationType;
    }


}
