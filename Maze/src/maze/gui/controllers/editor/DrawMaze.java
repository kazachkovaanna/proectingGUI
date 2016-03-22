/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers.editor;

import javafx.scene.canvas.Canvas;
import maze.Maze;

/**
 *
 * @author Ann
 */
public interface DrawMaze {
    public void Draw2D(Canvas field, Maze maze, boolean border);
}
