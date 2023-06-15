package RentCar;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        ObservableList<String> list = FXCollections.observableArrayList();
        System.out.println(list);
        launch(args);

    }
    public static Stage mainStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("thuexe.fxml"));
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
    }
}
