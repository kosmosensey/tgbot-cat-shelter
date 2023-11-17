package pro.sky.tgbotcatshelter.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.service.impl.UserServiceImpl;

import java.util.Collection;

/**
 * Контроллер для управления пользователями.
 * Предоставляет методы для получения, обновления, создания и удаления информации о пользователях.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    /**
     * Конструктор класса UserController.
     * @param userService сервис для работы с пользователями
     */
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Получение списка всех пользователей.
     * @return список всех пользователей
     */
    @GetMapping
    public Collection<User> getAll() {
        return userService.getAll();
    }

    /**
     * Создание новой записи о пользователе.
     * @param user информация о новом пользователе
     * @return информация о созданном пользователе
     */
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    /**
     * Обновление информации о пользователе по его идентификатору.
     * @param id идентификатор пользователя
     * @param user новая информация о пользователе
     * @return обновленная информация о пользователе
     */
    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    /**
     * Удаление информации о пользователе по его идентификатору.
     * @param id идентификатор пользователя для удаления
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    /**
     * Получение пользователя по его идентификатору в Telegram.
     * @param user информация о пользователе (с идентификатором в Telegram)
     * @return информация о пользователе с указанным идентификатором в Telegram
     */
    @GetMapping("/byTelegramId")
    public User findByTelegramId(@RequestBody User user){
        return userService.findUserByTelegramId(user.getTelegramId());
    }
}

