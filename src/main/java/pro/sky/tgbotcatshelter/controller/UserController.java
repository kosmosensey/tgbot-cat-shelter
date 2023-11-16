package pro.sky.tgbotcatshelter.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.service.impl.UserServiceImpl;

import java.util.Collection;

/**
 * Контроллер пользователей
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> getAll() {
        return userService.getAll();}


    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user) {

        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {

        userService.delete(id);
    }

    @GetMapping("/byTelegramId")
    public User findByTelegramId(@RequestBody User user){
        return userService.findUserByTelegramId(user.getTelegramId());
    }
}
