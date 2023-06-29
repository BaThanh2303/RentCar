package RentCar.controller;

import RentCar.Main;
import RentCar.entity.RentCar;
import RentCar.repository.HomeRepository;
import RentCar.repository.RentCarRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeClt implements Initializable {
    public TableView<RentCar> tbViewHome;
    public TableColumn<RentCar, String> CusName;
    public TableColumn<RentCar, String> CusTel;
    public TableColumn<RentCar, String> Brand;
    public TableColumn<RentCar, String> Model;
    public  TableColumn<RentCar, String> License;
    public TableColumn<RentCar, Date> dateReturn;
    public TableColumn<RentCar, Date> dateRent;
    public TableColumn<RentCar, Double> price;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CusName.setCellValueFactory(new PropertyValueFactory<>("cusname"));
        CusTel.setCellValueFactory(new PropertyValueFactory<>("custel"));
        Brand.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        Model.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        License.setCellValueFactory(new PropertyValueFactory<>("carLicense"));
        dateRent.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        dateReturn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        try {
            ObservableList<RentCar> listCus = FXCollections.observableArrayList();
            listCus.addAll(HomeRepository.getInstance().getAll());
            tbViewHome.setItems(listCus);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    public void RentCar(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/rentcar.fxml"));
        Main.mainStage.setScene(new Scene(root, 1235,475));
    }

    public void ReturnCar(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/carreturn.fxml"));
        Main.mainStage.setScene(new Scene(root, 1235,475));
    }

    public void Home(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/homev2.fxml"));
        Main.mainStage.setScene(new Scene(root, 1235,475));
    }

    public void SignOut(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/signin.fxml"));
        Main.mainStage.setScene(new Scene(root, 366, 503));
    }
}
