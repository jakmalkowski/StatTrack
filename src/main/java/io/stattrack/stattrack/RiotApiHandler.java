package io.stattrack.stattrack;

import io.stattrack.stattrack.dto.LeagueMatch;
import io.stattrack.stattrack.dto.PlayerStats;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

//Pulls data from logged user Session, or gets them from a set String
//Singleton with thread safety
public final class RiotApiHandler {
    private static volatile RiotApiHandler instance;
    static final String tempKey="RGAPI-15cc1105-5552-4a8d-bba9-8d181e4cd35d";

    public RiotApiHandler(){
    }
    //Singlpleton
    public static RiotApiHandler getInstance(){
        RiotApiHandler result = instance;
        if (result!=null){
            return result;
        }
        synchronized(RiotApiHandler.class){
            if(instance==null){
                instance = new RiotApiHandler();
            }
            return instance;
        }
    }

    //Formatting usernames for the API request
    public String nameCorrection(String summonerName){
        return summonerName.toLowerCase(Locale.ROOT).replaceAll(" ","");
    }

    //returns users encryptedID, usefull for pulling advanced info from api
    public String getEncryptedSummID(String summonerName,String region){
        String correctSummoner = nameCorrection(summonerName);
        String requestBase="https://"+region+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+correctSummoner+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson=new Gson();
        Map<String,String> map=gson.fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        return map.get("id");
    }
    //DOES NOT WORK ITS NOT MY FAULT THEY ARE REWORKING THE API AND DID NOT PROVIDE A DTO
    public String getRankedInfo(String region,String summonerName){
        //I've gotta implement the entire DTO interface to work with this one
        String requestBase="https://"+region+".api.riotgames.com/lol/league/v4/entries/by-summoner/"+getEncryptedSummID(summonerName,region)+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson = new Gson();
        return json;
    }
    //returns personalUserID wchich is universal for all regions
    public String getEncryptedPUUID(String summonerName,String region) {

        String correctSummoner=summonerName.toLowerCase(Locale.ROOT).replaceAll(" ","");
        //Hardcoded for now, I'll set it up later, consists of https://{server}.api{...}/by-name/{SummonerName}{ApiKey}
        String requestBase="https://"+region+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+correctSummoner+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson=new Gson();
        Map<String,String> map=gson.fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        return map.get("puuid");
    }

    //returns data of a leaguematch, provided with matchID and serverRegion
    public LeagueMatch  getMatchDetails(String matchID,String region){
        region=correctRegion(region);
        String requestBase ="https://"+region+".api.riotgames.com/lol/match/v5/matches/"+matchID+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        return new LeagueMatch(json);
    }
    //Parses regions in specific ways if needed
    String correctRegion(String region){
        switch (region){
            case "euw1":
            case "eun1":
                return "europe";
            case "ru" :
            case "kr" :
                return "asia";
            case "na1" : return "americas";
        }
        return region;
    }
    //Returns the stats of player from a game
    public PlayerStats getPlayerMatchStats(String summonerName,String matchID,String region){
        region=correctRegion(region);
        String requestBase ="https://"+region+".api.riotgames.com/lol/match/v5/matches/"+matchID+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        LeagueMatch match = new LeagueMatch(json);
        for( PlayerStats player : match.playerStats){
            if(player.getSummonerName().equals(summonerName)){
                return player;
            }
        }
        return null;
    }
    //Used to send requests to the API, it is's only job, you know, like S in the SOLID
    public String getGsonBase(String requestBase){
        try{
            StringBuilder json= new StringBuilder();
            URL url=new URL(requestBase);
            URLConnection conn=url.openConnection();
            conn.connect();
            BufferedReader in= new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputline;
            while((inputline=in.readLine())!=null){
                json.append(inputline);
            }
            return json.toString();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    //returns last 30 games played on an account
    public ArrayList<String> getMatchlist(String summonerName,String region)
    {
        String correctSummoner = nameCorrection(summonerName);
        String requestBase="https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"+getEncryptedPUUID(summonerName,region)+"/ids?start=0&count=30&api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson=new Gson();
        return gson.fromJson(json,new TypeToken<ArrayList<String>>(){}.getType());
    }
}