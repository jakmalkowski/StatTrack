package models;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

public class UserModel {
    UUID id;
    String uname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public HashMap<String, HashMap<String, ?>> getDisplayStatistics() {
        return displayStatistics;
    }

    public void setDisplayStatistics(HashMap<String, HashMap<String, ?>> displayStatistics) {
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

    String password;
    String email;
    HashMap<String, String> accounts;
    Instant accCreationInstant;
    HashMap<String, HashMap<String, ?>> displayStatistics;
    Instant updateInstant;
    HashMap<String, HashMap<String, ?>> statistics;
    String bio;
}
