/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import maze.gui.controllers.gamehandler.GameHandler;
import maze.gui.loader.Loader;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLGameProcessController implements Initializable {

    private boolean pause;      //true если игра на паузе
    private Scene scene;
    @FXML
    private VBox pauseBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
    
    public void setLevel(int level){
        
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
