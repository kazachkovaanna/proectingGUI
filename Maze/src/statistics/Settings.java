/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import javafx.scene.paint.Color;

/**
 *
 * @author ilyab
 */
public class Settings {
    private Color wallColor;
    private Color travelColor;
    private Color startColor;
    private Color finishColor;
    private Color distanceTravelColor;
    
    /**
     * Конструктор поумолчанию. Инициализирует все поля значением null.
     */
    public Settings() {
        this.wallColor = Color.DARKRED;
        this.travelColor = Color.BEIGE;
        this.startColor = Color.GREENYELLOW;
        this.finishColor = Color.BLUEVIOLET;
        this.distanceTravelColor = Color.DARKSALMON;
    }
    
    /**
     * Конструктор копирования. Копирует объект ob.
     * @param ob копируемый объект.
     */
    public Settings(final Settings ob) {
        this.wallColor = ob.wallColor;
        this.travelColor = ob.travelColor;
        this.startColor = ob.startColor;
        this.finishColor = ob.finishColor;
        this.distanceTravelColor = ob.distanceTravelColor;
    }
    
    /**
     * Конструктор инициализации.
     * @param wall цвет стен.
     * @param travel цвет дорожек.
     * @param start цвет старта.
     * @param finish цвет финиша.
     * @param distanceTravel цвет пройденного пути.
     */
    public Settings(final Color wall, final Color travel, final Color start, final Color finish, final Color distanceTravel) {
        this.wallColor = wall;
        this.travelColor = travel;
        this.startColor = start;
        this.finishColor = finish;
        this.distanceTravelColor = distanceTravel;
    }
    
    /**
     * Конструктор инициализации из потока ввода. (Десериализация)
     * @param in поток ввода, содержащий в себе информацию о настройках в формате Map.
     * @throws java.io.IOException если информация в потоке не соответствует требованиям или с потоком что-то случилось.
     */
    public Settings(final InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String key;
        String value;
        int c = 0;
        while(c >= 0) {
            for(c = in.read(); c != '=' && c >= 0;)
                if (c < 0 ) throw new IOException();
                else sb.append((char)c);
            key = sb.toString();
            sb.delete(0, sb.length());
            
            for(c = in.read(); c != '\n' && c >= 0;)
                sb.append((char)c);
            value = sb.toString();
            sb.delete(0, sb.length());
            
            switch(key) {
                case "wallColor=":
                    wallColor = Color.valueOf(value);
                    break;
                case "travelColor=":
                    travelColor = Color.valueOf(value);
                    break;
                case "startColor=":
                    startColor = Color.valueOf(value);
                    break;
                case "finishColor=":
                    finishColor = Color.valueOf(value);
                    break;
                case "distanceTravelColor=":
                    distanceTravelColor = Color.valueOf(value);
                    break;
            }
        }
    }
    
    /**
     * Выгрузка класса в поток вывода. (Сериализация)
     * @param out поток, в который выгружается информация о настройках в формате Map.
     * @throws java.io.IOException если что-то случилось с потоком вывода.
     */
    public void print(final OutputStream out) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("wallColor=").append(wallColor).append('\n');
        sb.append("travelColor=").append(travelColor).append('\n');
        sb.append("startColor=").append(startColor).append('\n');
        sb.append("finishColor=").append(finishColor).append('\n');
        sb.append("distanceTravelColor=").append(distanceTravelColor);
        out.write(sb.toString().getBytes());
        out.flush();
    }

    /**
     * @return цвет стен.
     */
    public Color getWallColor() {
        return wallColor;
    }

    /**
     * @param wallColor цвет стен для задания.
     */
    public void setWallColor(Color wallColor) {
        this.wallColor = wallColor;
    }

    /**
     * @return цвет дорожек.
     */
    public Color getTravelColor() {
        return travelColor;
    }

    /**
     * @param travelColor цвет дорожек для задания.
     */
    public void setTravelColor(Color travelColor) {
        this.travelColor = travelColor;
    }

    /**
     * @return цвет старта.
     */
    public Color getStartColor() {
        return startColor;
    }

    /**
     * @param startColor цвет старта для задания.
     */
    public void setStartColor(Color startColor) {
        this.startColor = startColor;
    }

    /**
     * @return цвет финиша.
     */
    public Color getFinishColor() {
        return finishColor;
    }

    /**
     * @param finishColor цвет финиша для задания.
     */
    public void setFinishColor(Color finishColor) {
        this.finishColor = finishColor;
    }

    /**
     * @return цвет пройденого пути.
     */
    public Color getDistanceTravelColor() {
        return distanceTravelColor;
    }

    /**
     * @param distanceTravelColor цвет пройденого пути для задания.
     */
    public void setDistanceTravelColor(Color distanceTravelColor) {
        this.distanceTravelColor = distanceTravelColor;
    }
}
