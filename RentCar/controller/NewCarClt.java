package RentCar.controller;

import RentCar.Main;
import RentCar.entity.Car;
import RentCar.repository.CarRepository;
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

import java.net.URL;
import java.util.ResourceBundle;

public class NewCarClt implements Initializable {
    public TextField Id;
    public TextField Brand;
    public TextField Model;
    public  TextField License;
    public TextField Price;
    public ComboBox<String> Status;
    public TableView<Car> tbView;
    public TableColumn<Car, Integer> TCId;
    public TableColumn<Car, String> TCBrand;
    public TableColumn<Car, String> TCModel;
    public TableColumn<Car, String> TCLicense;
    public TableColumn<Car, Double> TCPrice;
    public TableColumn<Car, String> TCStatus;
    public ObservableList<String> status = FXCollections.observableArrayList("Available", "Not Available");
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TCId.setCellValueFactory(new PropertyValueFactory<>("Carid"));
        TCBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        TCModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        TCLicense.setCellValueFactory(new PropertyValueFactory<>("License"));
        TCPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        TCStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        Id.setDisable(true);
        try{
            Status.setItems(status);
            ObservableList<Car> list = FXCollections.observableArrayList();
            list.addAll(CarRepository.getInstance().getAll());
            tbView.setItems(list);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    public void Home(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/homev2.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280,475));
    }

    public void RentCar(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/rentcar.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280,475));
    }

    public void Submit(ActionEvent actionEvent) throws Exception {
        try {
            String brand = Brand.getText();
            String model = Model.getText();
            String license = License.getText();
            Double price = Double.valueOf(Price.getText());
            String status = Status.getSelectionModel().getSelectedItem().toString();
            Car car = new Car(brand,model,license,price,status);
            if (CarRepository.getInstance().create(car)){
                throw new  Exception("Thêm Xe Mới Thành Công!!");
            }else {
                throw new Exception("Hãy Điền Thông Tin");
            }

        }catch (Exception e ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.show();
        }
       NewCar(null);
    }

    public void Delete(ActionEvent actionEvent) throws Exception {
        try {
            Car car = tbView.getSelectionModel().getSelectedItem();
            if (CarRepository.getInstance().delete(car)) {
                throw new Exception("Đã Xóa Thành Công!");
            }

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        NewCar(null);
    }

    public void Update(ActionEvent actionEvent) throws Exception {
        try {
                int id = Integer.parseInt(Id.getText());
                String brand = Brand.getText();
                String model = Model.getText();
                String license = License.getText();
                Double price = Double.valueOf(Price.getText());
                String status = Status.getSelectionModel().getSelectedItem().toString();
                Car car = new Car(id,brand, model, license, price, status);
                if (CarRepository.getInstance().update(car)) {
                    throw new Exception("Cập Nhật Thành Công!!");
                }else {
                    throw new Exception("Hãy Điền Thông Tin!!");
                }

        }catch (Exception e ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        NewCar(null);
    }


    public void NewCar(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/newcar.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280,475));
    }

    public void Click(MouseEvent mouseEvent) {
        Car carsl = tbView.getSelectionModel().getSelectedItem();
        ObservableList<String> setStatus = FXCollections.observableArrayList();
        setStatus.add(carsl.getStatus());
        if (carsl != null) {
            Id.setText(String.valueOf(carsl.getCarid()));
            Brand.setText(carsl.getBrand());
            Model.setText(carsl.getModel());
            License.setText(carsl.getLicense());
            Price.setText(String.valueOf(carsl.getPrice()));
            Id.setDisable(true);
        }
    }

    public void SignOut(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/signin.fxml"));
        Main.mainStage.setScene(new Scene(root, 1235,475));
    }
}

