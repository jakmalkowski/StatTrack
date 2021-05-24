package io.stattrack.stattrack.services;

import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;

import javax.servlet.http.HttpSession;

public class SessionService {
    HttpSession session;

    public SessionService(HttpSession session) {
        this.session = session;
    }

    public void createUserSession(UserDto user) {
        session.setAttribute("user", user);
    }

    public void createUserSession(UserModel user) {
        UserDto userDto = new UserDto(user);
        session.setAttribute("user", userDto);
    }

    public UserDto getUserDto() {
        return (UserDto)session.getAttribute("user");
    }
}
