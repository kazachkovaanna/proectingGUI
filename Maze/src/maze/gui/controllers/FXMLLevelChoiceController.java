/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import maze.gui.loader.Loader;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class FXMLLevelChoiceController implements Initializable {
    @FXML
    GridPane gridPane;
    File levelsDir;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        levelsDir = new File("./src/levels");
        File[] files = levelsDir.listFiles();
        int w = files.length/2;
        int h = files.length/w;
        List l = new ArrayList();
        for(int i = 0; i<w; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setHalignment(HPos.CENTER);
            column.setHgrow(Priority.SOMETIMES);
            column.setPercentWidth(50);
            l.add(column);
        }
        gridPane.getColumnConstraints().addAll(l);
        l.clear();
        for(int i = 0; i<h-1; i++){
            RowConstraints r = new RowConstraints();
            r.setPercentHeight(50);
            r.setValignment(VPos.CENTER);
            r.setVgrow(Priority.SOMETIMES);
            l.add(r);
        }
        System.out.println(l.size());
        gridPane.getRowConstraints().addAll(l);
        int col=0, row =0;
        for(File f: files){
            Button b = new Button();
            b.setText(f.getName().replace(".maze", ""));
            b.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    try {
                        Button selected = (Button) event.getSource();
                        Loader.loadGameLevel(new File("./src/levels/"+selected.getText()+".maze"));
                    } catch (IOException ex) {
                    }
                }
                
            });
            gridPane.add(b, col, row);
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            col++;
            if(col==w){
                col = 0; row++;
            }
            for(ColumnConstraints c:gridPane.getColumnConstraints()){
                c.setHalignment(HPos.CENTER);
                c.setPercentWidth(50);
            }
        }
    }    
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        Loader.loadMainMenu();
    }
    @FXML
    private void handleLevelButtonAction(ActionEvent event) throws IOException{
        
    }
    
}

