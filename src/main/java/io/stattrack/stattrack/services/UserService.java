package io.stattrack.stattrack.services;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserService {

    public void updateStats();

    public void setDisplayed(HashMap<String, HashMap<String, ?>> args);

    public void addToDisplayed(String gameName, ArrayList<String> arrayToAdd);

    public void deleteFromDisplayed(String gameName, ArrayList<String> arrayToDelete);

    public void setAccounts(HashMap<String, String> accounts);

    public void linkAccount (String gameName, String account);

    public void changeBio(String bio);
}
