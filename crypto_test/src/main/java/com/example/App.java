package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.paint.Color;


public class App extends Application {

    private static Scene scene;

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws IOException{

        MainWindow pane = new MainWindow(); // Creates the pane
        // visual alignments - center, color, window size
        Scene scene = new Scene(pane, 650, 250, Color.LIGHTCORAL);

        // basic setup of window
        primaryStage.setTitle("AES Encryption App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    static void launchApp(){
        launch();
    }
}