package DataTransferObjects;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.util.*;

/*
Match{
zawiera Info i Metadate, + jakieś pierdoły
    Info{
    zawiera : gówno + participants
        participants zawiera :
            gówno + perks
                perks zawiera :
                selections
    }
}
* */
public class LeagueMatch {
    LinkedTreeMap<String,Object> metaData;
    List<String> participantsID= Collections.emptyList();
    HashMap<String,PlayerStats> blueSideTeam;
    HashMap<String,PlayerStats> redSideTeam;
    public LeagueMatch(String matchDetails){
        Gson gson = new Gson();
        Map<String,Object> entiriety =  gson.fromJson(matchDetails,new TypeToken<Map<String,?>>(){}.getType());
        this.metaData = (LinkedTreeMap<String,Object>) entiriety.get("metadata");
        List<String> participantsID = (List<String>) metaData.get("participants");
        LinkedTreeMap<String,Object> info = (LinkedTreeMap<String, Object>) entiriety.get("info");
        ArrayList<LinkedTreeMap<String,Object>> participantStats = (ArrayList<LinkedTreeMap<String,Object>>) info.get("participants");
        blueSideTeam = new HashMap<String ,PlayerStats>();
        redSideTeam = new HashMap<String ,PlayerStats>();
        for(int i=0;i<5;i++){
            blueSideTeam.put(participantsID.get(i),new PlayerStats(participantStats.get(i)));
        }
        for(int i=0;i<10;i++){
            redSideTeam.put(participantsID.get(i),new PlayerStats(participantStats.get(i)));
        }
    }
}
