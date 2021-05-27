package io.stattrack.stattrack.services;

import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserService {

    public void updateStats();

    public void setDisplayed(HashMap<String, HashMap<String, ?>> args);

    public void addToDisplayed(String gameName, ArrayList<String> arrayToAdd);

    public void deleteFromDisplayed(String gameName, ArrayList<String> arrayToDelete);

//    public void setAccounts(HashMap<String, String> accounts);

//    void setAccounts(UserModel userModel, HashMap<String, String> accounts);

    public UserModel linkGameAccount(String newGame, String newAccount, UserDto user);

    public UserModel unlinkGameAccount(UserDto user);

    public void changeBio(String bio);

    public boolean checkIfExists(String newGame, String newAccount, UserDto user);
}
