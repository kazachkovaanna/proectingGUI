/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.gui.controllers.gamehandler;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import maze.gui.controllers.FXMLGameProcessController;

/**
 *
 * @author Ann
 */
public class GameHandler implements EventHandler<KeyEvent>{

    FXMLGameProcessController controller;
    private ArrayList input;
    
    public GameHandler(FXMLGameProcessController controller){
        this.controller = controller;
        input = new ArrayList();
    }
    @Override
    public void handle(KeyEvent event) {
        String code = event.getCode().toString();
        if(code.equals("ESCAPE")){
            if(controller.isPause()){
                controller.unPause();
            }
            else{
                controller.pause();
            }
        }
        System.out.println(code); //ESCAPE
        // чтобы добавить только один раз
        if ( !input.contains(code) )
            input.add( code );
    }
    
}

