package io.stattrack.stattrack.models;

import DataTransferObjects.PlayerStats;
import io.stattrack.stattrack.RiotApiHandler;
import io.stattrack.stattrack.dto.GameAccount;
import io.stattrack.stattrack.dto.RecentGames;
import io.stattrack.stattrack.dto.UserDto;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserModel {

    @Id
    String id;
    String uname;
    String password;
    String email;
    RecentGames last30Games;
    HashMap<String, GameAccount> accounts;
    Instant accCreationInstant = Instant.now();
    HashMap<String, ArrayList<String>> displayStatistics;
    Instant updateInstant;
    HashMap<String, PlayerStats> statistics;
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

    public RecentGames getLast30Games() {
        return last30Games;
    }

    public void setLast30Games(RecentGames last30Games) {
        this.last30Games = last30Games;
    }

    public void updateLoLStatistics(){
        RiotApiHandler riotApiHandler = RiotApiHandler.getInstance();
        if(accounts.get("LeagueOfLegends")==null){
            return;
        }
        this.updateInstant = Instant.now();
        GameAccount acc = accounts.get("League of Legends");
        ArrayList<String> last20matches = riotApiHandler.getMatchlist(acc.getUsername(),acc.getRegion());
        for(String s : last20matches){
            if(!statistics.containsKey(s)){
                statistics.put(s,riotApiHandler.getPlayerMatchStats(acc.getUsername(),s,acc.getRegion()));
            }
        }
        double avgKills=0;
        double avgDeaths=0;
        double avgAssists=0;
        double avgVisionScore=0;
        double avgDamageToChampions=0;
        double avgHealing=0;
        ArrayList<String> mostPopularRole = new ArrayList<>();
        ArrayList<String> mostPopularChampion=new ArrayList<>();
        double winrate=0;
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
        this.last30Games.setKDA((avgKills+avgAssists/avgDeaths));
        this.last30Games.setWinrate(winrate/30);
    }
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
