package io.stattrack.stattrack.dto;


import com.google.gson.internal.LinkedTreeMap;
//Container for playerStats from a single match,selfexplanatory
public class PlayerStats {
    String summonerName;
    String puuID;
    String position;
    String championName;
    Boolean win;
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

    public PlayerStats(LinkedTreeMap<String, Object> data) {
        //Setting Strings
        this.summonerName = (String) data.get("summonerName");
        this.puuID = (String) data.get("puuid");
        this.position = (String) data.get("individualPosition");
        this.championName = (String) data.get("championName");
        this.win = (Boolean) data.get("win");
        //Setting doubles
        this.assists = (Double) data.get("assists");
        this.kills = (Double) data.get("kills");
        this.deaths = (Double) data.get("deaths");
        this.goldEarned = (Double) data.get("goldEarned");
        this.wardsPlaced = (Double) data.get("wardsPlaced");
        this.visionScore = (Double) data.get("visionScore");
        this.controlWards = (Double) data.get("visionWardsBoughtInGame");
        this.wardsDestroyed = (Double) data.get("wardsKilled");
        //Setting Multikills
        this.doubleKills = (Double) data.get("doubleKills");
        this.trippleKills = (Double) data.get("tripleKills");
        this.quadraKills = (Double) data.get("quadraKills");
        this.pentaKills = (Double) data.get("pentaKills");
        //Damage Profiles
        this.trueDamageDealtToChampions = (Double) data.get("trueDamageDealtToChampions");
        this.magicDamageDealtToChampions = (Double) data.get("magicDamageDealtToChampions");
        this.physicalDamageDealtToChapions = (Double) data.get("physicalDamageDealtToChampions");
        this.totalHeal = (Double) data.get("totalHeal");
        this.totalHealsOnTeammates = (Double) data.get("totalHealsOnTeammates");
    }

    public PlayerStats() {

    }

    public String getSummonerName() {
        return summonerName;
    }

    public String getPuuID() {
        return puuID;
    }

    public String getPosition() {
        return position;
    }

    public String getChampionName() {
        return championName;
    }

    public Boolean getWin() {
        return win;
    }

    public Double getAssists() {
        return assists;
    }

    public Double getKills() {
        return kills;
    }

    public Double getDeaths() {
        return deaths;
    }

    public Double getGoldEarned() {
        return goldEarned;
    }

    public Double getWardsPlaced() {
        return wardsPlaced;
    }

    public Double getVisionScore() {
        return visionScore;
    }

    public Double getControlWards() {
        return controlWards;
    }

    public Double getWardsDestroyed() {
        return wardsDestroyed;
    }

    public Double getDoubleKills() {
        return doubleKills;
    }

    public Double getTrippleKills() {
        return trippleKills;
    }

    public Double getQuadraKills() {
        return quadraKills;
    }

    public Double getPentaKills() {
        return pentaKills;
    }

    public Double getPhysicalDamageDealtToChapions() {
        return physicalDamageDealtToChapions;
    }

    public Double getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    public Double getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    public Double getTotalHeal() {
        return totalHeal;
    }

    public Double getTotalHealsOnTeammates() {
        return totalHealsOnTeammates;
    }
}
