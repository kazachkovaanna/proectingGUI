/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author ilyab
 */
public class InputStraemStatistics {
    private final ZipInputStream zis;
    private final List<Statistics> statistics;
    private Settings settings;
    
    /**
     * Конструктор связывающий поток ввода с файлом и считывающий данные из файла.
     * @param file фаил с данными.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public InputStraemStatistics(File file) throws FileNotFoundException, IOException {
        zis = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        statistics = new ArrayList<>();
        while(null != (entry = zis.getNextEntry())) {
            if("settings.cfg".equalsIgnoreCase(entry.getName())) {
                settings = new Settings(zis);
            } else if("level=".equalsIgnoreCase(entry.getName().substring(0, 5))) {
                statistics.add(new Statistics(zis));
            }
            zis.closeEntry();
        }
        zis.close();
    }
    
    /**
     * @return список статистик игрока.
     */
    public List<Statistics> getStatistics() {
        return statistics;
    }
    
    /**
     * @return настройки игрока.
     */
    public Settings getSettings() {
        return settings;
    }
}
