/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * OutputStreamStatistics - класс выгрузки статистики в фаил.
 * @author ilyab
 */
public class OutputStreamStatistics {
    private final ZipOutputStream zos;
    private List<Statistics> statistics;
    private Settings settings;
    
    /**
     * Конструктор инициализации. Связывает поток вывода с файлом.
     * @param file фаил для вывода.
     * @throws FileNotFoundException
     */
    public OutputStreamStatistics(File file) throws FileNotFoundException {
        zos = new ZipOutputStream(new FileOutputStream(file));
    }
    
    /**
     * @param statistics список статистик игрока по всем уровням для задания.
     */
    public void addAllStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }
    
    /**
     * Добавляет статистику по конкретному уровню к уже добавленым.
     * @param statistics статистика по конкретному уровню для задания.
     */
    public void addStatistics(Statistics statistics) {
        this.statistics.add(statistics);
    }
    
    /**
     * Удаляет добавленную статистику.
     */
    public void clearStatistics() {
        statistics.clear();
    }
    
    /**
     * @param settings настройки пользователя для задания.
     */
    public void setSettings(Settings settings) {
        this.settings = settings;
    }
    
    /**
     * Задание всех необходимых параметров с заменой статистики.
     * @param statistics список статистик игрока для задания.
     * @param settings настройки игрока для задания.
     */
    public void setAll(List<Statistics> statistics, Settings settings) {
        this.statistics = statistics;
        this.settings = settings;
    }
    
    /**
     * Запись в фаил заранее добавленых данных.
     * @throws java.io.IOException
     */
    public void flush() throws IOException {
        ZipEntry entry;
        
        //запись статистаки
        if(null != statistics)
        for(Statistics s : statistics) {
            entry = new ZipEntry("level=" + s.getLevelName() + ".cfg");
            zos.putNextEntry(entry);
            s.print(zos);
            zos.closeEntry();
        }
        
        //запись настроек
        if(null != settings) {
            entry = new ZipEntry("settings.cfg");
            zos.putNextEntry(entry);
            settings.print(zos);
            zos.closeEntry();
        }
        
        //закрытие потока на запись
        zos.close();
    }
}
