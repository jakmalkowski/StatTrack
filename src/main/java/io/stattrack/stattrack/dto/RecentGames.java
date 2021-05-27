package io.stattrack.stattrack.dto;

public class RecentGames{
    float KDA;
    float avgKills;
    float avgDeaths;
    float avgAssists;
    float avgVisionScore;
    float avgDamageToChampions;
    float avgHealing;
    String mostPopularRole;
    String mostPopularChampion;
    float winrate;

    public float getKDA() {
        return KDA;
    }

    public void setKDA(float KDA) {
        this.KDA = KDA;
    }

    public float getAvgKills() {
        return avgKills;
    }

    public void setAvgKills(float avgKills) {
        this.avgKills = avgKills;
    }

    public float getAvgDeaths() {
        return avgDeaths;
    }

    public void setAvgDeaths(float avgDeaths) {
        this.avgDeaths = avgDeaths;
    }

    public float getAvgAssists() {
        return avgAssists;
    }

    public void setAvgAssists(float avgAssists) {
        this.avgAssists = avgAssists;
    }

    public float getAvgVisionScore() {
        return avgVisionScore;
    }

    public void setAvgVisionScore(float avgVisionScore) {
        this.avgVisionScore = avgVisionScore;
    }

    public float getAvgDamageToChampions() {
        return avgDamageToChampions;
    }

    public void setAvgDamageToChampions(float avgDamageToChampions) {
        this.avgDamageToChampions = avgDamageToChampions;
    }

    public float getAvgHealing() {
        return avgHealing;
    }

    public void setAvgHealing(float avgHealing) {
        this.avgHealing = avgHealing;
    }

    public String getMostPopularRole() {
        return mostPopularRole;
    }

    public void setMostPopularRole(String mostPopularRole) {
        this.mostPopularRole = mostPopularRole;
    }

    public String getMostPopularChampion() {
        return mostPopularChampion;
    }

    public void setMostPopularChampion(String mostPopularChampion) {
        this.mostPopularChampion = mostPopularChampion;
    }

    public float getWinrate() {
        return winrate;
    }

    public void setWinrate(float winrate) {
        this.winrate = winrate;
    }
}
