
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
import maze.Point;
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
    private double canvasLX;
    private double canvasLY;
    private double canvasW;
    private double canvasH;
    
    
    DrawMaze drawer;
    
    public EditHandler(FXMLEditorController controller, Canvas editArea, Scene sc, Maze mz){
        this.controller = controller;
        input = new ArrayList();
        canvas = editArea;
        scene = sc;
        maze = mz;
        drawer = new DrawMazeImpl();
        canvasLX = canvas.getLayoutX();
        canvasLY = canvas.getLayoutY();
        canvasH = canvas.getHeight();
        canvasW = canvas.getWidth();
    }
    @Override
    public void handle(MouseEvent event) {
        double eventX = event.getX();
        double eventY = event.getY();
        
        canvasLX = canvas.getLayoutX();
        canvasLY = canvas.getLayoutY();
        //if(eventY > scene.getHeight()-canvas.getHeight())
        if(eventX>canvasLX && eventX < canvasLX+canvasW && eventY>canvasLY && eventY<canvasLY+canvasH)
        {
            double w = canvasW/maze.getWeight();  //ширина ячейки
            double h = canvasH/maze.getHight();  //высота ячейки
            
            double xe = eventX - canvasLX;
            xe/=w;
            double ye =  eventY - canvasLY;
            ye/=h;
            int x = (int) xe;
            int y = (int) ye;
            Point p = new Point();
            p.setX(x);
            p.setY(y);
            //теперь известны координаты ячейки
            if(event.getButton()==MouseButton.SECONDARY){
                if(maze.isEmpty(p)){
                    if(!(x==maze.getFinishX() && y== maze.getFinishY())){
                        maze.setStartX(x);
                        maze.setStartY(y);
                    }
                }
            
            }
            else if(event.getButton()==MouseButton.MIDDLE){
                if(maze.isEmpty(p)){
                    if(!(x==maze.getStartX() && y== maze.getStartY())){
                        maze.setFinishX(x);
                        maze.setFinishY(y);
                    }
                }
            }
            else{
                if(!(x== maze.getFinishX() &&y == maze.getFinishY() )&& ! ( x== maze.getStartX() && y== maze.getStartY()))
                maze.set(p, maze.isEmpty(p));
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
