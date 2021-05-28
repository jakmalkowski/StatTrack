package io.stattrack.stattrack.dto;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class LeagueMatch {
    public LinkedTreeMap<String,Object> metaData;
    public ArrayList<String> participantsID;
    public ArrayList<PlayerStats> playerStats;
    public LeagueMatch(String matchDetails){
        playerStats= new ArrayList<>();
        Gson gson = new Gson();
        Map<String,Object> entiriety =  gson.fromJson(matchDetails,new TypeToken<Map<String,?>>(){}.getType());
        this.metaData = (LinkedTreeMap<String,Object>) entiriety.get("metadata");
        this.participantsID = (ArrayList<String>) metaData.get("participants");
        LinkedTreeMap<String,Object> info = (LinkedTreeMap<String, Object>) entiriety.get("info");
        ArrayList<LinkedTreeMap<String,Object>> participantStats = (ArrayList<LinkedTreeMap<String,Object>>) info.get("participants");
        for(LinkedTreeMap<String ,Object> player : participantStats){
            playerStats.add(new PlayerStats(player));
        }
    }
}
