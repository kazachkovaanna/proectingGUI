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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import maze.Maze;
import maze.gui.controllers.editor.*;
import maze.gui.loader.Loader;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLEditorController implements Initializable {

    @FXML
    Canvas editArea;            //Ссылка на canvas, в котором рисуется лабиринт
    private Scene scene;        //Ссылка на сцену, для обработки нажатий
    private EditHandler handler;    //Обработчик нажатий
    private Maze maze;          //Изображаемый лабиринт
    FileChooser fileChooser;    //Окно выбора файла
    OutputStream mazeSaver;     //Поток, сохраняющий лабиринт
    InputStream mazeLoader;     //Поток, загружающий лабиринт
    
    
    /**
     * Инициализирует контроллер
     * Рисует новый пустой лабиринт и настраивает выбор файлов
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
        
    }
    private void askSaving(){
        if(!handler.isSaved()){
            Alert saving = new Alert(AlertType.CONFIRMATION);
            saving.setTitle("Сохранение");
            saving.setHeaderText("Сохранить лабиринт?");
            Optional<ButtonType> result = saving.showAndWait();
            if (result.get() == ButtonType.OK){
                handleSaveButtonAction(new ActionEvent());
            } 
        }
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        askSaving();
        Loader.getLoader().loadMainMenu();
    }
    
    @FXML
    private void handleSaveButtonAction(ActionEvent event){
        File file = fileChooser.showSaveDialog(null);
        if (null == file) return;
        try {
            OutputStream.setMaze(maze, file.getPath());
            handler.setSaved(true);
        } catch (IOException ex) {
            
        }
    }
    @FXML
    private void handleLoadButtonAction(ActionEvent event){
        askSaving();
        File file = fileChooser.showOpenDialog(null);
        if (null == file) return;
        try {
            Maze m = InputStream.getMaze(file);
            handler.setMaze(m);
        } catch (IOException ex) {
        }
    }
    @FXML
    private void handleNewWidthAction(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Задайте ширину");
        dialog.setHeaderText("Задайте новое значение");
        dialog.setContentText("Новая ширина:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            maze.setWeight(Integer.parseInt(result.get()));
        }
        handler.redraw();
    }
    @FXML
    private void handleNewHeightAction(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Задайте высоту");
        dialog.setHeaderText("Задайте новое значение");
        dialog.setContentText("Новая высота:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            maze.setHight(Integer.parseInt(result.get()));
        }
        handler.redraw();
    }
    
    public void setScene(Scene sc){
        scene = sc;
        handler = new EditHandler(this, editArea, scene, maze);
        scene.setOnMouseClicked(handler);
       
    }
    
    
}
