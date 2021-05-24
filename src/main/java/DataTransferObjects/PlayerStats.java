package DataTransferObjects;


import com.google.gson.internal.LinkedTreeMap;

public class PlayerStats {
    public String summonerName;
    public String puuID;
    public String position;
    public String championName;
    public Boolean win;
    //Basic stats
    public Double assists;
    public Double kills;
    public Double deaths;
    public Double goldEarned;
    public Double wardsPlaced;
    public Double visionScore;
    public Double controlWards;
    public Double wardsDestroyed;
    //Multikill stats
    public Double doubleKills;
    public Double trippleKills;
    public Double quadraKills;
    public Double pentaKills;
    //Damage profile
    public Double physicalDamageDealtToChapions;
    public Double trueDamageDealtToChampions;
    public Double magicDamageDealtToChampions;
    public Double totalHeal;
    public Double totalHealsOnTeammates;

    PlayerStats(LinkedTreeMap<String,Object> data){
        //Setting Strings
        this.summonerName=(String)data.get("summonerName");
        this.puuID=(String)data.get("puuid");
        this.position=(String)data.get("individualPosition");
        this.championName=(String)data.get("championName");
        this.win=(Boolean)data.get("win");
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
