package io.stattrack.stattrack.controllers;

import io.stattrack.stattrack.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;

public class UserControllerImpl implements UserController {
    UserService userService;
//    private UserView UserView;

    public UserControllerImpl (UserService userService) {
        this.userService = userService;
    }

    @Override
    public void linkAccount(String game, String account) {

    }

    @Override
    public void unlinkAccount(String game, String account) {

    }

    @Override
    public void createUser(String login, String password, String email) {

    }

    @Override
    public void logIn(String login, String password) {

    }

    @Override
    public void logOut(){

    }

    @Override
    public void userPage(){

    }

    @Override
    public void settings(String someString) {          //What String?

    }

    @Override
    public void mainPage() {

    }
}
