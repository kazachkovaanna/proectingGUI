/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import maze.gui.loader.Loader;
import statistics.OutputStreamStatistics;
import statistics.User;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLPlayerChoiceController implements Initializable {
    private ObservableList<String> items;
    @FXML
    private ListView list;
    
    @FXML
    private void handleOkButtonAction(ActionEvent event) throws IOException{
        String name = (String) list.getSelectionModel().getSelectedItems().get(0);
        if(name != null){
            File f = new File("./src/users/"+name);
            User.get(f);
            
        }
        Loader.loadMainMenu();
    }
    @FXML
    private void handleCancelButtonAction(ActionEvent event) throws IOException{
        Loader.loadMainMenu();
    }
    @FXML
    private void handleStatisticsButtonAction(ActionEvent event) throws IOException{
        Loader.loadStatistics();
    }
    
    @FXML
    private void handleNewPlayer(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("Введите имя");
        dialog.setTitle("Новый игрок");
        dialog.setHeaderText("Введите имя нового игрока");
        dialog.setContentText("Ваше имя:");

        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try {
                User.newUser(result.get());
                User.write();
                items.add(result.get());
            } catch (IOException ex) {
            }
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File usersDir = new File ("./src/users");
        File [] users = usersDir.listFiles();
        items =FXCollections.observableArrayList();
        for(File f : users){
            items.add(f.getName());
        }
        list.setItems(items);
    }    
    
}
