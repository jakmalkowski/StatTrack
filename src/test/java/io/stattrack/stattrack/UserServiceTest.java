package io.stattrack.stattrack;

import models.UserModel;
import models.UserService;
import models.UserServiceImpl;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Test
    public void setDisplayedCSGO() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "CSGO";

        ArrayList<String> elementsToAdd = new ArrayList<>();

        elementsToAdd.add("CSGO_kills");
        elementsToAdd.add("CSGO_wins");

        HashMap<String, ArrayList<String>> expected = new HashMap<>();
        expected.put(game, elementsToAdd);

        userService.setDisplayed(expected);

        Assert.assertEquals("Function should set", expected, userModel.getDisplayStatistics());
    }

    @Test
    public void setDisplayedLOL() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "LOL";

        ArrayList<String> elementsToAdd = new ArrayList<>();

        elementsToAdd.add("LOL_wins");

        HashMap<String, ArrayList<String>> expected = new HashMap<>();
        expected.put(game, elementsToAdd);

        userService.setDisplayed(expected);

        Assert.assertEquals("Function should set", expected, userModel.getDisplayStatistics());
    }

    @Test
    public void setDisplayedNull() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);


        HashMap<String, ArrayList<String>> expected = new HashMap<>();

        userService.setDisplayed(expected);

        Assert.assertNull("Function should set to null", userModel.getDisplayStatistics());
    }

    @Test
    public void addToDisplayedCSGO() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "CSGO";

        ArrayList<String> elementsToAdd = new ArrayList<>();

        userModel.setDisplayStatistics(new HashMap<String, ArrayList<String>>());

        elementsToAdd.add("CSGO_kills");
        elementsToAdd.add("CSGO_wins");
        userService.addToDisplayed(game, elementsToAdd);

        HashMap<String, ArrayList<String>> expected = new HashMap<>();
        expected.put(game, elementsToAdd);

        Assert.assertEquals("Function should add elements", expected, userModel.getDisplayStatistics());
    }

    @Test
    public void addToDisplayedNullCSGO() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "CSGO";

        ArrayList<String> elementsToAdd = new ArrayList<>();

        userModel.setDisplayStatistics(new HashMap<String, ArrayList<String>>());

        userService.addToDisplayed(game, elementsToAdd);

        Assert.assertNull("Function shouldn't add anything", userModel.getDisplayStatistics());
    }

    @Test
    public void addToDisplayedLOL() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "LOL";

        ArrayList<String> elementsToAdd = new ArrayList<>();

        userModel.setDisplayStatistics(new HashMap<String, ArrayList<String>>());

        elementsToAdd.add("LOL_wins");
        userService.addToDisplayed(game, elementsToAdd);

        HashMap<String, ArrayList<String>> expected = new HashMap<>();
        expected.put(game, elementsToAdd);

        Assert.assertEquals("Function shouldn't add anything", expected, userModel.getDisplayStatistics());
    }

    @Test
    public void addToDisplayedNullLOL() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "LOL";

        ArrayList<String> elementsToAdd = new ArrayList<>();

        userModel.setDisplayStatistics(new HashMap<String, ArrayList<String>>());

        userService.addToDisplayed(game, elementsToAdd);

        Assert.assertNull("Function shouldn't add anything", userModel.getDisplayStatistics());
    }


    @Test
    public void deleteFromDisplayedCSGO() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "CSGO";
        HashMap<String, ArrayList<String>> currentElements = userModel.getDisplayStatistics();

        ArrayList<String> CSGOStatistics = currentElements.get(game);

        ArrayList<String> elementsToAdd = new ArrayList<>();

        elementsToAdd.add("CSGO_kills");
        elementsToAdd.add("CSGO_wins");

        HashMap<String, ArrayList<String>> current = userModel.getDisplayStatistics();

        userModel.setDisplayStatistics(current);

        userService.deleteFromDisplayed(game, elementsToAdd);

        Assert.assertNull("Function should delete all elements", userModel.getDisplayStatistics());
    }

    @Test
    public void deleteNullFromDisplayedCSGO() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "CSGO";
        HashMap<String, ArrayList<String>> currentElements = userModel.getDisplayStatistics();

        ArrayList<String> CSGOStatistics = currentElements.get(game);

        ArrayList<String> elementsToAdd = new ArrayList<>();

        HashMap<String, ArrayList<String>> current = userModel.getDisplayStatistics();

        userModel.setDisplayStatistics(current);

        userService.deleteFromDisplayed(game, elementsToAdd);

        Assert.assertNull("Function shouldn't delete anything", userModel.getDisplayStatistics());
    }

    @Test
    public void deleteFromDisplayedLOL() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "LOL";
        HashMap<String, ArrayList<String>> currentElements = userModel.getDisplayStatistics();

        ArrayList<String> CSGOStatistics = currentElements.get(game);

        ArrayList<String> elementsToAdd = new ArrayList<>();

        elementsToAdd.add("LOL_wins");

        HashMap<String, ArrayList<String>> current = userModel.getDisplayStatistics();

        userModel.setDisplayStatistics(current);

        userService.deleteFromDisplayed(game, elementsToAdd);

        Assert.assertNull("Function should delete all elements", userModel.getDisplayStatistics());

    }

    @Test
    public void deleteNullFromDisplayedLOL() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String game = "LOL";
        HashMap<String, ArrayList<String>> currentElements = userModel.getDisplayStatistics();

        ArrayList<String> CSGOStatistics = currentElements.get(game);

        ArrayList<String> elementsToAdd = new ArrayList<>();

        HashMap<String, ArrayList<String>> current = userModel.getDisplayStatistics();

        userModel.setDisplayStatistics(current);

        userService.deleteFromDisplayed(game, elementsToAdd);

        Assert.assertNull("Function shouldn't delete anything", userModel.getDisplayStatistics());
    }

    @Test
    public void setAccountsToNullTest() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        HashMap<String, String> accountsToSet = new HashMap<>();

        userService.setAccounts(accountsToSet);

        Assert.assertNull("Accounts should be null", userModel.getAccounts());
    }

    @Test
    public void setAccountsTest() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        HashMap<String, String> accountsToSet = new HashMap<>();

        accountsToSet.put("CSGO", "Slayer420");
        accountsToSet.put("LOL", "xXxPussyDestroyer69xXx");

        userService.setAccounts(accountsToSet);

        Assert.assertEquals("Accounts didnt change", accountsToSet, userModel.getAccounts());
    }

    @Test
    public void linkAccountsTest() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        HashMap<String, String> accountsToSet = new HashMap<>();

        accountsToSet.put("CSGO", "Slayer420");
        accountsToSet.put("LOL", "xXxPussyDestroyer69xXx");

        userService.setAccounts(accountsToSet);

        userService.linkAccount("CSGO", "Czarny_Jezus");
        userService.linkAccount("LOL", "Czarny_Judasz");

        Assert.assertEquals("Accounts changed, when they shouldn't", accountsToSet, userModel.getAccounts());
    }

    @Test
    public void changeBioTest() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String oldBio = "Siema mordo";

        userModel.setBio(oldBio);

        String newBio = "Elo mordziaty";

        userService.changeBio(newBio);

        Assert.assertEquals("Bio didn't change", newBio, userModel.getBio());
    }

    @Test
    public void changeBioToNull() {
        UserModel userModel = new UserModel();
        UserService userService = new UserServiceImpl(userModel);

        String oldBio = "Siema mordo";

        userModel.setBio(oldBio);

        userService.changeBio(null);

        Assert.assertNull("Bio should be null", userModel.getBio());
    }

}
