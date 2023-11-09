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
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> getAll() {

        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {

        return userService.getById(id);
    }

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
}
