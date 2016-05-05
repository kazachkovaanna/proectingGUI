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
 *
 * @author Ann
 */
public class FXMLMainMenuController implements Initializable {
    
    
    @FXML
    private void handleLevelButtonAction(ActionEvent event) throws IOException {
        Loader.loadLevelChoice();
    }
    
    @FXML
    private void handlePlayerButtonAction(ActionEvent event) throws IOException {
        Loader.loadPlayerChoice();
    }
    
    @FXML
    private void handleSettingsButtonAction(ActionEvent event) throws IOException {
        Loader.loadSettings();
    }
    
    @FXML
    private void handleEditorButtonAction(ActionEvent event) throws IOException {
        Loader.loadEditor();
    }
    
    @FXML
    private void onExit() {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
