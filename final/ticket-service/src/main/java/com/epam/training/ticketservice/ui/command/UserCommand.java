package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class UserCommand {

    private final UserService userService;

    @ShellMethod(key = "user logout", value = "User logout")
    public String logout() {
        return userService.logout()
            .map(userDto -> userDto + " is logged out!")
            .orElse("You need to login first!");
    }

    @ShellMethod(key = "sign in privileged", value = "Admin login")
    public String adminLogin(String username, String password) {
        return userService.login(username, password)
                .map(userDto -> "signed in with privileged account " + userDto)
                .orElse("Login failed due to incorrect credentials");
    }

    @ShellMethod(key = "sign in", value = "User login")
    public String login(String username, String password) {
        return userService.login(username, password)
            .map(userDto -> userDto + " is successfully logged in!")
            .orElse("No such username or password!");
    }

    @ShellMethod(key = "describe account", value = "Get user information")
    public String print() {
        return userService.describe()
            .map(userDto -> "Signed in with account " + userDto)
            .orElse("You are not signed in");
    }

    @ShellMethod(key = "sign up", value = "User registration")
    public String registerUser(String userName, String password) {
        try {
            userService.registerUser(userName, password);
            return "Registration was successful!";
        } catch (Exception e) {
            return "Registration failed!";
        }
    }
}
