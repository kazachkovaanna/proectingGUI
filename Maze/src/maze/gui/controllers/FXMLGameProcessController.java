/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers;

import IOStreamMaze.InputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import maze.Maze;
import maze.gui.controllers.editor.DrawMaze;
import maze.gui.controllers.editor.DrawMazeImpl;
import maze.gui.controllers.gamehandler.GameHandler;
import maze.gui.loader.Loader;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLGameProcessController implements Initializable {

    private boolean pause;      //true РµСЃР»Рё РёРіСЂР° РЅР° РїР°СѓР·Рµ
    private Scene scene;
    @FXML
    private VBox pauseBox;
    @FXML
    private Canvas canvas;
    private DrawMaze drawer;
    private Maze maze;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawer = new DrawMazeImpl();        
    }
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        Loader.getLoader().loadMainMenu();
    }
    
    @FXML
    private void handleContinueButtonAction(ActionEvent event){
        this.unPause();
    }
    
    public void setScene(Scene sc){
        scene = sc;
        scene.setOnKeyPressed(new GameHandler(this));
    }
    
    public void setLevel(File level){
        try {
            maze = InputStream.getMaze(level);
        } catch (IOException ex) {
        }
    }
    
    public void pause(){
        pause = true;
        pauseBox.setVisible(pause);
    }
    
    public void unPause(){
        pause = false;
        pauseBox.setVisible(pause);
    }
    
    public boolean isPause(){
        return pause;
    }
    
}
