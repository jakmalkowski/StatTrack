//package io.stattrack.stattrack;
//
//import io.stattrack.stattrack.controllers.UserController;
//import io.stattrack.stattrack.controllers.UserControllerImpl;
//import io.stattrack.stattrack.dto.UserDto;
//import io.stattrack.stattrack.models.UserModel;
//import io.stattrack.stattrack.models.UserRepository;
//import io.stattrack.stattrack.services.UserService;
//import io.stattrack.stattrack.services.UserServiceImpl;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class UserControllerTests {
//
//    private UserModel userModel;
//    private UserService userService;
//    private UserController userController;
//    private UserDto userDto;
//    UserRepository userRepository;
//    HttpSession userSession;
//    //    private UserView UserView;
//
//    @Before
//    public void init() {
//        this.userModel = new UserModel("uname", "password", "email");
//        userModel.setId("test");
//        this.userService = new UserServiceImpl(userRepository);
//        this.userDto = new UserDto(userModel);
//        userSession =
//
//
//    }
//
//
//    @Test
//    public void linkLOLAccountKeyTrue() {
//        String game = "LOL";
//        String account = "Amon Gus";
//
//        userController.linkAccountFunc(game, account);
//
//        Assert.assertTrue(this.userModel.getAccounts().containsKey(game));          //Check if correct key was inserted
//    }
//
//    @Test
//    public void linkLOLAccountKeyFalse() {
//        String game1 = "CSGO", game2 = "LOL";
//        String account1 = "DoomSlayer420", account2 = "Anomaly";
//
//        userController.linkAccount(game1, account1);
//
//        Assert.assertFalse(this.userModel.getAccounts().containsKey(game2));          //Check if wrong key was inserted
//    }
//
//    @Test
//    public void linkLOLAccountValue() {
//        String game = "LOL";
//        String account = "Amon Gus";
//
//        userController.linkAccount(game, account);
//
//        Assert.assertTrue(this.userModel.getAccounts().containsValue(account));     //Check if value was inserted
//    }
//
//    @Test
//    public void linkLOLAccountMapping() {
//        HashMap<String, String> accounts = new HashMap<>();
//        String game = "LOL";
//        String account = "Amon Gus";
//
//        accounts.put(game, account);
//        userController.linkAccount(game, account);
//
//        Assert.assertEquals(accounts.get(game), this.userModel.getAccounts().get(game));   //Check if mapping is correct
//    }
//
//    @Test
//    public void linkLOLAccountMappingMultiple1() {
//        HashMap<String, String> accounts = new HashMap<>();
//        String game1 = "CSGO", game2 = "LOL";
//        String account1 = "DoomSlayer420", account2 = "Anomaly";
//
//        accounts.put(game1, account1);
//        accounts.put(game2, account2);
//        userController.linkAccount(game1, account1);
//        userController.linkAccount(game2, account2);
//
//        Assert.assertEquals(accounts.get(game2), this.userModel.getAccounts().get(game2));   //Check if mapping is correct
//    }
//
//    @Test
//    public void linkLOLAccountMappingMultiple2() {
//        HashMap<String, String> accounts = new HashMap<>();
//        String game1 = "CSGO", game2 = "LOL";
//        String account1 = "DoomSlayer420", account2 = "Anomaly";
//
//        accounts.put(game1, account1);
//        accounts.put(game2, account2);
//        userController.linkAccount(game1, account1);
//        userController.linkAccount(game2, account2);
//
//        Assert.assertNotEquals(accounts.get(game2), this.userModel.getAccounts().get(game1));   //Check if mapping is wrong
//    }
//
//    @Test
//    public void unlinkAccountKeyDeleteCSGO() {
//        String game1 = "CSGO", game2 = "LOL";
//        String account1 = "DoomSlayer420", account2 = "Anomaly";
//
//        userController.linkAccount(game1, account1);
//        userController.linkAccount(game2, account2);
//
//        userController.unlinkAccount(game1, account1);
//
//        Assert.assertFalse(this.userModel.getAccounts().containsKey(game1));                //Check if key was deleted
//    }
//
//    @Test
//    public void unlinkAccountKeyDeleteLOL() {
//        String game1 = "CSGO", game2 = "LOL";
//        String account1 = "DoomSlayer420", account2 = "Anomaly";
//
//        userController.linkAccount(game1, account1);
//        userController.linkAccount(game2, account2);
//
//        userController.unlinkAccount(game2, account2);
//
//        Assert.assertFalse(this.userModel.getAccounts().containsKey(game2));                //Check if key was deleted
//    }
//
//    @Test
//    public void unlinkAccountMappingCSGO() {
//        String game1 = "CSGO", game2 = "LOL";
//        String account1 = "DoomSlayer420", account2 = "Anomaly";
//        HashMap<String, String> accounts = new HashMap<>();
//
//        accounts.put(game1, account1);
//        accounts.put(game2, account2);
//        userController.linkAccount(game1, account1);
//        userController.linkAccount(game2, account2);
//
//        userController.unlinkAccount(game2, account2);
//        accounts.remove(game2);
//
//        Assert.assertEquals(accounts.get(game1), this.userModel.getAccounts().get(game1)); //Check if other mapping is correct
//    }
//
//    @Test
//    public void unlinkAccountMappingLOL() {
//        String game1 = "CSGO", game2 = "LOL";
//        String account1 = "DoomSlayer420", account2 = "Anomaly";
//        HashMap<String, String> accounts = new HashMap<>();
//
//        accounts.put(game1, account1);
//        accounts.put(game2, account2);
//        userController.linkAccount(game1, account1);
//        userController.linkAccount(game2, account2);
//
//        userController.unlinkAccount(game1, account1);
//        accounts.remove(game1);
//
//        Assert.assertEquals(accounts.get(game2), this.userModel.getAccounts().get(game2)); //Check if other mapping is correct
//    }
//
////    @Test
////    public void createUser() {
////        UserModel newUser = new UserModel();
////        UUID id = randomUUID();
////
////        String login = "Adam", password = "Apple", email = "adam.apple@yahoo.com";
////        newUser.setId(id);
////        newUser.setUname(login);        //todo: Verify whether Uname == login?
////        newUser.setPassword(password);
////        newUser.setEmail(email);
////
////        Assert.assertEquals(newUser.getId(), id);                 //Assert id
////        Assert.assertEquals(newUser.getUname(), login);           //Assert login
////        Assert.assertEquals(newUser.getPassword(), password);     //Assert password
////        Assert.assertEquals(newUser.getEmail(), email);           //Assert email
////    }
//
//    @Test
//    public void logIn() {
//        //session based test
//    }
//
//    @Test
//    public void logOut(){
//        //session based test
//    }
//
//}
