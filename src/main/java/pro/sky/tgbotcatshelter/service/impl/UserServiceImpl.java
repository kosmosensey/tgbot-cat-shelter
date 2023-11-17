package pro.sky.tgbotcatshelter.service.impl;

import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.exception.ShelterNotFoundException;
import pro.sky.tgbotcatshelter.exception.UserNotFoundException;
import pro.sky.tgbotcatshelter.repository.UserRepository;
import pro.sky.tgbotcatshelter.service.UserService;
import pro.sky.tgbotcatshelter.service.ValidationService;

import java.util.Collection;

/**
 * Бизнес-логика по работе с пользователем.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserServiceImpl(UserRepository userRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    // Поиск пользователя по идентификатору Telegram
    @Override
    public User findUserByTelegramId(long telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }

    // Создание нового пользователя
    @Override
    public User create(User user) {
        logger.info("started method create");
        return userRepository.save(user);
    }

    // Обновление информации о пользователе
    @Override
    public User update(Long id, User user) {
        logger.info("started method update");
        User existingUser = userRepository.findById(id)
                .orElseThrow(ShelterNotFoundException::new);
        existingUser.setName(user.getName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setTelegramId(id);
        return userRepository.save(existingUser);
    }

    // Удаление пользователя по идентификатору
    @Override
    public User delete(Long id) {
        logger.info("started method delete");
        User existingUser = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(existingUser);
        return existingUser;
    }

    // Получение всех пользователей
    public Collection<User> getAll() {
        logger.info("started method getAll");
        return userRepository.findAll();
    }

    // Добавление нового пользователя, если он еще не существует
    @Override
    public void addUser(long telegramId, String userName, UserType userType, UserStatus userStatus) {
        User newUser = new User(telegramId, userName, userType, userStatus);
        User user = userRepository.findByTelegramId(telegramId);
        if (user == null) {
            saveUser(newUser);
        }
    }

    // Сохранение пользователя с проверкой валидности данных
    @Override
    public void saveUser(User user) {
        if (!validationService.validate(user)) {
            throw new ValidationException(user.toString());
        }
        userRepository.save(user);
    }
}
