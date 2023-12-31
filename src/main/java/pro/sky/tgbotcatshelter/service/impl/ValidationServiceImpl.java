package pro.sky.tgbotcatshelter.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.entity.Shelter;
import pro.sky.tgbotcatshelter.service.ValidationService;

/**
 * Реализация сервиса валидации данных.
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    /**
     * Метод, выполняющий валидацию объекта.
     *
     * @param object объект для валидации
     * @return true, если объект валиден, иначе false
     */
    @Override
    public boolean validate(Object object) {

        // Проверка на тип объекта и соответствующие критерии валидации

        if (object instanceof User) {
            // Проверка валидности объекта типа User
            return ((User) object).getTelegramId() != 0
                   && ((User) object).getUserType() != null
                   && ((User) object).getUserStatus() != null

                   || ((User) object).getTelegramId() != 0
                      && ((User) object).getName() != null
                      && ((User) object).getPhoneNumber() != null
                      && ((User) object).getUserType() != null
                      && ((User) object).getUserStatus() != null

                   || ((User) object).getTelegramId() != 0
                      && ((User) object).getName() != null
                      && ((User) object).getAddress() != null
                      && ((User) object).getPhoneNumber() != null
                      && ((User) object).getUserType() != null
                      && ((User) object).getUserStatus() != null;

        } else if (object instanceof Animal) {
            // Проверка валидности объекта типа Animal
            return ((Animal) object).getColor() != null
                   && ((Animal) object).getName() != null
                   && ((Animal) object).getPetType() != null
                   && ((Animal) object).getSex() != null;

        } else if (object instanceof Shelter) {
            // Проверка валидности объекта типа Shelter
            return ((Shelter) object).getAddressShelter() != null
                   && ((Shelter) object).getTimeWork() != null
                   && ((Shelter) object).getDrivingDirections() != null
                   && ((Shelter) object).getPhoneShelter() != null
                   && ((Shelter) object).getPhoneSecurity() != null
                   && ((Shelter) object).getShelterType() != null;

        } else {
            return false;
        }
    }
}
