package io.stattrack.stattrack.controllers;
import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;
import io.stattrack.stattrack.models.UserRepository;
import io.stattrack.stattrack.services.SessionService;
import io.stattrack.stattrack.services.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserControllerImpl implements UserController {

    @Autowired
    UserRepository userRepository;

    public UserControllerImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value="/linkaccount")
    public String linkAccountPage(Model model, HttpSession session) {
//        UserServiceImpl userService = new UserServiceImpl(userRepository);
//        HttpSession currentSession = session.getSession();
//        currentSession.setAttribute("user", userModel);
        UserDto user = new UserDto();
        model.addAttribute("user", user);


        return "linkaccount";
    }


    @Override
    @RequestMapping(value = "/linkaccount", method = RequestMethod.POST)
    public String linkAccountFunc(@ModelAttribute("user") UserDto user, HttpSession session) {
        UserServiceImpl userService = new UserServiceImpl(userRepository);

//        currentSession.createUserSession(user);


        if (userService.checkIfExists(user.getTempGame(), user.getTempAccount(), (UserDto) session.getAttribute("user")))
            return "linkaccount_fail";

        userService.linkGameAccount(user.getTempGame(), user.getTempAccount(), (UserDto) session.getAttribute("user"));

        return "linkaccount_success";

    }

    @GetMapping(value="/unlinkaccount")
    public String unlinkAccountPage(Model userModel, HttpSession session) {
        return "unlinkaccount";
    }

    @Override
    @RequestMapping(value = "/unlinkaccount", method = RequestMethod.POST)
    public String unlinkAccountFunc(@ModelAttribute("user") UserDto user, HttpSession session){
        return "unlinkaccount";
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
