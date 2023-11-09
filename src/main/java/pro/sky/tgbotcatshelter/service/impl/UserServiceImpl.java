package pro.sky.tgbotcatshelter.service.impl;

import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.exception.ShelterNotFoundException;
import pro.sky.tgbotcatshelter.exception.UserNotFoundException;
import pro.sky.tgbotcatshelter.repository.UserRepository;
import pro.sky.tgbotcatshelter.service.UserService;

import java.util.Collection;

/**
 * Бизнес-логика по работе с пользователем.
 */

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                        .orElseThrow(UserNotFoundException::new);
    }

    public User update(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(ShelterNotFoundException::new);
        existingUser.setName(user.getName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(existingUser);
    }

    public User delete(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(existingUser);
        return existingUser;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public Collection<User> getAll() {

        return null;
    }

    @Override
    public void getById() {

    }
}
