package maze.gui.loader;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maze.gui.controllers.FXMLEditorController;
import maze.gui.controllers.FXMLGameProcessController;

/**
 *
 * @author Ann
 */
public class Loader {
    
    private static Loader loader;   //Экзепляр класса загрузчика - с ним будут работать все окна
    private final Stage stage;      //Окно на котором отрисовывается
    private Scene mainMenu;         //Главное меню
    private Scene levelChoice;      //Выбор уровня
    private Scene gameLevel;        //Прохождение уровня
    private FXMLGameProcessController gameController;   //Ссылка на контроллер прохождения уровней
    private FXMLEditorController editorController;      //Ссылка на контроллер редактировани уровней
    private Scene playerChoice;     //Выбор игрока
    private Scene statistics;       //Статистика игрока
    private Scene editor;           //Редактор уровней
    private Scene settings;         //Настройки
    private FXMLLoader fxmlLoader;  //Загрузчик
    
    public Loader(Stage stage){
        this.stage = stage;
        fxmlLoader = new FXMLLoader();
    }
    
    /**
     * Инициализирует экземпляр класса загрузчик
     * Загрузчик всем возвращается один и тот же
    */
    public static Loader initLoader(Stage stage){
        if(loader==null){
           loader = new Loader(stage); 
        }
        return loader;
    }
    
    /**
     * Возвращает загрузчик, с которым работают все контроллеры
    */
    public static Loader getLoader(){        
        return loader;
    }
    
    /**
     * Загружает окно главного меню. Если такого окна еще не существует, создает его
     */
    public void loadMainMenu() throws IOException{   
        if(mainMenu == null){
            Parent root = fxmlLoader.load(getClass().getResource("mainMenu/FXMLMainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.show();
            mainMenu = scene;
        }
        stage.setScene(mainMenu);
    }
    
    /**
     * Загружает окно выбора уровня. Если такого окна еще не существует, создает его
     */
    public void loadLevelChoice() throws IOException{
        if(levelChoice == null){
            Parent root = fxmlLoader.load(getClass().getResource("levelChoice/FXMLLevelChoice.fxml"));        
            Scene scene = new Scene(root);
            scene.setRoot(root);
            levelChoice = scene;
        }
        stage.setScene(levelChoice);
    }
    
    /**
     * Загружает окно прохождения уровня. Если такого окна еще не существует, создает его
     */
    public void loadGameLevel(File level) throws IOException{
        if(gameLevel == null){
            fxmlLoader = new FXMLLoader(getClass().getResource("gameProcess/FXMLGameProcess.fxml"));                
            Parent root = fxmlLoader.load();
            gameController = fxmlLoader.getController(); //Чтобы задать обработчик нажатий, контроллер должен иметь ссылку на сцену
            Scene scene = new Scene(root);  
            scene.setRoot(root);
            gameLevel = scene;
            gameController.setScene(gameLevel); //Поэтому контроллеру после его инициализации передается ссылка на сцену
        }
        gameController.setLevel(level);
        stage.setScene(gameLevel);
    }
    
    /**
     * Загружает окно выбора игрока. Если такого окна еще не существует, создает его
     */
    public void loadPlayerChoice() throws IOException{
        if(playerChoice == null){
            Parent root = fxmlLoader.load(getClass().getResource("playerChoice/FXMLPlayerChoice.fxml"));        
            Scene scene = new Scene(root);
            scene.setRoot(root);
            playerChoice = scene;
        }
        stage.setScene(playerChoice);
    }
    
    /**
     * Загружает окно статистики игрока. Если такого окна еще не существует, создает его
     */
    public void loadStatistics() throws IOException{
        if(statistics == null){
            Parent root = fxmlLoader.load(getClass().getResource("statistics/FXMLStatistics.fxml"));        
            Scene scene = new Scene(root);
            scene.setRoot(root);
            statistics = scene;
        }
        stage.setScene(statistics);
    }
    
    /**
     * Загружает окно редактора уровней. Если такого окна еще не существует, создает его
     */
    public void loadEditor() throws IOException{
        //Как и прохождение игры, для обработки нажатий на клавиши, передает контроллеру ссылку на сцену
        if(editor == null){
            fxmlLoader = new FXMLLoader(getClass().getResource("editor/FXMLEditor.fxml"));
            Parent root = fxmlLoader.load();
            editorController = fxmlLoader.getController();
            Scene scene = new Scene(root);
            scene.setRoot(root);
            editor = scene;
            editorController.setScene(scene);
        }
        stage.setScene(editor);
    }
    
    /**
     * Загружает окно настроек. Если такого окна еще не существует, создает его
     */
    public void loadSettings() throws IOException{
        if(statistics == null){
            Parent root = fxmlLoader.load(getClass().getResource("settings/FXMLSettings.fxml"));        
            Scene scene = new Scene(root);
            scene.setRoot(root);
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