
package maze.gui.controllers.editor;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
    private final Canvas canvas;
    private Maze maze;
    private double canvasLX;
    private double canvasLY;
    private double canvasW;
    private double canvasH;

    private boolean isSaved;
    
    
    DrawMaze drawer;
    /**
     * Инициализирует обработчик
     * 
     * @param controller - контроллер, усправляющий сценой редактирования
     * @param editArea - canvas, на котором рисуется лабиринт
     * @param mz - изображаемый лабиринт
     */
    public EditHandler(FXMLEditorController controller, Canvas editArea, Maze mz){
        this.controller = controller;
        canvas = editArea;
        maze = mz;
        drawer = new DrawMazeImpl();
        canvasLX = canvas.getLayoutX();
        canvasLY = canvas.getLayoutY();
        isSaved = true;
    }
    
    /**
     * Обрабатывает нажатия мыши
     * Если нажатие происходит в области отрисовки - преобразует координаты нажатия в индексы ячейки массива и изменяет ее значение в соответствии с типом нажатой кнопки
     * Левая кнопка - добавить/удалить стену
     * Правая кнопка - переместить в нажатую клетку старт
     * Средняя кнопка - переместить в нажатую клетку финиш
     * @param event 
     */
    @Override
    public void handle(MouseEvent event) {
        double eventX = event.getX();
        double eventY = event.getY();
        canvasH = canvas.getHeight();
        canvasW = canvas.getWidth();
        
        canvasLX = canvas.getLayoutX();
        canvasLY = canvas.getLayoutY();
        //if(eventY > scene.getHeight()-canvas.getHeight())
        if(eventX>canvasLX && eventX < canvasLX+canvasW && eventY>canvasLY && eventY<canvasLY+canvasH)
        {
            isSaved = false;
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
    
    /**
     * Задать лабиринт для отображения
     * @param mz 
     */
    public void setMaze(Maze mz){
        maze = mz;
        drawer.Draw2D(canvas, maze, true);
    }
    
    /**
     * Перерисовать лабиринт
     */
    public void redraw(){
        drawer.Draw2D(canvas, maze, true);
    }
    
    /**
     * Возвращает true, если с последнего сохранения не производилось изменений
     * @return 
     */
    public boolean isSaved() {
        return isSaved;
    }
    
    /**
     * Сообщает обработчику о сохранении лабиринта
     * @param isSaved 
     */
    public void setSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }
    
}
