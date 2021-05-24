package io.stattrack.stattrack.models;

import io.stattrack.stattrack.dto.UserDto;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

public class UserModel {

    @Id
    String id;
    String uname;
    String password;
    String email;
    HashMap<String, String> accounts;
    Instant accCreationInstant;
    HashMap<String, ArrayList<String>> displayStatistics;
    Instant updateInstant;
    HashMap<String, HashMap<String, ?>> statistics;
    String bio;

    public UserModel() {}

    public UserModel(String uname, String password, String email) {
//        super();
        this.uname = uname;
        this.password = password;
        this.email = email;
    }

    public UserModel(UserDto userDto) {
//        super();
        this.uname = userDto.getUname();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, String> accounts) {
        this.accounts = accounts;
    }

    public Instant getAccCreationInstant() {
        return accCreationInstant;
    }

    public void setAccCreationInstant(Instant accCreationInstant) {
        this.accCreationInstant = accCreationInstant;
    }

    public HashMap<String, ArrayList<String>> getDisplayStatistics() {
        return displayStatistics;
    }

    public void setDisplayStatistics(HashMap<String, ArrayList<String>> displayStatistics) {
        this.displayStatistics = displayStatistics;
    }

    public Instant getUpdateInstant() {
        return updateInstant;
    }

    public void setUpdateInstant(Instant updateInstant) {
        this.updateInstant = updateInstant;
    }

    public HashMap<String, HashMap<String, ?>> getStatistics() {
        return statistics;
    }

    public void setStatistics(HashMap<String, HashMap<String, ?>> statistics) {
        this.statistics = statistics;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
