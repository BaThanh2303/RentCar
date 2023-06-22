package RentCar.controller;

import RentCar.Main;
import RentCar.entity.Customers;
import RentCar.repository.CusRepository;
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
    public TableView<Customers> tbViewHome;
    public TableColumn<Customers, Integer> CusId;
    public TableColumn<Customers, String> CusName;
    public TableColumn<Customers, String> CusTel;
    public TableColumn<Customers, String> Brand;
    public TableColumn<Customers, String> Model;
    public  TableColumn<Customers, String> License;
    public TableColumn<Customers, Date> DateReturned;
    public TableColumn<Customers, Double> Price;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CusId.setCellValueFactory(new PropertyValueFactory<>("CusId"));
        CusName.setCellValueFactory(new PropertyValueFactory<>("CusName"));
        CusTel.setCellValueFactory(new PropertyValueFactory<>("CusTel"));
        Brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        License.setCellValueFactory(new PropertyValueFactory<>("License"));
        DateReturned.setCellValueFactory(new PropertyValueFactory<>("DateReturned"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        try {
            ObservableList<Customers> listCus = FXCollections.observableArrayList();
            listCus.addAll(CusRepository.getInstance().getAll());
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

    public void NewCar(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/newcar.fxml"));
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
