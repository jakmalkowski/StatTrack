package io.stattrack.stattrack;

import DataTransferObjects.LeagueMatch;

//Implementation of DTO for RiotAPI
public class LeagueEntryDTO {
    public static void main(String[] args){
        RiotApiHandler kek=RiotApiHandler.getInstance();
        LeagueMatch aaa=new LeagueMatch(kek.getMatchDetails("EUN1_2775220260","europe"));
    }
}
