package DataTransferObjects;

import com.google.gson.internal.LinkedTreeMap;

public class PlayerStats {
    String summonerName;
    String puuID;
    String position;
    String championName;
    boolean win;
    //Basic stats
    Double assists;
    Double kills;
    Double deaths;
    Double goldEarned;
    Double wardsPlaced;
    Double visionScore;
    Double controlWards;
    Double wardsDestroyed;
    //Multikill stats
    Double doubleKills;
    Double trippleKills;
    Double quadraKills;
    Double pentaKills;
    //Damage profile
    Double physicalDamageDealtToChapions;
    Double trueDamageDealtToChampions;
    Double magicDamageDealtToChampions;
    Double totalHeal;
    Double totalHealsOnTeammates;

    PlayerStats(LinkedTreeMap<String,Object> data){
        //Setting Strings
        this.summonerName=(String)data.get("summonerName");
        this.puuID=(String)data.get("puuid");
        this.position=(String)data.get("individualPosition");
        this.championName=(String)data.get("championName");
        //Setting doubles
        this.assists=(Double) data.get("assists");
        this.kills=(Double) data.get("kills");
        this.deaths=(Double) data.get("deaths");
        this.goldEarned=(Double) data.get("goldEarned");
        this.wardsPlaced=(Double) data.get("wardsPlaced");
        this.visionScore=(Double) data.get("visionScore");
        this.controlWards=(Double) data.get("visionWardsBoughtInGame");
        this.wardsDestroyed=(Double) data.get("wardsKilled");
        //Setting Multikills
        this.doubleKills=(Double) data.get("doubleKills");
        this.trippleKills=(Double) data.get("tripleKills");
        this.quadraKills=(Double) data.get("quadraKills");
        this.pentaKills=(Double) data.get("pentaKills");
        //Damage Profiles
        this.trueDamageDealtToChampions=(Double) data.get("trueDamageDealtToChampions");
        this.magicDamageDealtToChampions=(Double) data.get("magicDamageDealtToChampions");
        this.physicalDamageDealtToChapions=(Double) data.get("physicalDamageDealtToChampions");
        this.totalHeal=(Double) data.get("totalHeal");
        this.totalHealsOnTeammates=(Double) data.get("totalHealsOnTeammates");
    }
}
