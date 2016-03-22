/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOStreamMaze;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import maze.Maze;

/**
 *
 * @author ilyab
 */
public class OutputStream {

    /**
     * Поток записывает в фаил всю информацию о лабиринте
     * @param maze экземпляр класса Maze
     * @param fileName имя файла для записи
     * @throws java.io.FileNotFoundException
     */
    public static void setMaze(Maze maze, String fileName) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(fileName);
        ZipOutputStream zout = new ZipOutputStream(fout);
        //степень сжатия
        zout.setLevel(9);
        
        ZipEntry entry = new ZipEntry("maze.cfg");
        zout.putNextEntry(entry);
        Integer weight = maze.getWeight(),
            height = maze.getHight();
        
        zout.write(weight.toString().getBytes());
        zout.write("\n".getBytes());
        zout.write(height.toString().getBytes());
        zout.write("\n".getBytes());
        for (int i = 0; i < maze.getWeight(); i++) {
            for (int j = 0; j < maze.getHight(); j++) {
                if (maze.isEmpty(i, j)) zout.write('0');
                else zout.write('1');
                zout.write(' ');
            }
            zout.write("\n".getBytes());
        }
        zout.closeEntry();
        
        entry = new ZipEntry("settings.cfg");
        zout.putNextEntry(entry);
        zout.write(("start="+maze.getStartX()+"x"+maze.getStartY()+"\n").getBytes());
        zout.write(("finish="+maze.getFinishX()+"x"+maze.getFinishY()+"\n").getBytes());
        zout.closeEntry();
        
        zout.close();
    }
}
