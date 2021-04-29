package io.stattrack.stattrack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApiHandlingTests {

    @Test
    public void getUserEncryptedPUUID(){
        RiotApiHandler userTest=new RiotApiHandler();
        try {
            String encryptedId = userTest.getEncryptedPUUID("8f9zu86yj87xgh76", "eun1");
            Assert.assertEquals("ex0TUDyqcEumPfUFjmmTplmf6iHYdFnOi5HjjcFkz9byQNrCVdEdbaXm6CnpIbXS11iMKs8uNpXJ2Q",encryptedId);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    @Test
    public void getUserEncryptedPUUID2(){
        RiotApiHandler userTest=new RiotApiHandler();
        try {
            String encryptedId = userTest.getEncryptedPUUID("8f9zu86yj87xgh76", "eun1");
            Assert.assertNotEquals("pkJUgeuYD1wH6LqRsGmUYATe3F1T3NltD_s2wkDTzgKG8wdF4dABME5lH4yN05LAgCjzdg18BCmp5w",encryptedId);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
   @Test
    public void getMatchDetails(){
        try {
            RiotApiHandler userTest = new RiotApiHandler("eun1", "8f9zu86yj87xgh76");
            Path testfile = Path.of("/home/maek/StatTrack/src/test/java/io/stattrack/stattrack/matchinfo.txt");
            String fin = Files.readString(testfile);
            Assert.assertEquals(fin,userTest.getMatchDetails("EUN1_2775220260"));
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
   }
    @Test
    public void getMatchDetails2(){
        try {
            RiotApiHandler userTest = new RiotApiHandler("eun1", "Redlat");
            Path testfile = Path.of("/home/maek/StatTrack/src/test/java/io/stattrack/stattrack/matchinfo.txt");
            String fin = Files.readString(testfile);
            Assert.assertNotEquals(fin,userTest.getMatchDetails("EUN1_2775220260"));
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }
   @Test
    public void getMatchList(){
        RiotApiHandler userTest= new RiotApiHandler("eun1","8f9zu86yj87xgh76");
        String[] array= {"EUN1_2813356879", "EUN1_2797819662", "EUN1_2797712721", "EUN1_2797709856", "EUN1_2797693226", "EUN1_2797682448", "EUN1_2797526002",
                "EUN1_2797504038", "EUN1_2797540836", "EUN1_2794696829", "EUN1_2791827193", "EUN1_2791781454", "EUN1_2791655029",
                "EUN1_2791596648", "EUN1_2786187593", "EUN1_2785993952", "EUN1_2727858238", "EUN1_2727671015", "EUN1_2727312718", "EUN1_2727226847"
        };
        ArrayList<String> correct = new ArrayList<>(Arrays.asList(array));
        Assert.assertEquals(correct ,userTest.getMatchlist());
   }
   @Test
    public void getMatchList2(){
        //Actually found a bug, handler doesn't parse the string correctly(in getGsonbase) if it contains whitespace
        RiotApiHandler userTest= new RiotApiHandler("eun1","Stains of Time");
        String[] array= {"EUN1_2775220260", "EUN1_2774180103", "EUN1_2773714308", "EUN1_2773662778", "EUN1_2772530238", "EUN1_2772505023",
                "EUN1_2772491193", "EUN1_2772005021", "EUN1_2771803452", "EUN1_2769206534", "EUN1_2767311256", "EUN1_2767113653", "EUN1_2767101111",
                "EUN1_2767036478", "EUN1_2766884534", "EUN1_2766808664", "EUN1_2766432042", "EUN1_2766071093", "EUN1_2766077729", "EUN1_2765668508"
        };
        ArrayList<String> correct = new ArrayList<>(Arrays.asList(array));
        Assert.assertEquals(correct , userTest.getMatchlist());
   }
   @Test
    public void Combined(){
        try
        {
            RiotApiHandler userTest = new RiotApiHandler("eun1", "Redlat");
            String[] array = {"EUN1_2775220260", "EUN1_2774180103", "EUN1_2773714308", "EUN1_2773662778", "EUN1_2772530238", "EUN1_2772505023",
                    "EUN1_2772491193", "EUN1_2772005021", "EUN1_2771803452", "EUN1_2769206534", "EUN1_2767311256", "EUN1_2767113653", "EUN1_2767101111",
                    "EUN1_2767036478", "EUN1_2766884534", "EUN1_2766808664", "EUN1_2766432042", "EUN1_2766071093", "EUN1_2766077729", "EUN1_2765668508"
            };
            ArrayList<String> correct = new ArrayList<>(Arrays.asList(array));
            Path testfile = Path.of("/home/maek/StatTrack/src/test/java/io/stattrack/stattrack/matchinfo2.txt");
            String fin = Files.readString(testfile);
            //pUUID test
            Assert.assertEquals("pkJUgeuYD1wH6LqRsGmUYATe3F1T3NltD_s2wkDTzgKG8wdF4dABME5lH4yN05LAgCjzdg18BCmp5w",userTest.getEncryptedPUUID(userTest.summonerName, userTest.region));
            //matchList test
            Assert.assertEquals(correct,userTest.getMatchlist());
            //matchDetails test
            Assert.assertEquals(fin,userTest.getMatchDetails("EUN1_2772505023"));
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }

   }
}
