/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers.gamehandler;

import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import maze.Maze;
import maze.Point;
import maze.gui.controllers.FXMLGameProcessController;
import maze.gui.controllers.editor.DrawMaze;
import maze.gui.controllers.editor.DrawMazeImpl;

/**
 *
 * @author Ann
 */
public class GameHandler implements EventHandler<KeyEvent>{

    FXMLGameProcessController controller;
    private ArrayList input;
    private Canvas canvas;;
    private Maze maze;
    private DrawMaze drawer;
    private Point man;
    private List<Point> solution;
    private long steps;
    private long hints;
    private int index;
    
    
    public GameHandler(FXMLGameProcessController controller, Canvas c, Maze m){
        this.controller = controller;
        canvas = c;
        maze = m;
        input = new ArrayList();
        drawer = new DrawMazeImpl();
    }
    
    @Override
    public void handle(KeyEvent event) {
        String code = event.getCode().toString();
        switch(code){
            case "ESCAPE":
                if(!controller.isFinish()){
                    if(controller.isPause()){
                     controller.unPause();
                     }
                     else{
                         controller.pause();
                     }
                }
                else return;
               break;
            case "DOWN":
                if(!controller.isPause())
                    if(maze.top(man)) {
                        drawer.DrawMan(canvas, man, Point.plus(man, new Point(0,1)), maze);
                        man.setY(man.getY()+1);
                        steps++;
                    }
                break;
            case "UP":
                if(!controller.isPause())
                    if(maze.bottom(man)) {
                        drawer.DrawMan(canvas, man, Point.minus(man, new Point(0,1)), maze);
                        man.setY(man.getY()-1);
                        steps++;
                    }
                break;
            case "LEFT":
                if(!controller.isPause())
                    if(maze.left(man)) {
                        drawer.DrawMan(canvas, man, Point.minus(man, new Point(1,0)), maze);
                        man.setX(man.getX()-1);
                        steps++;
                    }
                break;
            case "RIGHT":
                if(!controller.isPause())
                    if(maze.right(man)) {
                        drawer.DrawMan(canvas, man, Point.plus(man, new Point(1,0)), maze);
                        man.setX(man.getX()+1);
                        steps++;
                    }
                break;
        }
        
        index = solHasMan();
        if(index != -1)solution = solution.subList(index, solution.size());
        else solution = maze.getPath(man);
        //System.out.println(index); //ESCAPE
        //System.out.println(solution.size()); //ESCAPE
        //System.out.println(code); //ESCAPE
        // чтобы добавить только один раз
        if ( !input.contains(code) )
            input.add( code );
        if(maze.getFinish().equals(man)) controller.finish();
    }
    
    private int solHasMan(){
        int res = -1;
        for(Point p:solution){
            if (p.equals(man))
                res = solution.indexOf(p);
        }
        
        return res;
    }
    
    /**
     * Задать лабиринт для отображения
     * @param mz 
     */
    public void setMaze(Maze mz){
        maze = mz;
        drawer.Draw2D(canvas, maze, false);
        man = maze.getStart();
        solution = maze.getPath(man);
        drawer.DrawMan(canvas,  man,man, maze);
        hints = 0;
        steps = 0;
    }
    
    public void getHint(){
        if(index != -1){
            drawer.DrawSolution(canvas, maze, false, solution.subList(index+1,solution.size()), 1);
            hints++;
        }
    }
    public long getHints(){
        return hints;
    }
    public long getSteps(){
        return steps;
    }
    
    /**
     * Перерисовать лабиринт
     */
    public void redraw(){
        drawer.Draw2D(canvas, maze, false);
        drawer.DrawMan(canvas,  man,man, maze);
    }
    
    public void drawHint(){
        
    }
    
}

