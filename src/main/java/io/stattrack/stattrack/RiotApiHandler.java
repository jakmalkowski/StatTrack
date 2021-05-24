package io.stattrack.stattrack;

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
    static final String tempKey="RGAPI-7c39cbe6-110c-4ac9-a02d-4c43e4667ca2";

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


    String nameCorrection(String summonerName){
        return summonerName.toLowerCase(Locale.ROOT).replaceAll(" ","");
    }


    String getEncryptedSummID(String summonerName,String region){
        //Hardcoded for now, I'll set it up later, consists of https://{server}.api{...}/by-name/{SummonerName}{ApiKey}
        String correctSummoner = nameCorrection(summonerName);
        String requestBase="https://"+region+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+correctSummoner+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson=new Gson();
        Map<String,String> map=gson.fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        return map.get("id");
    }
    String getRankedInfo(String region,String summonerName){
        //I've gotta implement the entire DTO interface to work with this one
        String requestBase="https://"+region+".api.riotgames.com/lol/league/v4/entries/by-summoner/"+getEncryptedSummID(summonerName,region)+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson = new Gson();
        return json;
    }
    String getEncryptedPUUID(String summonerName,String region)
    {

        String correctSummoner=summonerName.toLowerCase(Locale.ROOT).replaceAll(" ","");
        //Hardcoded for now, I'll set it up later, consists of https://{server}.api{...}/by-name/{SummonerName}{ApiKey}
        String requestBase="https://"+region+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+correctSummoner+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        Gson gson=new Gson();
        Map<String,String> map=gson.fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        return map.get("puuid");
    }
    public String getMatchDetails(String matchID,String region){
        //Hardcoded for now
        String requestBase ="https://"+region+".api.riotgames.com/lol/match/v5/matches/"+matchID+"?api_key="+tempKey;
        String json=getGsonBase(requestBase);
        return json;
    }
    String getGsonBase(String requestBase){
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
    ArrayList<String> getMatchlist(String summonerName,String region)
    {
        String json;
        String requestBase="https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"+getEncryptedPUUID(summonerName,region)+"/ids?start=0&count=20&api_key="+tempKey;
        json=getGsonBase(requestBase);
        Gson gson=new Gson();
        return gson.fromJson(json,new TypeToken<ArrayList<String>>(){}.getType());
    }
}