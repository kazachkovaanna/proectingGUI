package maze.gui.controllers.editor;

import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import maze.Maze;
import maze.Point;
import statistics.User;

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
        Point p = new Point();
        for(int i = 0; i<maze.getWeight(); i++){
            for(int j = 0; j<maze.getHight(); j++){
                p.setX(i);
                p.setY(j);
                if(maze.isEmpty(p)) gc.setFill(User.getUserTravelColor());
                else gc.setFill(User.getUserWallColor());
                gc.fillRect(width*i, height*j, width, height);  //рисует залитый цветом квадрат
                if(border){
                    gc.setFill(Color.BLACK);
                    gc.setLineWidth(1);
                    gc.strokeRect(width*i, height*j, width, height);
                }
            }
        }
        gc.setFill(User.getUserFinishColor());
        gc.fillRect(width*maze.getFinishX(), height*maze.getFinishY(), width, height);
        gc.setFill(User.getUserStartColor());
        gc.fillRect(width*maze.getStartX(), height*maze.getStartY(), width, height);
        if(border){
            gc.setFill(Color.BLACK);
            gc.setLineWidth(1);
            gc.strokeRect(width*maze.getFinishX(), height*maze.getFinishY(), width, height);
            gc.strokeRect(width*maze.getStartX(), height*maze.getStartY(), width, height);

        }
    }

    @Override
    public void DrawSolution(Canvas field, Maze maze, boolean border, List<Point> sol, int steps) {       
        double fieldW = field.getWidth();       //ширина поля
        double fieldH = field.getHeight();      //высота поля
        //Пусть лабиринт растягивается на все поле
        //тогда ширина 1 клетки = ширина поля/число клеток по ширине
        //аналогично для высоты
        double height = fieldH/maze.getHight();
        double width = fieldW/maze.getWeight();
        GraphicsContext gc = field.getGraphicsContext2D();
        gc.setFill(User.getUserDistanceTravelColor());
        Point p;
        if(sol!=null && sol.size() >= steps){
            if(sol.size() == steps) steps--;
            for(int i = 0; i<steps; i++){
                gc.setFill(User.getUserDistanceTravelColor());
                p = sol.get(i);
                gc.fillRect(p.getX()*width, p.getY()*height, width, height);
                if(border){
                    gc.setFill(Color.BLACK);
                    gc.setLineWidth(1);
                    gc.strokeRect(p.getX()*width,p.getY()*height, width, height);
                }
            }
        }
    }

    @Override
    public void DrawMan(Canvas field, Point from, Point to, Maze maze) {
        double fieldW = field.getWidth();       //ширина поля
        double fieldH = field.getHeight();      //высота поля
        //Пусть лабиринт растягивается на все поле
        //тогда ширина 1 клетки = ширина поля/число клеток по ширине
        //аналогично для высоты
        double height = fieldH/maze.getHight();
        double width = fieldW/maze.getWeight();
        GraphicsContext gc = field.getGraphicsContext2D();
        if(from.equals(maze.getStart()))
            gc.setFill(User.getUserStartColor());
        else
            gc.setFill(User.getUserTravelColor());
        gc.fillRect(from.getX()*width, from.getY()*height, width, height);
        Image man = new Image(DrawMazeImpl.class.getResource("man.png").toString());
        gc.drawImage(man, to.getX()*width, to.getY()*height, Double.min(width, height), Double.min(width, height));
    }
    
}
