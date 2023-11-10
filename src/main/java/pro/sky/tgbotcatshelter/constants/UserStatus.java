package pro.sky.tgbotcatshelter.constants;

public enum UserStatus {
    APPROVE("Подтвержденный"),
    BLOCKED("Заблокированный");

    private final String translationStatus;

    UserStatus(String translationStatus) {
        this.translationStatus = translationStatus;
    }

    public String getTranslationColor() {
        return translationStatus;
    }
}
