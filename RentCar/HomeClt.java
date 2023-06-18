package RentCar;

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
    public  TableColumn<Customers, String> Bien;
    public TableColumn<Customers, Date> DateReturned;
    public TableColumn<Customers, Double> Price;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CusId.setCellValueFactory(new PropertyValueFactory<>("CusId"));
        CusName.setCellValueFactory(new PropertyValueFactory<>("CusName"));
        CusTel.setCellValueFactory(new PropertyValueFactory<>("CusTel"));
        Brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Bien.setCellValueFactory(new PropertyValueFactory<>("Bien"));
        DateReturned.setCellValueFactory(new PropertyValueFactory<>("DateReturned"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        try {
            ObservableList<Customers> listCus = FXCollections.observableArrayList();
            listCus.addAll(CusRepository.getInstance().getAll());
            System.out.println(listCus);
            tbViewHome.setItems(listCus);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    public void ThueXe(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("thuexe.fxml"));
        Main.mainStage.setScene(new Scene(root, 1235,475));
    }
}
