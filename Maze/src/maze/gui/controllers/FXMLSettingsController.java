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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import maze.gui.loader.Loader;
import statistics.User;
/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLSettingsController implements Initializable {

    @FXML
    private ColorPicker colorPicker;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleOkButtonAction(ActionEvent event) throws IOException{
        Loader.loadMainMenu();
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event) throws IOException{
        Loader.loadMainMenu();
    }
    
    @FXML
    private void handleSetWallColor(ActionEvent event){
        Color c = colorPicker.getValue();
        System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
    }

    
    /*colorPicker.setOnAction(new EventHandler() {
        public void handle(Event t) {
             Color c = colorPicker.getValue();
             System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
         }
    });*/

}
