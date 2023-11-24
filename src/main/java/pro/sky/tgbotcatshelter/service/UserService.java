package pro.sky.tgbotcatshelter.service;

import pro.sky.tgbotcatshelter.constants.PetType;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.entity.User;

import java.util.List;

/**
 * Сервис по работе с пользователем.
 */
public interface UserService {

    User create(User user);

    User update(Long id, User user);

    User delete(Long id);

    List<User> getAll();

    User findUserByTelegramId(long telegramId);

    void addUser(long telegramId, String userName, UserType userType, UserStatus userStatus);

    void saveUser(User user);


    void editUser(Long userId, User updatedUser);

    List<User> getAllUsers();

    List<User> getAllUserByType(UserType userType);

}
