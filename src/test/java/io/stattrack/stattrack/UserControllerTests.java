package io.stattrack.stattrack;

import controllers.UserController;
import controllers.UserControllerImpl;
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
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTests {

    private UserModel userModel;
    private UserService userService;
    private UserController userController;
    //    private UserView UserView;            //todo: how to fetch UserView?

    @Before
    public void init() {
        this.userModel = new UserModel();
        this.userService = new UserServiceImpl(userModel);      //fixme: how to get correct userModel?
        this.userController = new UserControllerImpl(userService);
    }

    @Test
    public void linkCSGOAccountKey() {
        HashMap<String, String> accounts = new HashMap<>();
        String game = "CSGO";
        String account = "DoomSlayer420";

        accounts.put(game, account);
        userController.linkAccount(game, account);

        Assert.assertTrue(this.userModel.getAccounts().containsKey(game));          //Check if key was inserted
    }

    @Test
    public void linkCSGOAccountValue() {
        HashMap<String, String> accounts = new HashMap<>();
        String game = "CSGO";
        String account = "DoomSlayer420";

        accounts.put(game, account);
        userController.linkAccount(game, account);

        Assert.assertTrue(this.userModel.getAccounts().containsValue(account));     //Check if value was inserted
    }

    @Test
    public void linkCSGOAccountMapping() {
        HashMap<String, String> accounts = new HashMap<>();
        String game = "CSGO";
        String account = "DoomSlayer420";

        accounts.put(game, account);
        userController.linkAccount(game, account);

        Assert.assertEquals(accounts.get(game), this.userModel.getAccounts().get(game));   //Check if mapping is correct
    }

    @Test
    public void linkLOLAccountKey() {
        HashMap<String, String> accounts = new HashMap<>();
        String game = "LOL";
        String account = "Amon Gus";

        accounts.put(game, account);
        userController.linkAccount(game, account);

        Assert.assertTrue(this.userModel.getAccounts().containsKey(game));          //Check if key was inserted
    }

    @Test
    public void linkLOLAccountValue() {
        HashMap<String, String> accounts = new HashMap<>();
        String game = "LOL";
        String account = "Amon Gus";

        accounts.put(game, account);
        userController.linkAccount(game, account);

        Assert.assertTrue(this.userModel.getAccounts().containsValue(account));     //Check if value was inserted
    }

    @Test
    public void linkLOLAccountMapping() {
        HashMap<String, String> accounts = new HashMap<>();
        String game = "LOL";
        String account = "Amon Gus";

        accounts.put(game, account);
        userController.linkAccount(game, account);

        Assert.assertEquals(accounts.get(game), this.userModel.getAccounts().get(game));   //Check if mapping is correct
    }

    @Test
    public void unlinkAccountKeyDelete() {
        String game1 = "CSGO", game2 = "LOL";
        String account1 = "DoomSlayer420", account2 = "Anomaly";

        userController.linkAccount(game1, account1);
        userController.linkAccount(game2, account2);

        userController.unlinkAccount(game1, account1);

        Assert.assertFalse(this.userModel.getAccounts().containsKey(game1));                //Check if key was deleted
    }

    @Test
    public void unlinkAccountMapping() {
        String game1 = "CSGO", game2 = "LOL";
        String account1 = "DoomSlayer420", account2 = "Anomaly";
        HashMap<String, String> accounts = new HashMap<>();

        accounts.put(game1, account1);
        accounts.put(game2, account2);
        userController.linkAccount(game1, account1);
        userController.linkAccount(game2, account2);

        userController.unlinkAccount(game1, account1);
        accounts.remove(game1);

        Assert.assertEquals(accounts.get(game2), this.userModel.getAccounts().get(game2)); //Check if other mapping is correct
    }

    @Test
    public void createUser() {
        UserModel newUser = new UserModel();
        UUID id = randomUUID();

        String login = "Adam", password = "Apple", email = "adam.apple@yahoo.com";
        newUser.setId(id);
        newUser.setUname(login);        //todo: Verify whether Uname == login?
        newUser.setPassword(password);
        newUser.setEmail(email);

        Assert.assertEquals(newUser.getId(), id);                 //Assert id
        Assert.assertEquals(newUser.getUname(), login);           //Assert login
        Assert.assertEquals(newUser.getPassword(), password);     //Assert password
        Assert.assertEquals(newUser.getEmail(), email);           //Assert email
    }

    @Test
    public void logIn() {
            //session based test
    }

    @Test
    public void logOut(){
            //session based test
    }

}
