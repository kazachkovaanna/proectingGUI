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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 *
 * @author kseniadiogenova
 */
public class User {
    private static String userName;
    private static ArrayList<Statistics> userStatistics = new ArrayList<>();
    private static Settings userSettings = new Settings();
    private static OutputStreamStatistics out;
    private static InputStraemStatistics in;
    public static ObservableList<Statistics> data  = FXCollections.observableArrayList(userStatistics);;
    
    public static void setUser(String name, ArrayList<Statistics> statistics, Settings settings){
        userName = name;
        userStatistics = statistics;
        userSettings = settings;
    }
    
    public static void newUser(String name) throws FileNotFoundException{
        userName = name;
        userStatistics = new ArrayList<>();
        userSettings = new Settings();
        userSettings.setWallColor(Color.DARKRED);
        userSettings.setTravelColor(Color.BEIGE);
        userSettings.setStartColor(Color.GREENYELLOW);
        userSettings.setFinishColor(Color.BLUEVIOLET);
        userSettings.setDistanceTravelColor(Color.DARKSALMON);
        out = new OutputStreamStatistics(new File("./src/users/"+userName));
        data.addAll(userStatistics);
    }
    
    public static void write() throws IOException{
        if(userName == null) return;
        out = new OutputStreamStatistics(new File("./src/users/"+userName));
        out.setAll(userStatistics, userSettings);
        out.flush();
    }
    
    public static void get(File f) throws IOException{
        data.removeAll(userStatistics);
        in = new InputStraemStatistics(f);
        userName = f.getName();
        userStatistics = (ArrayList<Statistics>) in.getStatistics();
        userSettings = in.getSettings();
        data.addAll(userStatistics);
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
    
    /**
     * @return настройки игрока
    */
    public static Settings getUserSettings(){
        return userSettings;
    }
    
    public static void setUserStatistics(ArrayList<Statistics> statistics){
        userStatistics = statistics;
        data.removeAll(userStatistics);
        data.addAll(userStatistics);
    }
    
    public static void addStatistics(String level, Long time, Long steps, Long help){
        Statistics stat = null;
        for (Statistics sc : userStatistics){
            if(sc.getLevelName().equals(level))
                stat = sc;
        }
        if(stat != null){
            data.remove(stat);
            if(stat.getTime() > time) stat.setTime(time);
            if(stat.getStep() > steps) stat.setStep(steps);
            if(stat.getHelpAmount() > help) stat.setHelpAmount(help);
        }
        else {
            Statistics newStat = new Statistics(level, time, steps, help);
            userStatistics.add(newStat);
        }
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.removeAll(userStatistics);
        data.addAll(userStatistics);
    }
    
    public static ObservableList<Statistics> getData(){
        return data;
    }
    
    public static void setUserSettings(Settings settings){
        userSettings = settings;
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setUserWallColor(Color wallColor){
        userSettings.setWallColor(wallColor);
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setUserTravelColor(Color travelColor){
        userSettings.setTravelColor(travelColor);
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setUserStartColor(Color StartColor){
        userSettings.setStartColor(StartColor);
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setUserFinishColor(Color FinishColor){
        userSettings.setFinishColor(FinishColor);
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setUserDistanceTravelColor(Color distanceTravelColor){
        userSettings.setDistanceTravelColor(distanceTravelColor);
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
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
