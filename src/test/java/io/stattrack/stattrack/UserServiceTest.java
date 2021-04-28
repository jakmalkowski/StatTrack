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

    private UserModel userModel;
    private UserService userService;
    @Before
    public void init() {
        this.userModel = new UserModel();
        this.userService = new UserServiceImpl(this.userModel);
    }

    @Test
    public void setDisplayed() {

    }

    @Test
    public void addToDisplayed() {
        String game = "CSGO";
        ArrayList<String> elementsToAdd = new ArrayList<>();

        elementsToAdd.add("CSGO_kills");
        elementsToAdd.add("CSGO_wins");
        userService.addToDisplayed(game, elementsToAdd);

        String combinedString = elementsToAdd.get(0) + elementsToAdd.get(1);
        HashMap<String, ArrayList<String>> expected = new HashMap<>();
        expected.put(game, elementsToAdd);

        Assert.assertEquals(expected, userModel.getDisplayStatistics());

    }
}
