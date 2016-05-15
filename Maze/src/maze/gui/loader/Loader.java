package maze.gui.loader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import maze.gui.controllers.FXMLEditorController;
import maze.gui.controllers.FXMLGameProcessController;
import maze.gui.controllers.FXMLMainMenuController;

/**
 *
 * @author Ann
 */
public class Loader {
    private static Stage stage;             //Окно на котором отрисовывается
    private static Parent mainMenu;         //Главное меню
    private static Parent levelChoice;      //Выбор уровня
    private static Parent gameLevel;        //Прохождение уровня
    private static FXMLGameProcessController gameController;   //Ссылка на контроллер прохождения уровней
    private static FXMLEditorController editorController;      //Ссылка на контроллер редактировани уровней
    private static Parent playerChoice;     //Выбор игрока
    private static Parent statistics;       //Статистика игрока
    private static Parent editor;           //Редактор уровней
    private static Parent settings;         //Настройки
    private static StackPane stack;
    private static FXMLLoader FXMLLoader;
    /**
     * Инициализирует экземпляр класса загрузчик
     * Загрузчик всем возвращается один и тот же
     * @param stage 
    */
    public static void initLoader(Stage stage){
        stack = new StackPane();
        Loader.stage = stage;
        Loader.stage.setFullScreen(true);
        Loader.stage.setScene(new Scene(stack));
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
        FXMLLoader = new FXMLLoader();
        FXMLLoader.setBuilderFactory(new JavaFXBuilderFactory());
    }
    
    private static void loadScreen(Parent p) {
        DoubleProperty opacity = stack.opacityProperty();
        if(stack.getChildren().isEmpty()) {
            stack.setOpacity(0.0); 
            stack.getChildren().add(p);  
            Timeline fadeIn = new Timeline( 
             new KeyFrame(Duration.ZERO, 
                          new KeyValue(opacity, 0.0)), 
             new KeyFrame(new Duration(1500), 
                          new KeyValue(opacity, 1.0))); 
            fadeIn.play(); 
        } else {
            Timeline fade = new Timeline( 
           new KeyFrame(Duration.ZERO, 
                        new KeyValue(opacity,1.0)), 
           new KeyFrame(new Duration(500), 

               new EventHandler() { 
                 @Override 
                 public void handle(Event t) { 
                   //remove displayed screen 
                   stack.getChildren().remove(0); 
                   //add new screen 
                   stack.getChildren().add(0, p); 
                   Timeline fadeIn = new Timeline( 
                       new KeyFrame(Duration.ZERO, 
                              new KeyValue(opacity, 0.0)), 
                       new KeyFrame(new Duration(500), 
                              new KeyValue(opacity, 1.0))); 
                   fadeIn.play(); 
                 } 
               }, new KeyValue(opacity, 0.0))); 
            fade.play(); 
        }
    }
    
    /**
     * Загружает окно главного меню. Если такого окна еще не существует, создает его
     * @throws java.io.IOException
     */
    public static void loadMainMenu() throws IOException{   
        if(mainMenu == null){ mainMenu = FXMLLoader.load(Loader.class.getResource("/maze/gui/loader/mainMenu/FXMLMainMenu.fxml"));
        }
        loadScreen(mainMenu);
    }
    
    /**
     * Загружает окно выбора уровня. Если такого окна еще не существует, создает его
     * @throws java.io.IOException
     */
    public static void loadLevelChoice() throws IOException{
        if(levelChoice == null){
            levelChoice = FXMLLoader.load(Loader.class.getResource("levelChoice/FXMLLevelChoice.fxml"));        
        }
        loadScreen(levelChoice);
    }
    
    /**
     * Загружает окно прохождения уровня. Если такого окна еще не существует, создает его
     * @param level
     * @throws java.io.IOException
     */
    public static void loadGameLevel(File level) throws IOException{
        if(gameLevel == null){
            gameLevel = FXMLLoader.load(Loader.class.getResource("gameProcess/FXMLGameProcess.fxml").openStream());            
            gameController = FXMLLoader.getController();
        }
        gameController.setLevel(level);
        loadScreen(gameLevel);
    }
    
    /**
     * Загружает окно выбора игрока. Если такого окна еще не существует, создает его
     * @throws java.io.IOException
     */
    public static void loadPlayerChoice() throws IOException{
        if(playerChoice == null){       
            playerChoice = FXMLLoader.load(Loader.class.getResource("playerChoice/FXMLPlayerChoice.fxml"));
        }
        loadScreen(playerChoice);
    }
    
    /**
     * Загружает окно статистики игрока. Если такого окна еще не существует, создает его
     * @throws java.io.IOException
     */
    public static void loadStatistics() throws IOException{
        if(statistics == null){   
            statistics = FXMLLoader.load(Loader.class.getResource("statistics/FXMLStatistics.fxml"));
        }
        loadScreen(statistics);
    }
    
    /**
     * Загружает окно редактора уровней. Если такого окна еще не существует, создает его
     * @throws java.io.IOException
     */
    public static void loadEditor() throws IOException{
        //Как и прохождение игры, для обработки нажатий на клавиши, передает контроллеру ссылку на сцену
        if(editor == null){
            editor = FXMLLoader.load(Loader.class.getResource("editor/FXMLEditor.fxml"));
        }
        loadScreen(editor);
    }
    
    /**
     * Загружает окно настроек. Если такого окна еще не существует, создает его
     * @throws java.io.IOException
     */
    public static void loadSettings() throws IOException{
        if(statistics == null){
            settings = FXMLLoader.load(Loader.class.getResource("settings/FXMLSettings.fxml"));
        }
        loadScreen(settings);
    }
    
    /**
     * Метод изменяет свойства окна в соответствии с параметром mode
     * @param mode, указывает тип окна из перечисления ...
     */
    public static void changeStage(Integer mode) /*throws ChoiseModeException*/{
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