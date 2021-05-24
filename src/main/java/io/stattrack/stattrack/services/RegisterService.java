package io.stattrack.stattrack.services;

import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;
import io.stattrack.stattrack.models.UserRepository;

import java.util.List;

public class RegisterService {

    private UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(UserDto userDto) {
        UserModel user = new UserModel(userDto);
        userRepository.insert(user);

        return user;
    }

    private boolean isEmailInDb(String email) {
        return userRepository.findByEmail(email).size() > 0;
    }

    public boolean validateUser(UserDto user) {
        if(user.getPassword().equals(user.getPasswordRepeat())) {
            if (!isEmailInDb(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public UserModel searchForUser(UserDto user) {
        List<UserModel> foundUser = userRepository.findByEmail(user.getEmail());

        if (!foundUser.isEmpty()) {
            String password = foundUser.get(0).getPassword();
            if (password.equals(user.getPassword())) {
                return foundUser.get(0);
            }
        }
        return null;
    }
}
