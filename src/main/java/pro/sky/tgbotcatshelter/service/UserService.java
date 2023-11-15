package pro.sky.tgbotcatshelter.service;

import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.User;

import java.util.Collection;

/**
 * Сервис по работе с пользователем.
 */
public interface UserService {

    void addUser(long telegramId, String userName, UserType userType);

    User findUserByTelegramId(long telegramId);


    void saveUser(User user);

    User create(User user);

    User update(Long id, User user);

    User delete(Long id);

    Collection<User> getAll();

}
