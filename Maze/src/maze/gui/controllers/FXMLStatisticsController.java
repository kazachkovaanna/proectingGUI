/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import maze.gui.loader.Loader;
import statistics.Statistics;
import statistics.User;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLStatisticsController implements Initializable {

    @FXML
    private Label info;
    private ObservableList<Statistics> data;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<Statistics, String> level;
    @FXML
    private TableColumn<Statistics, Number> time;
    @FXML
    private TableColumn<Statistics, Number> steps;
    @FXML
    private TableColumn<Statistics, Number> hints;
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        Loader.loadPlayerChoice();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        level.setCellValueFactory(cellData ->  cellData.getValue().levelNameProperty());
        time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        steps.setCellValueFactory(cellData -> cellData.getValue().stepProperty());
        hints.setCellValueFactory(cellData -> cellData.getValue().helpAmountProperty());
        table.setItems(User.data);
    }    
    
}
