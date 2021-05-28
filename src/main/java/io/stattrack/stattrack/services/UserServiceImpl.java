package io.stattrack.stattrack.services;

import io.stattrack.stattrack.dto.GameAccount;
import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;

import io.stattrack.stattrack.models.UserRepository;
import org.apache.catalina.User;

import java.util.*;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserModel updateStats(UserDto user) {
        UserModel userModel = new UserModel(user);
        userModel.updateLoLStatistics();
        userRepository.save(userModel);
        return userModel;
    }

    public UserModel updateDisplayed(UserDto user,ArrayList<String> upDisplayed){
        ArrayList<String> displayed = new ArrayList<>(upDisplayed);
        user.setDisplayStatistics(displayed);
        UserModel userModel = new UserModel(user);
        userRepository.save(userModel);
        return userModel;
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
    public UserModel unlinkGameAccount(String newGame, String newAccount, UserDto user) {
        HashMap<String, GameAccount> gameAccounts = user.getGameAccounts();
        gameAccounts.remove(newGame);
        user.setGameAccounts(gameAccounts);

        UserModel userModel = new UserModel(user);
        userRepository.save(userModel);

        return userModel;
    }

    @Override
    public void changeBio(String bio) {

    }
    public ArrayList<String> getUserStatistics(UserDto userDto){
        UserModel user = userRepository.findByEmail(userDto.getEmail()).get(0);
        return user.getDisplayStatistics();
    }
    public HashMap<String,GameAccount> getUserGameAccounts(UserDto userDto){
        UserModel user = userRepository.findByEmail(userDto.getEmail()).get(0);
        return user.getAccounts();
    }
    @Override
    public boolean checkIfExists(String newGame, String newAccount, UserDto user) {
        if(user.getGameAccounts() != null)
            if(user.getGameAccounts().containsKey(newGame))
                if(user.getGameAccounts().get(newGame).getUsername().equals(newAccount))
                    return true;

        return false;
    }

    public UserDto getUserFromUname(String uname) {
        List<UserModel> userList = userRepository.findByUname(uname);
        if (userList.isEmpty()) return null;
        return new UserDto(userList.get(0));
    }
}
