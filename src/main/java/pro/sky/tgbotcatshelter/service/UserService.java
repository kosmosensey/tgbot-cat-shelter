package pro.sky.tgbotcatshelter.service;

import pro.sky.tgbotcatshelter.entity.User;

import java.util.Collection;

/**
 * Сервис по работе с пользователем.
 */
public interface UserService {

    User create(User user);
    User update(Long id, User user);

    User delete(Long id);

    Collection<User> getAll();

    User getById(Long id);
}
