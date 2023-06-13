package RentCar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeClt implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void ThueXe(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("thuexe.fxml"));
        Main.mainStage.setScene(new Scene(root, 1200,600));
    }
}
