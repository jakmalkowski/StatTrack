package io.stattrack.stattrack.services;

import io.stattrack.stattrack.dto.GameAccount;
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
    public UserModel linkGameAccount(String newGame, String newAccount, String newRegion, UserDto user) {
        HashMap<String, GameAccount> gameAccounts = user.getGameAccounts();
        GameAccount gameAccount = new GameAccount();
        gameAccount.setRegion(newRegion);
        gameAccount.setUsername(newAccount);

        if(gameAccounts == null){                                     //first ever account link
            HashMap<String, GameAccount> newAccounts = new HashMap<>();
            newAccounts.put(newGame, gameAccount);
            gameAccounts = newAccounts;
        }
        else if(!gameAccounts.containsKey(newGame)){                  //first account link to this game
            gameAccounts.put(newGame, gameAccount);
        }

        user.setGameAccounts(gameAccounts);

//        System.out.println("Before:" + userModel.getAccounts());
        UserModel userModel = new UserModel(user);                            //set user accounts to updated account list
//        System.out.println("After:" + userModel.getAccounts());
        userRepository.save(userModel);

        return userModel;
    }

    @Override
    public UserModel unlinkGameAccount(UserDto user) {      //todo
        HashMap<String, GameAccount> gameAccounts = user.getGameAccounts();
        UserModel userModel = new UserModel(user);
        gameAccounts.remove(user.getTempAccount());
        userModel.setAccounts(gameAccounts);

        return userModel;
    }

    @Override
    public void changeBio(String bio) {

    }

    @Override
    public boolean checkIfExists(String newGame, String newAccount, String newRegion, UserDto user) {
        GameAccount gameAccount = new GameAccount();
        gameAccount.setRegion(newRegion);
        gameAccount.setUsername(newAccount);
        if(user.getGameAccounts() != null)
            if(user.getGameAccounts().containsKey(newGame))
                if(user.getGameAccounts().containsValue(gameAccount))
                    return true;

        return false;
    }
}
