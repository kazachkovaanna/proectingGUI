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
    private ColorPicker walls;
    @FXML
    private ColorPicker travel;
    @FXML
    private ColorPicker start;
    @FXML
    private ColorPicker finish;
    @FXML
    private ColorPicker hasTravelled;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        walls.setValue(User.getUserWallColor());
        travel.setValue(User.getUserTravelColor());
        start.setValue(User.getUserStartColor());
        finish.setValue(User.getUserFinishColor());
        hasTravelled.setValue(User.getUserDistanceTravelColor());
    }    
    
    @FXML
    private void handleOkButtonAction(ActionEvent event) throws IOException{
        Color c = walls.getValue();
        User.setUserWallColor(c);
        c = travel.getValue();
        User.setUserTravelColor(c);
        c = start.getValue();
        User.setUserStartColor(c);
        c = finish.getValue();
        User.setUserFinishColor(c);
        c = hasTravelled.getValue();
        User.setUserDistanceTravelColor(c);
        Loader.loadMainMenu();
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event) throws IOException{
        Loader.loadMainMenu();
    }
    
    

    
    /*colorPicker.setOnAction(new EventHandler() {
        public void handle(Event t) {
             Color c = colorPicker.getValue();
             System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
         }
    });*/

}
