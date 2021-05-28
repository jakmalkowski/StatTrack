package io.stattrack.stattrack.services;

import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserService {

    public void updateStats();


//    public void setAccounts(HashMap<String, String> accounts);

//    void setAccounts(UserModel userModel, HashMap<String, String> accounts);

    UserModel linkGameAccount(String newGame, String newAccount, String newRegion, UserDto user);

    UserModel unlinkGameAccount(String newGame, String newAccount, UserDto user);

    public void changeBio(String bio);

    boolean checkIfExists(String newGame, String newAccount, UserDto user);
}
