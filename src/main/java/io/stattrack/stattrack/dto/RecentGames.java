package io.stattrack.stattrack.dto;

public class RecentGames{
    double KDA;
    double avgKills;
    double avgDeaths;
    double avgAssists;
    double avgVisionScore;
    double avgDamageToChampions;
    double avgHealing;
    String mostPopularRole;
    String mostPopularChampion;
    double winrate;

    public double getKDA() {
        return KDA;
    }

    public void setKDA(double KDA) {
        this.KDA = KDA;
    }

    public double getAvgKills() {
        return avgKills;
    }

    public void setAvgKills(double avgKills) {
        this.avgKills = avgKills;
    }

    public double getAvgDeaths() {
        return avgDeaths;
    }

    public void setAvgDeaths(double avgDeaths) {
        this.avgDeaths = avgDeaths;
    }

    public double getAvgAssists() {
        return avgAssists;
    }

    public void setAvgAssists(double avgAssists) {
        this.avgAssists = avgAssists;
    }

    public double getAvgVisionScore() {
        return avgVisionScore;
    }

    public void setAvgVisionScore(double avgVisionScore) {
        this.avgVisionScore = avgVisionScore;
    }

    public double getAvgDamageToChampions() {
        return avgDamageToChampions;
    }

    public void setAvgDamageToChampions(double avgDamageToChampions) {
        this.avgDamageToChampions = avgDamageToChampions;
    }

    public double getAvgHealing() {
        return avgHealing;
    }

    public void setAvgHealing(double avgHealing) {
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

    public double getWinrate() {
        return winrate;
    }

    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }
}
