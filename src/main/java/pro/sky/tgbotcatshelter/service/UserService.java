package pro.sky.tgbotcatshelter.service;

import pro.sky.tgbotcatshelter.entity.User;

import java.util.Collection;

/**
 * Сервис по работе с пользователем.
 */
public interface UserService {

    void create();
    void update();

    void delete();

    Collection<User> getAll();

    void getById();
}
