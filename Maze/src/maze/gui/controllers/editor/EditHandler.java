
package maze.gui.controllers.editor;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import maze.Maze;
import maze.gui.controllers.FXMLEditorController;

/**
 *
 * @author Ann
 */
public class EditHandler implements EventHandler<MouseEvent>{

    FXMLEditorController controller;
    private Canvas canvas;
    private ArrayList input;
    private Scene scene;
    private Maze maze;
    DrawMaze drawer;
    
    public EditHandler(FXMLEditorController controller, Canvas editArea, Scene sc, Maze mz){
        this.controller = controller;
        input = new ArrayList();
        canvas = editArea;
        scene = sc;
        maze = mz;
        drawer = new DrawMazeImpl();
    }
    @Override
    public void handle(MouseEvent event) {
        double eventX = event.getX();
        double eventY = event.getY();
        if(eventY > scene.getHeight()-canvas.getHeight())
        {
            double w = canvas.getWidth()/maze.getWeight();  //ширина ячейки
            double h = canvas.getHeight()/maze.getHight();  //высота ячейки
            int x = (int) (eventX/w);
            int y = (int) (eventY/h) - 1;
            //теперь известны координаты ячейки
            if(event.getButton()==MouseButton.SECONDARY){
                if(maze.isEmpty(x, y)){
                    if(!(x==maze.getFinishX() && y== maze.getFinishY())){
                        maze.setStartX(x);
                        maze.setStartY(y);
                    }
                }
            
            }
            else if(event.getButton()==MouseButton.MIDDLE){
                if(maze.isEmpty(x, y)){
                    if(!(x==maze.getStartX() && y== maze.getStartY())){
                        maze.setFinishX(x);
                        maze.setFinishY(y);
                    }
                }
            }
            else{
                if(!(x== maze.getFinishX() &&y == maze.getFinishY() )&& ! ( x== maze.getStartX() && y== maze.getStartY()))
                maze.set(x, y, maze.isEmpty(x, y));
            }
            drawer.Draw2D(canvas, maze, true);
        }
            
    }
    public void setMaze(Maze mz){
        maze = mz;
        drawer.Draw2D(canvas, maze, true);
    }
    
    public void redraw(){
        drawer.Draw2D(canvas, maze, true);
    }
    
}