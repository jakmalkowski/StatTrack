package io.stattrack.stattrack.controllers;

import io.stattrack.stattrack.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public interface UserController {

//    public String linkaccount(@ModelAttribute("user") UserDto user, HttpServletRequest session);

    @RequestMapping(value = "/linkaccount", method = RequestMethod.POST)
    String linkAccountFunc(@ModelAttribute("user") UserDto user, HttpSession session);

    @RequestMapping(value = "/unlinkaccount", method = RequestMethod.POST)
    String unlinkAccountFunc(@ModelAttribute("user") UserDto user, HttpSession session);

    void createUser(String login, String password, String email);

    void logIn(String login, String password);

    void logOut();

    void userPage();

    void settings(String someString);      //What String?

    void mainPage();



}
