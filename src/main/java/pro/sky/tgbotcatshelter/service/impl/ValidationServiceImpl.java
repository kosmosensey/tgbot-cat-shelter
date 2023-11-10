package pro.sky.tgbotcatshelter.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.entity.Shelter;
import pro.sky.tgbotcatshelter.service.ValidationService;


@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Object object) {

        if (object instanceof User) {
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
            return ((Animal) object).getColor() != null
                   && ((Animal) object).getName() != null
                   && ((Animal) object).getPetType() != null
                   && ((Animal) object).getSex() != null;

        } else if (object instanceof Shelter) {
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
