/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author kseniadiogenova
 */
public class User {
    private String userName;
    private ArrayList<Statistics> userStatistics;
    private Settings userSettings;
    
    
    /**
     * Конструктор по умолчанию. Инициализирует все поля значением null.
     */    
    public User(){
        this.userName = null;
        userStatistics = new ArrayList<Statistics>();
        userSettings = new Settings();
    }
    
    /**
     * Конструктор инициализации. 
     * @param name значение имени игрока
     * @param statistics
     * @param settings
    */
    public User(String name, ArrayList<Statistics> statistics, Settings settings){
        this.userName = name;
        userStatistics = statistics;
        userSettings = settings;
    }
    
    /**
     * @return имя игрока
    */
    public String getUserName(){
        return userName;
    }
    
    /**
     * @return статистику игрока
    */
    public ArrayList<Statistics> getUserStatistics(){
        return userStatistics;
    }
 //   public Statistics getStatsticsByName(String name){
   //     if (userName.equals(name))
     //   return Statistics;
    //}
    
    /**
     * @return настройки игрока
    */
    public Settings getUserSettings(){
        return userSettings;
    }
    
    public void setUserStatistics(ArrayList<Statistics> statistics){
        userStatistics = statistics;
    }
    
    public void setUserSettings(Settings settings){
        userSettings = settings;
    }
    
    public void setUserWallColor(Color wallColor){
        userSettings.setWallColor(wallColor);
    }
    
    public void setUserTravelColor(Color travelColor){
        userSettings.setTravelColor(travelColor);
    }
    public void setUserStartColor(Color StartColor){
        userSettings.setStartColor(StartColor);
    }
    public void setUserFinishColor(Color FinishColor){
        userSettings.setFinishColor(FinishColor);
    }
    public void setUserDistanceTravelColor(Color distanceTravelColor){
        userSettings.setDistanceTravelColor(distanceTravelColor);
    }

}
