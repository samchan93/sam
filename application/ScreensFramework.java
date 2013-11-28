package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
	
public class ScreensFramework extends Application {
    
    public static String screen1ID = "login";
    public static String screen1File = "login.fxml";
    public static String screen2ID = "main";
    public static String screen2File = "guicw1.fxml";

    
    
    public void start(Stage primaryStage) throws IOException {	//set mainStage
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
   
        
        mainContainer.setScreen(ScreensFramework.screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Personal Trainer");
        primaryStage.setResizable(false);
        primaryStage.setWidth(600);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("myicon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch (args);
    }

	
}