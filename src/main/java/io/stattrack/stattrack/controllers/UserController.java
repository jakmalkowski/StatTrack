package io.stattrack.stattrack.controllers;

public interface UserController {

    void linkAccount(String game, String account);

    void unlinkAccount(String game, String account);

    void createUser(String login, String password, String email);

    void logIn(String login, String password);

    void logOut();

    void userPage();

    void settings(String someString);      //What String?

    void mainPage();

}
