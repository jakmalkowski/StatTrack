package io.stattrack.stattrack.services;

import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;

import io.stattrack.stattrack.models.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
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

//    @Override
//    public void setAccounts(HashMap<String, String> accounts) {
//
//    }

    @Override
    public UserModel linkGameAccount(UserDto user) {
        HashMap<String, List<String>> gameAccounts = user.getGameAccounts();
        UserModel userModel = new UserModel(user);

        if(gameAccounts == null){                                     //first ever account link
            HashMap<String, List<String>> newAccounts = new HashMap<>();
            ArrayList<String> newList = new ArrayList<>();
            newList.add(user.getTempAccount());
            newAccounts.put(user.getTempGame(), newList);
            user.setGameAccounts(newAccounts);
        }
        else if(!gameAccounts.containsKey(user.getTempGame())){                  //first account link to this game
            ArrayList<String> newList = new ArrayList<>();
            newList.add(user.getTempAccount());
            gameAccounts.put(user.getTempGame(), newList);
        }
        else{                                                           //other account(s) for this game already linked
            gameAccounts.get(user.getTempGame()).add(user.getTempAccount());
        }

//        System.out.println("Before:" + userModel.getAccounts());
        userModel.setAccounts(gameAccounts);                            //set user accounts to updated account list
//        System.out.println("After:" + userModel.getAccounts());
        return userModel;
    }

    @Override
    public UserModel unlinkGameAccount(UserDto user) {
        HashMap<String, List<String>> gameAccounts = user.getGameAccounts();
        UserModel userModel = new UserModel(user);
        gameAccounts.remove(user.getTempGame()).remove(user.getTempAccount());
        userModel.setAccounts(gameAccounts);

        return userModel;
    }

    @Override
    public void changeBio(String bio) {

    }

    @Override
    public boolean checkIfExists(UserDto user) {
        if(user.getGameAccounts() != null)
            if(user.getGameAccounts().containsKey(user.getTempGame()))
                if(user.getAccount(user.getTempGame()).contains(user.getTempAccount()))
                    return true;

        return false;
    }
}
