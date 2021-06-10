package io.stattrack.stattrack.models;

import io.stattrack.stattrack.dto.PlayerStats;
import io.stattrack.stattrack.RiotApiHandler;
import io.stattrack.stattrack.dto.GameAccount;
import io.stattrack.stattrack.dto.RecentGames;
import io.stattrack.stattrack.dto.UserDto;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
//Our main userModel that serves as a template for MongoDB documents
public class UserModel {

    @Id
    String id;
    String uname;
    String password;
    String email;
    RecentGames last30Games;
    HashMap<String, GameAccount> accounts;
    Instant accCreationInstant = Instant.now();
    ArrayList<String> displayStatistics;
    Instant updateInstant;
    HashMap<String, PlayerStats> statistics;
    String bio;

    public UserModel() {

    }

    public UserModel(String uname, String password, String email) {
//        super();
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.displayStatistics = new ArrayList<String>();
        displayStatistics.add("KDA");
    }

    public UserModel(UserDto userDto) {
//        super();
        this.id = userDto.getId();
        this.uname = userDto.getUname();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.accounts = userDto.getGameAccounts();
        this.displayStatistics = userDto.getDisplayStatistics();
        if(displayStatistics==null){
            displayStatistics = new ArrayList<String>();
            displayStatistics.add("KDA");
        }
    }

    public RecentGames getLast30Games() {
        return last30Games;
    }

    public void setLast30Games(RecentGames last30Games) {
        this.last30Games = last30Games;
    }
    //Basic function updating our Database and data available for display
    public void updateLoLStatistics(){
        if(this.statistics==null){
            this.statistics=new HashMap<>();
        }
        RiotApiHandler riotApiHandler = RiotApiHandler.getInstance();
        if(accounts.get("LeagueOfLegends")==null){
            return;
        }
        this.updateInstant = Instant.now();
        GameAccount acc = accounts.get("LeagueOfLegends");
        ArrayList<String> last20matches = riotApiHandler.getMatchlist(acc.getUsername(),acc.getRegion());
        for(String s : last20matches){
            if(!statistics.containsKey(s)){
                statistics.put(s,riotApiHandler.getPlayerMatchStats(acc.getUsername(),s,acc.getRegion()));
            }
        }
        float avgKills=0;
        float avgDeaths=0;
        float avgAssists=0;
        float avgVisionScore=0;
        float avgDamageToChampions=0;
        float avgHealing=0;
        ArrayList<String> mostPopularRole = new ArrayList<>();
        ArrayList<String> mostPopularChampion=new ArrayList<>();
        float winrate=0;
        for(Map.Entry<String,PlayerStats> s : statistics.entrySet()){
            PlayerStats temp = s.getValue();
            avgKills+=temp.getKills();
            avgDeaths+=temp.getDeaths();
            avgAssists+=temp.getAssists();
            avgVisionScore+=temp.getVisionScore();
            avgDamageToChampions+=temp.getMagicDamageDealtToChampions()
                    +temp.getTrueDamageDealtToChampions()+temp.getPhysicalDamageDealtToChapions();
            avgHealing+=temp.getTotalHealsOnTeammates();
            mostPopularRole.add(temp.getChampionName());
            mostPopularChampion.add((temp.getChampionName()));
            if(temp.getWin())
                winrate++;
        }
        if(this.last30Games==null){
            this.last30Games = new RecentGames();
        }
        this.last30Games.setAvgKills(avgKills/30);
        this.last30Games.setAvgAssists(avgAssists/30);
        this.last30Games.setAvgDeaths(avgDeaths/30);
        this.last30Games.setAvgHealing(avgHealing/30);
        this.last30Games.setAvgDamageToChampions(avgDamageToChampions/30);
        this.last30Games.setAvgVisionScore(avgVisionScore/30);
        this.last30Games.setMostPopularChampion(mostPopular(mostPopularChampion));
        this.last30Games.setMostPopularRole(mostPopular(mostPopularRole));
        this.last30Games.setKDA((avgKills+avgAssists/avgDeaths)/30);
        this.last30Games.setWinrate(winrate/30);
    }
    //selfExplanatory
    String mostPopular(ArrayList<String> list){
        Map<String,Integer> map = new HashMap<>();
        for(String s : list){
            Integer val = map.get(s);
            map.put(s,val == null ? 1 : val + 1);
        }
        Map.Entry<String,Integer> max = null;
        for(Map.Entry<String,Integer> et: map.entrySet()){
            if(max == null || et.getValue() > max.getValue()){
                max = et;
            }
        }
        assert max != null;
        return max.getKey();
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

    public HashMap<String, GameAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, GameAccount> accounts) {
        this.accounts = accounts;
    }

    public Instant getAccCreationInstant() {
        return accCreationInstant;
    }

    public void setAccCreationInstant(Instant accCreationInstant) {
        this.accCreationInstant = accCreationInstant;
    }

    public ArrayList<String> getDisplayStatistics() {
        return displayStatistics;
    }

    public void setDisplayStatistics(ArrayList<String> displayStatistics) {
        this.displayStatistics = displayStatistics;
    }

    public Instant getUpdateInstant() {
        return updateInstant;
    }

    public void setUpdateInstant(Instant updateInstant) {
        this.updateInstant = updateInstant;
    }

    public HashMap<String, PlayerStats> getStatistics() {
        return statistics;
    }

    public void setStatistics(HashMap<String, PlayerStats> statistics) {
        this.statistics = statistics;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


}
