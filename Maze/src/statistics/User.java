/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author kseniadiogenova
 */
public class User {
    private static String userName;
    private static ArrayList<Statistics> userStatistics = new ArrayList<Statistics>();
    private static Settings userSettings = new Settings();
    private static OutputStreamStatistics out;
    private static InputStraemStatistics in;
    
    
    /**
     * Конструктор по умолчанию. 
     */    
    public User(){
        this.userName = null;
    }
    
    public static void setUser(String name, ArrayList<Statistics> statistics, Settings settings){
        userName = name;
        userStatistics = statistics;
        userSettings = settings;
    }
    public static void newUser(String name) throws FileNotFoundException{
        userName = name;
        userStatistics = new ArrayList<Statistics>();
        userSettings = new Settings();
        userSettings.setWallColor(Color.DARKRED);
        userSettings.setTravelColor(Color.BEIGE);
        userSettings.setStartColor(Color.GREENYELLOW);
        userSettings.setFinishColor(Color.BLUEVIOLET);
        userSettings.setDistanceTravelColor(Color.DARKSALMON);
        out = new OutputStreamStatistics(new File("./src/users/"+userName));
    }
    
    public static void write() throws IOException{
        out.setAll(userStatistics, userSettings);
        out.flush();
    }
    
    public static void get(File f) throws IOException{
        in = new InputStraemStatistics(f);
        userName = f.getName();
        userStatistics = (ArrayList<Statistics>) in.getStatistics();
        userSettings = in.getSettings();
        
    }
    
    /**
     * @return имя игрока
    */
    public static String getUserName(){
        return userName;
    }
    
    /**
     * @return статистику игрока
    */
    public static ArrayList<Statistics> getUserStatistics(){
        return userStatistics;
    }
 //   public Statistics getStatsticsByName(String name){
   //     if (userName.equals(name))
     //   return Statistics;
    //}
    
    /**
     * @return настройки игрока
    */
    public static Settings getUserSettings(){
        return userSettings;
    }
    
    public static void setUserStatistics(ArrayList<Statistics> statistics){
        userStatistics = statistics;
    }
    
    public static void setUserSettings(Settings settings){
        userSettings = settings;
    }
    
    public static void setUserWallColor(Color wallColor){
        userSettings.setWallColor(wallColor);
    }
    
    public static void setUserTravelColor(Color travelColor){
        userSettings.setTravelColor(travelColor);
    }
    public static void setUserStartColor(Color StartColor){
        userSettings.setStartColor(StartColor);
    }
    public static void setUserFinishColor(Color FinishColor){
        userSettings.setFinishColor(FinishColor);
    }
    public static void setUserDistanceTravelColor(Color distanceTravelColor){
        userSettings.setDistanceTravelColor(distanceTravelColor);
    }
    public static Color getUserWallColor(){
        return userSettings.getWallColor();
    }
    
    public static Color getUserTravelColor(){
        return userSettings.getTravelColor();
    }
    public static Color getUserStartColor(){
        return userSettings.getStartColor();
    }
    public static Color getUserFinishColor(){
        return userSettings.getFinishColor();
    }
    public static Color getUserDistanceTravelColor(){
        return userSettings.getDistanceTravelColor();
    }


}
