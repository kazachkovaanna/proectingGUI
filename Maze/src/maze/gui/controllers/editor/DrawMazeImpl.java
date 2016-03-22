package maze.gui.controllers.editor;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import maze.Maze;

/**
 *
 * @author Ann
 */
public class DrawMazeImpl implements DrawMaze{

    @Override
    public void Draw2D(Canvas field, Maze maze, boolean border) {
        double fieldW = field.getWidth();       //ширина поля
        double fieldH = field.getHeight();      //высота поля
        //Пусть лабиринт растягивается на все поле
        //тогда ширина 1 клетки = ширина поля/число клеток по ширине
        //аналогично для высоты
        double height = fieldH/maze.getHight();
        double width = fieldW/maze.getWeight();
        GraphicsContext gc = field.getGraphicsContext2D();
        for(int i = 0; i<maze.getWeight(); i++){
            for(int j = 0; j<maze.getHight(); j++){
                if(maze.isEmpty(i, j)) gc.setFill(Color.BEIGE);
                else gc.setFill(Color.DARKRED);
                gc.fillRect(width*i, height*j, width, height);  //рисует залитый цветом квадрат
                if(border){
                    gc.setFill(Color.BLACK);
                    gc.setLineWidth(1);
                    gc.strokeRect(width*i, height*j, width, height);
                }
            }
        }
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(width*maze.getFinishX(), height*maze.getFinishY(), width, height);
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(width*maze.getStartX(), height*maze.getStartY(), width, height);
        gc.setFill(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(width*maze.getFinishX(), height*maze.getFinishY(), width, height);
        gc.strokeRect(width*maze.getStartX(), height*maze.getStartY(), width, height);
    }
    
}
