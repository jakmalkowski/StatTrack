package io.stattrack.stattrack.dto;

public class SettingsContainer {
    public boolean KDA=false;
    public boolean avgKills=false;
    public boolean avgDeaths=false;
    public boolean avgAssists=false;
    public boolean avgVisionScore=false;
    public boolean avgDamageToChampions=false;
    public boolean avgHealing=false;
    public boolean mostPopularRole=false;
    public boolean mostPopularChampion=false;
    public boolean winrate=false;
    public void changeValue(String field){
        switch (field) {
            case "KDA":KDA=true;
                break;
            case "avgKills":avgKills=true;
                break;
            case "avgDeaths":avgDeaths=true;
                break;
            case "avgAssists":avgAssists=true;
                break;
            case "avgVisionScore":avgVisionScore=true;
                break;
            case "avgDamageToChampions":avgDamageToChampions=true;
                break;
            case "avgHealing":avgHealing=true;
                break;
            case "mostPopularRole":mostPopularRole=true;
                break;
            case "mostPopularChampion":mostPopularChampion=true;
                break;
            case "winrate":winrate=true;
                break;
        }
    }
    public boolean getKDA() {
        return KDA;
    }

    public void setKDA(boolean KDA) {
        this.KDA = KDA;
    }

    public boolean getAvgKills() {
        return avgKills;
    }

    public void setAvgKills(boolean avgKills) {
        this.avgKills = avgKills;
    }

    public boolean getAvgDeaths() {
        return avgDeaths;
    }

    public void setAvgDeaths(boolean avgDeaths) {
        this.avgDeaths = avgDeaths;
    }

    public boolean getAvgAssists() {
        return avgAssists;
    }

    public void setAvgAssists(boolean avgAssists) {
        this.avgAssists = avgAssists;
    }

    public boolean getAvgVisionScore() {
        return avgVisionScore;
    }

    public void setAvgVisionScore(boolean avgVisionScore) {
        this.avgVisionScore = avgVisionScore;
    }

    public boolean getAvgDamageToChampions() {
        return avgDamageToChampions;
    }

    public void setAvgDamageToChampions(boolean avgDamageToChampions) {
        this.avgDamageToChampions = avgDamageToChampions;
    }

    public boolean getAvgHealing() {
        return avgHealing;
    }

    public void setAvgHealing(boolean avgHealing) {
        this.avgHealing = avgHealing;
    }

    public boolean getMostPopularRole() {
        return mostPopularRole;
    }

    public void setMostPopularRole(boolean mostPopularRole) {
        this.mostPopularRole = mostPopularRole;
    }

    public boolean getMostPopularChampion() {
        return mostPopularChampion;
    }

    public void setMostPopularChampion(boolean mostPopularChampion) {
        this.mostPopularChampion = mostPopularChampion;
    }

    public boolean getWinrate() {
        return winrate;
    }

    public void setWinrate(boolean winrate) {
        this.winrate = winrate;
    }


}
