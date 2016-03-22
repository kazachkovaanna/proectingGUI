/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOStreamMaze;

import maze.Maze;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.Scanner;

/**
 *
 * @author ilyab
 */
public class InputStream {
    private static Maze maze;
    
    /**
     * Инициализирует лабиринт из файла
     * @param file - параметр, в котором хранится необходимая информация о лабиринте. 
     * Расширение файла должно быть *.maze
     * @return экземпляр класса Maze.
     * @throws java.io.FileNotFoundException, IOException
     */
    public static Maze getMaze(File file) throws FileNotFoundException, IOException {
        maze = new Maze();
        ZipInputStream iStream = new ZipInputStream(new java.io.FileInputStream(file));
        ZipEntry entry = null;
        while(null != (entry = iStream.getNextEntry())) {
            switch(entry.getName()) {
                case "maze.cfg":
                    readMaze(iStream);
                    break;
                case "settings.cfg":
                    readSettings(iStream);
                    break;
            }
            iStream.closeEntry();
        }
        iStream.close();
        return maze;
    }
    
    /**
     * Инициализирует лабиринт из файла
     * @param fileName - имя файла (вместе с путём), в котором хранится необходимая информация о лабиринте.
     * Расширение файла должно быть *.maze
     * @return экземпляр класса Maze.
     * @throws java.io.FileNotFoundException, IOException
     */
    public static Maze getMaze(String fileName) throws FileNotFoundException,IOException {
        return getMaze(new File(fileName));
    }
    
    private static void readMaze(ZipInputStream in) throws IOException {
        Scanner scanner = new Scanner(in);
        int w, h;
        w = scanner.nextInt();
        h = scanner.nextInt();
        
        maze.setWeight(w);
        maze.setHight(h);
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++)
                if (scanner.hasNext()) maze.set(i, j, scanner.nextByte() == 1);
                else throw new IOException("File incorrect");
    }
    
    private static void readSettings(ZipInputStream in) {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String []param = input.split("=");
            switch(param[0]) {
                case "start":
                    param = param[1].split("x");
                    maze.setStartX(Integer.parseInt(param[0]));
                    maze.setStartY(Integer.parseInt(param[1]));
                    break;
                case "finish":
                    param = param[1].split("x");
                    maze.setFinishX(Integer.parseInt(param[0]));
                    maze.setFinishY(Integer.parseInt(param[1]));
                    break;
            }
        }
    }
}
