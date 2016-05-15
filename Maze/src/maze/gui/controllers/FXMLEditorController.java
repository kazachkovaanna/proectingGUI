/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers;

import IOStreamMaze.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import maze.Maze;
import maze.Point;
import maze.gui.controllers.editor.*;
import maze.gui.loader.Loader;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLEditorController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private Canvas editArea;            //Ссылка на canvas, в котором рисуется лабиринт
    private Scene scene;
    private EditHandler handler;    //Обработчик нажатий
    private Maze maze;          //Изображаемый лабиринт
    FileChooser fileChooser;    //Окно выбора файла
    OutputStream mazeSaver;     //Поток, сохраняющий лабиринт
    InputStream mazeLoader;     //Поток, загружающий лабиринт
    
    
    /**
     * Инициализирует контроллер
     * Рисует новый пустой лабиринт и настраивает выбор файлов
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DrawMaze drawer = new DrawMazeImpl();       //При инициализации надо отрисовать лабиринт впервые
        maze = new Maze();                          //Пустой лабиринт по умолчанию
        drawer.Draw2D(editArea, maze, true);        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(   //Связывет название файлов с расширением и ищет в директории только их
                new FileChooser.ExtensionFilter("Maze", "*.maze")
            );

        fileChooser.setInitialDirectory(            //директория по умолчанию
                new File("./src/levels")
        );
        pane.heightProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    onResize(0, newValue.doubleValue());
                }
            );
        pane.widthProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    onResize(newValue.doubleValue(), 0);
                }
            );
        handler = new EditHandler(this, editArea,maze);
        editArea.setOnMouseClicked(handler);
        /// костыль!!!!! исправить!!!!!!!!!!!!!!!!!!!!!!!
       // setScene(new Scene(pane.getParent()));
    }
    /**
     * Спрашивает пользователя, надо ли сохранить созданный лабиринт и сохраяет его
     */
    private void askSaving(){
        if(!handler.isSaved()){
            Alert saving = new Alert(AlertType.CONFIRMATION);   //Диалоговое окно с OK и Cancel
            saving.setTitle("Сохранение");
            saving.setHeaderText("Сохранить лабиринт?");
            Optional<ButtonType> result = saving.showAndWait();
            if (result.get() == ButtonType.OK){
                handleSaveButtonAction(new ActionEvent());
            } 
        }
    }
    
    /**
     * Обработка события нажатия на кнопку Назад
     * Спрашивает, сохранить ли файл и выходит в меню
     * @param event - событие нажатия
     */
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        askSaving();
        Loader.loadMainMenu();
    }
    
    /**
     * Обработка события нажатия на кнопку Сохранить
     * @param event - событие нажатия
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event){
        List<Point> solution = maze.getPath(maze.getStart());
        if(solution == null || solution.isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Нет пути к выходу!!!");
            alert.setHeaderText("В созданном лабиринте невозможно построить путь к выходу!");
            alert.setContentText("Лабиринт не будет сохранен!");
            alert.showAndWait();
            return;
        }
        DrawMaze d = new DrawMazeImpl();
        solution.remove(0);
        d.DrawSolution(editArea, maze, true, solution, solution.size());
        File file = fileChooser.showSaveDialog(null);
        if (null == file) return;
        try {
            OutputStream.setMaze(maze, file.getPath());
            handler.setSaved(true);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Обработка события нажатия на кнопку Загрузить
     * @param event - событие нажатия
     */
    @FXML
    private void handleLoadButtonAction(ActionEvent event){
        askSaving();
        File file = fileChooser.showOpenDialog(null);
        if (null == file) return;
        try {
            maze = InputStream.getMaze(file);
            handler.setMaze(maze);
        } catch (IOException ex) {
        }
    }
    
    /**
     * Обработка события нажатия на кнопку Ширина...
     * @param event - событие нажатия
     */
    @FXML
    private void handleNewWidthAction(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Задайте ширину");
        dialog.setHeaderText("Задайте новое значение");
        dialog.setContentText("Новая ширина:");

        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            maze.setWeight(Integer.parseInt(result.get()));
        }
        handler.redraw();
    }
    
    /**
     * Обработка события нажатия на кнопку Высота...
     * @param event - событие нажатия
     */
    @FXML
    private void handleNewHeightAction(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Задайте высоту");
        dialog.setHeaderText("Задайте новое значение");
        dialog.setContentText("Новая высота:");

        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            maze.setHight(Integer.parseInt(result.get()));
        }
        handler.redraw();
    }
    
    /**
     * Настройка сцены (необходима для прикрепления к сцене обработчика нажатий мыши
     * @param sc - новая сцена
     */
    public void setScene(Scene sc){
        scene = sc;
        
       // scene.setOnMouseClicked(handler);
       
    }
    
    public void onResize(double w, double h) {
        if (h > 0) editArea.setHeight(h);
        if (w > 0) editArea.setWidth(w);
        handler.redraw();
    }
}
