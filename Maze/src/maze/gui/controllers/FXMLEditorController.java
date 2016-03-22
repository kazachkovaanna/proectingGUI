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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
    Canvas editArea;
    private Scene scene;
    private EditHandler handler;
    private Maze maze;
    FileChooser fileChooser;
    OutputStream mazeSaver;
    InputStream mazeLoader;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DrawMaze drawer = new DrawMazeImpl(); 
        maze = new Maze();
        drawer.Draw2D(editArea, maze, true);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Maze", "*.maze")
            );
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );
        
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        Loader.getLoader().loadMainMenu();
    }
    
    @FXML
    private void handleSaveButtonAction(ActionEvent event){
        File file = fileChooser.showSaveDialog(null);
        try {
            OutputStream.setMaze(maze, file.getPath());
        } catch (IOException ex) {
            
        }
    }
    @FXML
    private void handleLoadButtonAction(ActionEvent event){
        File file = fileChooser.showOpenDialog(null);
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
