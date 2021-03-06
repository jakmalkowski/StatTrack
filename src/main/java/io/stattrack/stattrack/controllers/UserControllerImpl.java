package io.stattrack.stattrack.controllers;
import io.stattrack.stattrack.dto.RecentGames;
import io.stattrack.stattrack.dto.SettingsContainer;
import io.stattrack.stattrack.dto.UserDto;
import io.stattrack.stattrack.models.UserModel;
import io.stattrack.stattrack.models.UserRepository;
import io.stattrack.stattrack.services.SessionService;
import io.stattrack.stattrack.services.UserService;
import io.stattrack.stattrack.services.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class UserControllerImpl implements UserController {

  @Autowired
    UserRepository userRepository;
    public UserControllerImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value="/linkaccount")
    public String linkAccountPage(Model model, HttpSession session) {

        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "linkaccount";
    }


    @Override
    @RequestMapping(value = "/linkaccount", method = RequestMethod.POST)
    public String linkAccountFunc(@ModelAttribute("user") UserDto user, HttpSession session) {

        UserServiceImpl userService = new UserServiceImpl(userRepository);

        if (userService.checkIfExists(user.getTempGame(), user.getTempAccount(), (UserDto) session.getAttribute("user")))
            return "linkaccount_fail";

        userService.linkGameAccount(user.getTempGame(), user.getTempAccount(), user.getTempRegion(), (UserDto) session.getAttribute("user"));

        return "homepage";

    }

    @GetMapping(value="/unlinkaccount")
    public String unlinkAccountPage(Model model, HttpSession session) {

        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "unlinkaccount";
    }

    @Override
    @RequestMapping(value = "/unlinkaccount", method = RequestMethod.POST)
    public String unlinkAccountFunc(@ModelAttribute("user") UserDto user, HttpSession session){

        UserServiceImpl userService = new UserServiceImpl(userRepository);

        if (!userService.checkIfExists(user.getTempGame(), user.getTempAccount(), (UserDto) session.getAttribute("user")))
            return "unlinkaccount_fail";

        userService.unlinkGameAccount(user.getTempGame(), user.getTempAccount(), (UserDto) session.getAttribute("user"));
        return "unlinkaccount_success";


    }

    @Override
    public void createUser(String login, String password, String email) {

    }

    @Override
    public void logIn(String login, String password) {

    }

    @Override
    public void logOut(){

    }

    @Override
    @GetMapping(value="/{uname}")
    public String userPage(@PathVariable String uname, Model userModel, HttpSession session) {
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        UserDto user = userService.getUserFromUname(uname);
        if (user == null) return "userNotFound";
        Map<String, String> stats = new HashMap<>();
        RecentGames recentGames = user.getLast30Games();
        if (recentGames != null) {
            for (String stat : user.getDisplayStatistics()) {
                if (stat.equals("KDA")) stats.put("KDA", String.valueOf(recentGames.getKDA()));
                if (stat.equals("avgKills")) stats.put("avgKills", String.valueOf(recentGames.getAvgKills()));
                if (stat.equals("avgDeaths")) stats.put("avgDeaths", String.valueOf(recentGames.getAvgDeaths()));
                if (stat.equals("avgAssists")) stats.put("avgAssists", String.valueOf(recentGames.getAvgAssists()));
                if (stat.equals("avgVisionScore")) stats.put("avgVisionScore", String.valueOf(recentGames.getAvgVisionScore()));
                if (stat.equals("avgDamageToChampions")) stats.put("avgDamageToChampions", String.valueOf(recentGames.getAvgDamageToChampions()));
                if (stat.equals("avgHealing")) stats.put("avgHealing", String.valueOf(recentGames.getAvgHealing()));
                if (stat.equals("mostPopularRole")) stats.put("mostPopularRole", String.valueOf(recentGames.getMostPopularRole()));
                if (stat.equals("mostPopularChampion")) stats.put("mostPopularChampion", String.valueOf(recentGames.getMostPopularChampion()));
                if (stat.equals("winrate")) stats.put("winrate", String.valueOf(recentGames.getWinrate()));
            }
            userModel.addAttribute("message", "");
        } else {
            userModel.addAttribute("message", "Update your stats!");
        }
        userModel.addAttribute("uname", uname);
        userModel.addAttribute("data", stats);
        SessionService sessionService = new SessionService(session);
        UserDto loggedUser = sessionService.getUserDto();
        if (loggedUser != null && loggedUser.getUname().equals(uname)) {
            userModel.addAttribute("currentUser", true);
        } else {
            userModel.addAttribute("currentUser", false);
        }

        return "userPage";
    }

    @Override
    @GetMapping(value = "/settings")
    public String settings(Model model, HttpSession session) {
        if(!isUserLogged(session)){
            return "redirect:/login";
        }
        SessionService sessionService = new SessionService(session);
        UserDto user = sessionService.getUserDto();
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        ArrayList<String> currentStats = userService.getUserStatistics(user);
        SettingsContainer container = new SettingsContainer();
        Field[] fields = container.getClass().getFields();
        if(currentStats!= null) {
            for (Field f : fields) {
                if (currentStats.contains(f.getName())) {
                    container.changeValue(f.getName());
                }
            }
        }
        model.addAttribute("container",container);
        model.addAttribute("user",user);
        return "settings";
    }
    @PostMapping(value = "/settings")
    public String changeSettings(@ModelAttribute("user") UserDto user ,@ModelAttribute("container") SettingsContainer container, HttpSession session) throws IllegalAccessException {
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        ArrayList<String> updatedStats = new ArrayList<>();
        Field[] fields = container.getClass().getFields();
        for(Field s : fields){
            if(s.getBoolean(container)){
                updatedStats.add(s.getName());
            }
        }
        userService.updateDisplayed((UserDto) session.getAttribute("user"),updatedStats);
        return "settings";
    }
    @GetMapping(value = "/update")
    public String update(Model model,HttpSession session){
        if(!isUserLogged(session)){
            return "redirect:/login";
        }
        SessionService sessionService = new SessionService(session);
        UserDto user = sessionService.getUserDto();
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        if(userService.getUserGameAccounts(user)==null)
        {
            return "redirect:/linkaccount";
        }
        userService.updateStats(user);
        return "redirect:/" + user.getUname();
    }
    @Override
    public void mainPage() {

    }
    private boolean isUserLogged(HttpSession httpSession){
        SessionService sessionService = new SessionService(httpSession);
        return sessionService.getUserDto() != null;
    }
}
