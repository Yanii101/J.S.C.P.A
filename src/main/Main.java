package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myclass.Client;
import myclass.Intervention;
import myclass.Visit;

/**Yaneeke Pollock-1500625...I did most of the coding so I wrote my name only once**/

public class Main extends Application {

    /**Intervention visit;
     * visit=new Visit;
     * the code above has shown how to use polymorphism however it can polymorphis can only occur in main
     * and the visit and removal object was not used in based on our design*/
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/controller.fxml"));
        primaryStage.setTitle("J.S.C.P.A");
        primaryStage.setScene(new Scene(root, 520, 570));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
