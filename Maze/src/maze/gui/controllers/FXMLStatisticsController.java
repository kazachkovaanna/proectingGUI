/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import maze.gui.loader.Loader;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLStatisticsController implements Initializable {

    @FXML
    private Label info;
    @FXML
    private ChoiceBox level;
    private ObservableList<String> items;
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        Loader.loadPlayerChoice();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File levelsDir = new File ("./src/levels");
        File [] users = levelsDir.listFiles();
        items =FXCollections.observableArrayList();
        for(File f : users){
            items.add(f.getName());
        }
        level.setItems(items);
        level.setOnAction((Event event) -> {
            
        });
    }    
    
}
