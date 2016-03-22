package maze.gui.loader;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maze.Main;
import maze.gui.controllers.FXMLEditorController;
import maze.gui.controllers.FXMLGameProcessController;

/**
 *
 * @author Ann
 */
public class Loader {
    
    private static Loader loader;   //Экзепляр класса загрузчика - с ним будут работать все окна
    private Stage stage;            //Окно на котором отрисовывается
    private Scene mainMenu;         //Главное меню
    private Scene levelChoice;      //Выбор уровня
    private Scene gameLevel;
    private FXMLGameProcessController gameController;
    private FXMLEditorController editorController;
    private Scene playerChoice;
    private Scene statistics;
    private Scene editor;
    private Scene settings;
    private FXMLLoader fxmlLoader;
    
    public Loader(Stage stage){
        this.stage = stage;
        fxmlLoader = new FXMLLoader();
       // fxmlLoader.setLocation(Loader.class.getResource(""));
    }
    
    public static Loader initLoader(Stage stage){
        if(loader==null){
           loader = new Loader(stage); 
        }
        return loader;
    }
    
    public static Loader getLoader(){        
        return loader;
    }
    
    public void loadMainMenu() throws IOException{   
        if(mainMenu == null){
            Parent root = fxmlLoader.load(getClass().getResource("mainMenu/FXMLMainMenu.fxml"));/*);   Loader.class.getResource("mainMenu/FXMLMainMenu.fxml")     */
            Scene scene = new Scene(root);
            stage.show();
            mainMenu = scene;
        }
        stage.setScene(mainMenu);
    }
    
    public void loadLevelChoice() throws IOException{
        if(levelChoice == null){
            Parent root = fxmlLoader.load(getClass().getResource("levelChoice/FXMLLevelChoice.fxml"));        
            Scene scene = new Scene(root);
            scene.setRoot(root);
            stage.show();
            levelChoice = scene;
        }
        stage.setScene(levelChoice);
    }
    
    public void loadGameLevel() throws IOException{
        if(gameLevel == null){
            fxmlLoader = new FXMLLoader(getClass().getResource("gameProcess/FXMLGameProcess.fxml"));                
            Parent root = fxmlLoader.load();
            gameController = fxmlLoader.getController();
            Scene scene = new Scene(root);
            scene.setRoot(root);
            stage.show();
            gameLevel = scene;
            gameController.setScene(gameLevel);
        }
        stage.setScene(gameLevel);
    }
    
    public void loadPlayerChoice() throws IOException{
        if(playerChoice == null){
            Parent root = fxmlLoader.load(getClass().getResource("playerChoice/FXMLPlayerChoice.fxml"));        
            Scene scene = new Scene(root);
            scene.setRoot(root);
            stage.show();
            playerChoice = scene;
        }
        stage.setScene(playerChoice);
    }
    public void loadStatistics() throws IOException{
        if(statistics == null){
            Parent root = fxmlLoader.load(getClass().getResource("statistics/FXMLStatistics.fxml"));        
            Scene scene = new Scene(root);
            scene.setRoot(root);
            stage.show();
            statistics = scene;
        }
        stage.setScene(statistics);
    }
    
    public void loadEditor() throws IOException{
        if(editor == null){
            fxmlLoader = new FXMLLoader(getClass().getResource("editor/FXMLEditor.fxml"));
            Parent root = fxmlLoader.load();
            editorController = fxmlLoader.getController();
            Scene scene = new Scene(root);
            scene.setRoot(root);
            stage.show();
            editor = scene;
            editorController.setScene(scene);
        }
        stage.setScene(editor);
    }
    
    public void loadSettings() throws IOException{
        if(statistics == null){
            Parent root = fxmlLoader.load(getClass().getResource("settings/FXMLSettings.fxml"));        
            Scene scene = new Scene(root);
            scene.setRoot(root);
            stage.show();
            settings = scene;
        }
        stage.setScene(settings);
    }
    /**
     * 
     * @param mode, указывает тип окна из перечисления ...
     * 
     * Метод изменяет свойства окна в соответствии с параметром mode
     */
    public void changeStage(Integer mode) /*throws ChoiseModeException*/{
        switch (mode) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                //throw ChoiseModeException();
        }
    }
}

/*public class ChoiseModeException extends Exception {
    private final String message;
    
    public ChoiseModeException() {
        message = "Not supported mode";
    }
    
    public ChoiseModeException(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return message;
    }
}*/