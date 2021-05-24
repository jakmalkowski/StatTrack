package io.stattrack.stattrack.controllers;

import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;
import io.stattrack.stattrack.models.UserRepository;
import io.stattrack.stattrack.services.RegisterService;
import io.stattrack.stattrack.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/register")
    public String showRegistrationForm(Model model, HttpSession session) {
        if (isUserLogged(session)) return "redirect:/";

        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "registration";
    }

    @PostMapping(value="/register")
    public String submitRegisterForm(@ModelAttribute("user") UserDto user, HttpSession session) {
        if (isUserLogged(session)) return "redirect:/";

        RegisterService registerService = new RegisterService(userRepository);
        if (registerService.validateUser(user)) {
            UserModel createdUser = registerService.registerUser(user);
            SessionService sessionService = new SessionService(session);
            sessionService.createUserSession(createdUser);
            return "register_success";
        }
        return "register_failed";
    }

    @GetMapping(value="/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }

    @GetMapping(value="/login")
    public String login(Model model, HttpSession session) {
        if (isUserLogged(session)) return "redirect:/";

        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "login";
    }


    @PostMapping(value="/login")
    public String submitLoginForm(@ModelAttribute("user") UserDto user, HttpSession session) {
        if (isUserLogged(session)) return "redirect:/";

        RegisterService registerService = new RegisterService(userRepository);
        UserModel foundUser = registerService.searchForUser(user);
        if (foundUser != null) {
            SessionService sessionService = new SessionService(session);
            sessionService.createUserSession(foundUser);

            return "login_success";
        }
        return "login_failed";
    }

    @GetMapping(value="/test")
    public String testtt(Model model, HttpSession session) {
        SessionService sessionService = new SessionService(session);
        UserDto userDto = sessionService.getUserDto();
        model.addAttribute("user", userDto);

        return "login_test";
    }

    private boolean isUserLogged(HttpSession httpSession) {
        SessionService sessionService = new SessionService(httpSession);
        return sessionService.getUserDto() != null;
    }
}
