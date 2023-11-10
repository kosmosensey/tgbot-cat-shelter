package pro.sky.tgbotcatshelter.service.impl;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.repository.UserRepository;
import pro.sky.tgbotcatshelter.service.UserService;
import pro.sky.tgbotcatshelter.service.ValidationService;

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
    public void addUser(long telegramId, String userName, UserType userType, UserStatus userStatus) {
        User newUser = new User(telegramId, userType, userStatus);
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
