package pro.sky.tgbotcatshelter.service;

import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.User;

/**
 * Сервис по работе с пользователем.
 */
public interface UserService {
    User findUserByTelegramId(long telegramId);

    void addUser(long telegramId, String userName, UserType userType, UserStatus userStatus);

    void saveUser(User user);
}
