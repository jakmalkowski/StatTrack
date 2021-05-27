package io.stattrack.stattrack;

import DataTransferObjects.LeagueMatch;
import DataTransferObjects.PlayerStats;

import java.lang.reflect.Field;
import java.util.ArrayList;

//Implementation of DTO for RiotAPI
public class LeagueEntryDTO {
    public static void main(String[] args) throws IllegalAccessException
    {
        RiotApiHandler kek=RiotApiHandler.getInstance();
        ArrayList<String> array = kek.getMatchlist("8f9zu86yj87xgh76","eun1");
        long start = System.currentTimeMillis();
        for(String s : array){
            PlayerStats redlat;
            redlat=kek.getPlayerMatchStats("8f9zu86yj87xgh76",s,"europe");
            assert redlat != null;
            System.out.println("GIERA UWAGA LECĄ DANE");
            for(Field field : redlat.getClass().getDeclaredFields()){
                field.setAccessible(true);
                String name=field.getName();
                Object value = field.get(redlat);
                System.out.println("   " +name+":  "+value);
            }
            System.out.println();
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }
}
