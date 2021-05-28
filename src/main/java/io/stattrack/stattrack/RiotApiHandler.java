package io.stattrack.stattrack;

import DataTransferObjects.LeagueMatch;
import DataTransferObjects.PlayerStats;
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
    static final String tempKey="RGAPI-9c4c3902-77ef-44f0-9ef4-1fcb47ab4694";

    public RiotApiHandler(){
    }

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


    public String nameCorrection(String summonerName){
        return summonerName.toLowerCase(Locale.ROOT).replaceAll(" ","");
    }


    public String getEncryptedSummID(String summonerName,String region){
        //Hardcoded for now, I'll set it up later, consists of https://{server}.api{...}/by-name/{SummonerName}{ApiKey}
        String correctSummoner = nameCorrection(summonerName);
        String requestBase="https://"+region+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+correctSummoner+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson=new Gson();
        Map<String,String> map=gson.fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        return map.get("id");
    }
    public String getRankedInfo(String region,String summonerName){
        //I've gotta implement the entire DTO interface to work with this one
        String requestBase="https://"+region+".api.riotgames.com/lol/league/v4/entries/by-summoner/"+getEncryptedSummID(summonerName,region)+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson = new Gson();
        return json;
    }

    public String getEncryptedPUUID(String summonerName,String region) {

        String correctSummoner=summonerName.toLowerCase(Locale.ROOT).replaceAll(" ","");
        //Hardcoded for now, I'll set it up later, consists of https://{server}.api{...}/by-name/{SummonerName}{ApiKey}
        String requestBase="https://"+region+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+correctSummoner+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson=new Gson();
        Map<String,String> map=gson.fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        return map.get("puuid");
    }

    public LeagueMatch  getMatchDetails(String matchID,String region){
        region=correctRegion(region);
        String requestBase ="https://"+region+".api.riotgames.com/lol/match/v5/matches/"+matchID+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        return new LeagueMatch(json);
    }
    String correctRegion(String region){
        switch (region){
            case "euw1":
            case "eune":
                return "europe";
            case "ru" :
            case "kr" :
                return "asia";
            case "na1" : return "americas";
        }
        return null;
    }
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
    public ArrayList<String> getMatchlist(String summonerName,String region)
    {
        String correctSummoner = nameCorrection(summonerName);
        String requestBase="https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"+getEncryptedPUUID(summonerName,region)+"/ids?start=0&count=30&api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson=new Gson();
        return gson.fromJson(json,new TypeToken<ArrayList<String>>(){}.getType());
    }
}