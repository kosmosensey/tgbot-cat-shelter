package pro.sky.tgbotcatshelter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.exception.ShelterNotFoundException;
import pro.sky.tgbotcatshelter.exception.UserNotFoundException;
import pro.sky.tgbotcatshelter.repository.UserRepository;
import pro.sky.tgbotcatshelter.service.UserService;

import java.util.Collection;

/**
 * Бизнес-логика по работе с пользователем.
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        logger.info("started method create");
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        logger.info("started method getById");
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User update(Long id, User user) {
        logger.info("started method update");
        User existingUser = userRepository.findById(id)
                .orElseThrow(ShelterNotFoundException::new);
        existingUser.setName(user.getName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(existingUser);
    }

    @Override
    public User delete(Long id) {
        logger.info("started method delete");
        User existingUser = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(existingUser);
        return existingUser;
    }

    @Override
    public Collection<User> getAll() {
        logger.info("started method getAll");
        return userRepository.findAll();
    }
}

