package io.stattrack.stattrack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.bson.json.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

//Pulls data from logged user Session, or gets them from a set String
public class RiotApiHandler {
    String region="";
    String summonerName="";
    String encryptedPUUID="";
    ArrayList<String> last20games;
    Map<String,String> matchdetails=Collections.emptyMap();

    RiotApiHandler(String region,String summonerName){
        this.region=region;
        this.summonerName=summonerName;
        try
        {
            this.encryptedPUUID=getEncryptedPUUID(summonerName,region);
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }
        this.last20games=getMatchlist();

    }
    RiotApiHandler(){

    }
    String getEncryptedPUUID(String summonerName,String region) throws IOException
    {
        String json;
        //Hardcoded for now, I'll set it up later, consists of https://{server}.api{...}/by-name/{SummonerName}{ApiKey}
        String requestBase="https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+summonerName+"?api_key=RGAPI-2fb630ae-9481-4536-99a5-88b61ac1cc8c";
        json=getGsonBase(requestBase);
        Gson gson=new Gson();
        Map<String,String> map=gson.fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        return map.get("puuid");
    }
    String getMatchDetails(String matchID){
        //Hardcoded for now
        String requestBase ="https://europe.api.riotgames.com/lol/match/v5/matches/"+matchID+"?api_key=RGAPI-2fb630ae-9481-4536-99a5-88b61ac1cc8c";
        JsonObject obj = new Gson().fromJson(getGsonBase(requestBase),JsonObject.class);
        //Work in progress, for now returns the entire string dispatched by the API
        //Map<Map<String,String>,String> matchDetails = gson.fromJson(json,new TypeToken<Map<Map<String,String>,String>>(){}.getType());
        return obj.toString();
    }
    String getGsonBase(String requestBase){
        try{
            String json="";
            URL url=new URL(requestBase);
            URLConnection conn=url.openConnection();
            conn.connect();
            BufferedReader in= new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputline;
            while((inputline=in.readLine())!=null){
                json+=inputline;
            }
            return json;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    ArrayList<String> getMatchlist(){
        String json;
        String requestBase="https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"+this.encryptedPUUID+"/ids?start=0&count=20&api_key=RGAPI-2fb630ae-9481-4536-99a5-88b61ac1cc8c";
        json=getGsonBase(requestBase);
        Gson gson=new Gson();
        return gson.fromJson(json,new TypeToken<ArrayList<String>>(){}.getType());
    }
}
