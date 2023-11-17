package pro.sky.tgbotcatshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.service.impl.UserServiceImpl;

import java.util.Collection;

// ... (existing imports)

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users", description = "Retrieve a collection of all users.")
    @GetMapping
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "Create a user", description = "Create a new user.")
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @Operation(summary = "Update user information", description = "Update user information based on ID.")
    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @Operation(summary = "Delete user", description = "Delete user information based on ID.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @Operation(summary = "Get user by Telegram ID", description = "Retrieve user information based on Telegram ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/byTelegramId")
    public User findByTelegramId(@RequestBody User user){
        return userService.findUserByTelegramId(user.getTelegramId());
    }
}

