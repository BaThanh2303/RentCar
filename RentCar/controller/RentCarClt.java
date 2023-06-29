package RentCar.controller;


import RentCar.Main;
import RentCar.entity.Car;
import RentCar.entity.RentCar;
import RentCar.repository.CarRepository;
import RentCar.repository.RentCarRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static RentCar.repository.CarRepository.countRentDate;
import static RentCar.repository.CarRepository.totalPrice;

public class RentCarClt implements Initializable {
    public Text total;
    public TextField namecus;
    public TextField telcus;
    public TextField Carid;
    public TextField License;
    public TextField Model;
    public static String ModelSelect;
    public TextField Brand;

    public DatePicker dateReturn;
    public TableView<Car> tbView;
    public TableColumn<Car, Integer> TCId;
    public TableColumn<Car, String> TCBrand;
    public TableColumn<Car, String> TCModel;
    public TableColumn<Car, String> TCLicense;
    public TableColumn<Car, Double> TCPrice;
    public TableColumn<Car, String> TCStatus;
    public static LocalDate returndate;
    public static LocalDate rentdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TCId.setCellValueFactory(new PropertyValueFactory<>("Carid"));
        TCBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        TCModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        TCLicense.setCellValueFactory(new PropertyValueFactory<>("License"));
        TCPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        TCStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        rentdate = LocalDate.now();
       try {
            ObservableList<Car> list = FXCollections.observableArrayList();
            list.addAll(CarRepository.getInstance().getAll());
            tbView.setItems(list);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    public void Submit(ActionEvent actionEvent) throws Exception {
        try {
            int carid = Integer.parseInt(Carid.getText());
            String cusName = namecus.getText();
            int cusTel = Integer.parseInt(telcus.getText());
            String brand = Brand.getText();
            String model = Model.getText();
            String license = License.getText();
            Date rentdate = Date.valueOf(LocalDate.now());
            Date returndate = Date.valueOf(dateReturn.getValue());
            Double price = Double.valueOf(totalPrice());
            String status = "Not Available";
            String rental = "Renting";
            RentCar rentCar = new RentCar(carid,cusName,cusTel,rentdate,returndate,price,rental);
            Car car = new Car(carid,brand,model,license,price,status);
            if (namecus.getText().isEmpty() || telcus.getText().isEmpty()){
                throw new Exception("Hãy Điền Thông Tin Khách Hàng!!");
            }else if (RentCarRepository.getInstance().create(rentCar)) {
                CarRepository.getInstance().update(car);
                RentCar(null);
                throw new Exception("Đã Thêm Khách Thuê Thành Công!!!");
            } else if(Brand.getText().isEmpty()) {
                throw new Exception("Hãy Chọn Xe!!");
            } else {
                throw new Exception("Đã Có Lỗi Xảy Ra!");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    public void Home(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/homev2.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280, 475));
    }


    public void ReturnCar(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/carreturn.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280, 475));
    }

    public void RentCar(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/rentcar.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280, 475));
    }

    public void SignOut(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/signin.fxml"));
        Main.mainStage.setScene(new Scene(root, 366, 503));
    }

    public void SelectCar(MouseEvent mouseEvent) {
        Car carsl = tbView.getSelectionModel().getSelectedItem();
        if (carsl != null) {
            Brand.setText(carsl.getBrand());
            Model.setText(carsl.getModel());
            License.setText(carsl.getLicense());
            Carid.setText(String.valueOf(carsl.getCarid()));
            ModelSelect = carsl.getModel();
        }
    }

    public void dateReturnSelect(ActionEvent event) {
        returndate = dateReturn.getValue();
        System.out.println(returndate);
        countRentDate();
        total.setText(String.valueOf(totalPrice()) + "$");
    }
}
