package controllers;

import models.UserService;

public interface UserController {
    public static void linkAccount(String game, String account) {}
    public static void unlinkAccount(String game, String account) {}
    public static void createUser(String login, String password, String email) {}
    public static void logIn(String login, String password) {}
    public static void logOut(){}
    public static void userPage(){}
    public static void settings(String someString) {}      //What String?
    public static void mainPage() {}
}
