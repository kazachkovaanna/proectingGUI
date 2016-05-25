/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.io.IOException;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ilyab
 */
public class Statistics {
    private String levelName;
    private Long time;
    private Long step;
    private Long helpAmount;
    
    /**
     * Конструктор поумолчанию инициализирует все поля значениями null.
     */
    public Statistics() {
        this.levelName = null;
        this.time = null;
        this.step = null;
        this.helpAmount = null;
    }
    
    /**
     * Конструктор инициализации.
     * @param levelName значение названия уровня для задания.
     * @param time значение времени прохождения уровня для задания.
     * @param step значение количества шагов для задания.
     * @param helpAmount зачение количества подсказок для задания.
     */
    public Statistics(final String levelName, final Long time, final Long step, final Long helpAmount) {
        this.levelName = levelName;
        this.time = time;
        this.step = step;
        this.helpAmount = helpAmount;
    }
    
    /**
     * Конструктор копирования.
     * @param ob объект для копирования.
     */
    public Statistics(final Statistics ob) {
        this.levelName = ob.levelName;
        this.time = ob.time;
        this.helpAmount = ob.helpAmount;
    }
    
    /**
     * Конструктор инициализации из потока. конструктор десериализации.
     * @param in поток, в котором хранится информация о статистике в формате Map.
     * @throws IOException если впроблемы с потоком, или информация в нем не соответствует заданным требованиям.
     */
    public Statistics(final java.io.InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String key;
        String value;
        int c = 0;
        while(c >= 0) {
            sb.delete(0, sb.length());
            for (c = in.read(); c != '=' && c >= 0;)
                sb.append((char)c);
            key = sb.toString();
            
            sb.delete(0, sb.length());
            for (c = in.read(); c != '\n' && c >= 0;)
                sb.append((char)c);
            value = sb.toString();
            
            switch(key) {
                case "levelName":
                    levelName = value;
                    break;
                case "time":
                    time = Long.parseLong(value);
                    break;
                case "helpAmount":
                    helpAmount = Long.parseLong(value);
                    break;
            }
        }
            
    }
    
    /**
     * Десериализация.
     * @param out поток вывода, в который кладётся десиреализованный объект.
     * @throws IOException если что-то с потоком случилось не так.
     */
    public void print(java.io.OutputStream out) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("levelName=").append(levelName).append('\n');
        sb.append("time=").append(time.toString()).append('\n');
        sb.append("step").append(getStep()).append('\n');
        sb.append("helpAmount=").append(helpAmount.toString());
        out.write(sb.toString().getBytes());
        out.flush();
    }

    /**
     * @return имя уровня.
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * @param levelName имя уровня для задания.
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * @return время прохождения уровня.
     */
    public Long getTime() {
        return time;
    }

    /**
     * @param time время прохождения уровня для задания.
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * @return число использованных подсказок.
     */
    public Long getHelpAmount() {
        return helpAmount;
    }

    /**
     * @param helpAmount число использованных подсказок для задания.
     */
    public void setHelpAmount(Long helpAmount) {
        this.helpAmount = helpAmount;
    }

    /**
     * @return число шагов, за которые был пройден лабиринт.
     */
    public Long getStep() {
        return step;
    }

    /**
     * @param step число шагов, за которые был пройден лабиринт, для задания.
     */
    public void setStep(Long step) {
        this.step = step;
    }
    
    public StringProperty levelNameProperty(){
        return new SimpleStringProperty(levelName);
    }
    public LongProperty stepProperty(){
        return new SimpleLongProperty(step);
    }
    public LongProperty helpAmountProperty(){
        return new SimpleLongProperty(helpAmount);
    }
    public LongProperty timeProperty(){
        return new SimpleLongProperty(time);
    }
    
}
