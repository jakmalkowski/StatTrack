package io.stattrack.stattrack.models;

import java.util.ArrayList;
import java.util.HashMap;

public class UserServiceImpl implements UserService {

    UserModel userModel;

    public UserServiceImpl (UserModel userModel) {
        this.userModel = userModel;
    }


    @Override
    public void updateStats() {

    }

    @Override
    public void setDisplayed(HashMap<String, HashMap<String, ?>> args) {

    }

    @Override
    public void addToDisplayed(String gameName, ArrayList<String> arrayToAdd) {

    }

    @Override
    public void deleteFromDisplayed(String gameName, ArrayList<String> arrayToDelete) {

    }

    @Override
    public void setAccounts(HashMap<String, String> accounts) {

    }

    @Override
    public void linkAccount(String gameName, String account) {

    }

    @Override
    public void changeBio(String bio) {

    }
}
