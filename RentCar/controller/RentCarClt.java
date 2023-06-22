package RentCar.controller;


import RentCar.Main;
import RentCar.entity.Car;
import RentCar.entity.Customers;
import RentCar.repository.CarRepository;
import RentCar.repository.CusRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static RentCar.repository.CarRepository.countRentDate;
import static RentCar.repository.CarRepository.totalPrice;

public class RentCarClt implements Initializable {
    public static String BrandChooce;
    public static String ModelChooce;
    public static String LicenceChooce;
    public Text total;
    public TextField namecus;
    public TextField telcus;
    public DatePicker dateReturn;
    public DatePicker dateRented;
    public ComboBox rentCarBrand;
    public ComboBox rentCarModel;
    public ComboBox rentCarLicense;
    public TableView<Car> tbView;
    public TableColumn<Car, Integer> TCId;
    public TableColumn<Car, String> TCBrand;
    public TableColumn<Car, String> TCModel;
    public TableColumn<Car, String> TCLicense;
    public TableColumn<Car, Double> TCPrice;
    public TableColumn<Car, String> TCStatus;
    public static LocalDate returndate;
    public static LocalDate rentdate;
    public Button submit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TCId.setCellValueFactory(new PropertyValueFactory<>("Carid"));
        TCBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        TCModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        TCLicense.setCellValueFactory(new PropertyValueFactory<>("License"));
        TCPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        TCStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        try {
            ObservableList<Car> list = FXCollections.observableArrayList();
            ObservableList<String> ListBrand = FXCollections.observableArrayList();
            ListBrand.addAll(CarRepository.getListBrand());
            rentCarBrand.setItems(ListBrand);
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
            String cusName = namecus.getText();
            String cusTel = telcus.getText();
            String brand = rentCarBrand.getSelectionModel().getSelectedItem().toString();
            String model = rentCarModel.getSelectionModel().getSelectedItem().toString();
            Double price = Double.parseDouble(total.getText());
            Date date = Date.valueOf(dateReturn.getValue());
            String license = rentCarLicense.getSelectionModel().getSelectedItem().toString();
            Customers cus = new Customers(cusName, cusTel, brand, model, license, date, price);
            if (namecus.getText().isEmpty() || telcus.getText().isEmpty()){
                throw new Exception("Hãy Điền Thông Tin Khách Hàng!!");
            }else if (CusRepository.getInstance().create(cus)) {
                RentCar(null);
                throw new Exception("Đã Thêm Khách Thuê Thành Công!!!");
            } else if(cus == null) {
                throw new Exception("Hãy Điền Vào Chỗ Còn Trống!!");
            } else {
                throw new Exception("Đã Có Lỗi Xảy Ra!");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    public void Home(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/homev2.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280, 475));
    }


    public void NewCar(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/newcar.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280, 475));
    }

    public void brandChange(ActionEvent event) {
        BrandChooce = rentCarBrand.getSelectionModel().getSelectedItem().toString();
        ObservableList<String> listModel = FXCollections.observableArrayList();
        listModel.addAll(CarRepository.getlistModel());
        rentCarModel.setItems(listModel);
    }

    public void modelChange(ActionEvent event) {
        ModelChooce = rentCarModel.getSelectionModel().getSelectedItem().toString();
        ObservableList<String> listLicense = FXCollections.observableArrayList();
        listLicense.addAll(CarRepository.getlistLicense());
        rentCarLicense.setItems(listLicense);
    }

    public void dateRt(ActionEvent event) {
        returndate = dateReturn.getValue();
        countRentDate();
        total.setText(String.valueOf(totalPrice()));
    }

    public void dateRent(ActionEvent event) {
        rentdate = dateRented.getValue();

    }

    public void LicenseChange(ActionEvent event) {
        LicenceChooce = rentCarLicense.getSelectionModel().getSelectedItem().toString();
    }

    public void RentCar(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/rentcar.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280, 475));
    }

    public void SignOut(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/signin.fxml"));
        Main.mainStage.setScene(new Scene(root, 366, 503));
    }
}
