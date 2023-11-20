package pro.sky.tgbotcatshelter.service;

/**
 * Интерфейс сервиса валидации объектов.
 */
public interface ValidationService {

    /**
     * Проверяет объект на соответствие определенным правилам валидации.
     *
     * @param object объект для валидации
     * @return {@code true}, если объект соответствует правилам валидации, в противном случае - {@code false}
     */
    boolean validate(Object object);
}
