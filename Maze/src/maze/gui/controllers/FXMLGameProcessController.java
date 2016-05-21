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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import maze.Maze;
import maze.gui.controllers.gamehandler.GameHandler;
import maze.gui.loader.Loader;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLGameProcessController implements Initializable {

    private boolean pause;
    private boolean finish;
    @FXML
    private Pane pane;
    @FXML
    private VBox pauseBox;
    @FXML
    private Canvas canvas;
    @FXML
    private Button back;
    @FXML
    private Button play;
    @FXML
    private Button hint;
    private GameHandler handler;
    private Maze maze;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unPause();
        handler = new GameHandler(this, canvas, maze);
        canvas.setOnKeyPressed(handler);
        canvas.setFocusTraversable(true);
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
    }
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        Loader.loadLevelChoice();
    }
    
    @FXML
    private void handleContinueButtonAction(ActionEvent event){
        this.unPause();
    }
    
    @FXML
    private void getHint(ActionEvent event){
        unPause();
        handler.getHint();
        
    }
    
    public void setLevel(File level){
        try {
            maze = InputStream.getMaze(level);
            handler.setMaze(maze);
            finish = false;
            unPause();
            hint.setDisable(false);
            play.setDisable(false);
        } catch (IOException ex) {
        }
    }
    
    public void finish(){
        finish = true;
        hint.setDisable(true);
        play.setDisable(true);
        pause();
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Уровень пройден!");
        dialog.setHeaderText("Уровень пройден!");
        dialog.setContentText("Число шагов: "+ handler.getSteps() + "\nЧисло подсказок: "+handler.getHints());
        dialog.showAndWait();
    }
    
    public boolean isFinish(){
        return finish;
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
    
    public void onResize(double w, double h) {
        if (h > 0) canvas.setHeight(h);
        if (w > 0) canvas.setWidth(w);
        handler.redraw();
    }
}
