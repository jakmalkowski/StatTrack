package io.stattrack.stattrack.dto;

import io.stattrack.stattrack.models.UserModel;

import java.util.HashMap;
import java.util.List;

public class UserDto {
    private String id;
    private String uname;
    private String password;
    private String passwordRepeat;
    private String email;
    private String tempGame;
    private String tempAccount;
    private HashMap<String, List<String>> gameAccounts;



    public UserDto(UserModel userModel) {
        this.id = userModel.getId();
        this.uname = userModel.getUname();
        this.password = userModel.getPassword();
        this.email = userModel.getEmail();
        this.gameAccounts = userModel.getAccounts();
    }

    public UserDto() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, List<String>> getGameAccounts() { return gameAccounts; }

    public void setGameAccounts(HashMap<String, List<String>> gameAccounts) { this.gameAccounts = gameAccounts; }

//    public void appendGameAccount(String game, String account){ this.gameAccounts.put(game, this.gameAccounts.get(game))}

    public String getPasswordRepeat() { return passwordRepeat; }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTempGame() {
        return tempGame;
    }

    public void setTempGame(String tempGame) {
        this.tempGame = tempGame;
    }

    public String getTempAccount() {
        return tempAccount;
    }

    public void setTempAccount(String tempAccount) {
        this.tempAccount = tempAccount;
    }

    public List<String> getAccount(String game){return this.gameAccounts.get(game); }
}
