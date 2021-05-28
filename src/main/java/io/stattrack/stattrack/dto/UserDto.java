package io.stattrack.stattrack.dto;

import io.stattrack.stattrack.models.UserModel;

import java.util.ArrayList;
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
    private String tempRegion;
    private HashMap<String, GameAccount> gameAccounts;
    private RecentGames last30Games;
    private ArrayList<String> displayStatistics;
    HashMap<String, PlayerStats> statistics;


    public UserDto(UserModel userModel) {
        this.id = userModel.getId();
        this.uname = userModel.getUname();
        this.password = userModel.getPassword();
        this.email = userModel.getEmail();
        this.gameAccounts = userModel.getAccounts();
        this.last30Games = userModel.getLast30Games();
        this.displayStatistics = userModel.getDisplayStatistics();
        this.statistics = userModel.getStatistics();

    }

    public UserDto() {

    }

    public ArrayList<String> getDisplayStatistics() {
        return displayStatistics;
    }

    public void setDisplayStatistics(ArrayList<String> displayStatistics) {
        this.displayStatistics = displayStatistics;
    }

    public RecentGames getLast30Games() {
        return last30Games;
    }

    public void setLast30Games(RecentGames last30Games) {
        this.last30Games = last30Games;
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

    public HashMap<String, GameAccount> getGameAccounts() { return gameAccounts; }

    public void setGameAccounts(HashMap<String, GameAccount> gameAccounts) { this.gameAccounts = gameAccounts; }

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

    public String getTempAccount() {return tempAccount; }

    public String getTempRegion() {return tempRegion; }

    public void setTempGame(String tempGame) {
        this.tempGame = tempGame;
    }

    public void setTempAccount(String tempAccount) {
        this.tempAccount = tempAccount;
    }

    public void setTempRegion(String tempRegion) {
        this.tempRegion = tempRegion;
    }

    public GameAccount getTempAccount(String game){return this.gameAccounts.get(game); }

}
