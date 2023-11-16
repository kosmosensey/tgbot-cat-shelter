package pro.sky.tgbotcatshelter.service.impl;


import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.entity.User;

import java.util.Collection;

/**
 * Бизнес-логика по работе с пользователем.
 */
@Service
public class UserServiceImpl implements UserService {

private final UserRepository userRepository;

private final ValidationService validationService;

public UserServiceImpl(UserRepository userRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    @Override
    public User findUserByTelegramId(long telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }


    @Override
    public User create(User user) {
        logger.info("started method create");
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        logger.info("started method update");
        User existingUser = userRepository.findById(id)
                .orElseThrow(ShelterNotFoundException::new);
        existingUser.setName(user.getName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setTelegram_id(id);
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

    public Collection<User> getAll() {
        logger.info("started method getAll");
        return userRepository.findAll();

    public void addUser(long telegramId, String userName, UserType userType, UserStatus userStatus) {
        User newUser = new User(telegramId,userName, userType, userStatus);
        User user = userRepository.findByTelegramId(telegramId);
        if (user == null) {
            saveUser(newUser);
        }
    }
      
    @Override
    public void saveUser(User user) {
        if (!validationService.validate(user)) {
            throw new ValidationException(user.toString());
        }
        userRepository.save(user);
    }
}

