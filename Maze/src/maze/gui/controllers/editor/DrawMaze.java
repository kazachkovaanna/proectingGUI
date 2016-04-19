/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers.editor;

import java.util.List;
import javafx.scene.canvas.Canvas;
import maze.Maze;
import maze.Point;

/**
 *
 * @author Ann
 */
public interface DrawMaze {
    public void Draw2D(Canvas field, Maze maze, boolean border);
    public void DrawSolution(Canvas field, Maze maze, boolean border,List<Point> sol, int steps);
}
