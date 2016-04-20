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
import maze.gui.loader.Loader;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLPlayerChoiceController implements Initializable {

    @FXML
    private void handleOkButtonAction(ActionEvent event) throws IOException{
        Loader.getLoader().loadMainMenu();
    }
    @FXML
    private void handleCancelButtonAction(ActionEvent event) throws IOException{
        Loader.getLoader().loadMainMenu();
    }
    @FXML
    private void handleStatisticsButtonAction(ActionEvent event) throws IOException{
        Loader.getLoader().loadStatistics();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
