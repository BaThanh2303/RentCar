package RentCar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ThemXeClt implements Initializable {
    public TextField Id;
    public TextField Brand;
    public TextField Model;
    public  TextField Bien;
    public TextField Price;
    public ComboBox<String> Status;
    public TableView<Car> tbView;
    public TableColumn<Car, Integer> TCId;
    public TableColumn<Car, String> TCBrand;
    public TableColumn<Car, String> TCModel;
    public TableColumn<Car, String> TCBien;
    public TableColumn<Car, Double> TCPrice;
    public TableColumn<Car, String> TCStatus;
    public ObservableList<String> status = FXCollections.observableArrayList("Available", "Not Available");
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TCId.setCellValueFactory(new PropertyValueFactory<>("Carid"));
        TCBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        TCModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        TCBien.setCellValueFactory(new PropertyValueFactory<>("Bien"));
        TCPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        TCStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        try{
            Status.setItems(status);
            ObservableList<Car> list = FXCollections.observableArrayList();
            list.addAll(CarRepository.getInstance().getAll());
            System.out.println(list);
            tbView.setItems(list);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    public void Home(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homev2.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280,475));
    }

    public void ThueXe(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("thuexe.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280,475));
    }

    public void Submit(ActionEvent actionEvent) throws Exception {
        try {
            String brand = Brand.getText();
            String model = Model.getText();
            String bien = Bien.getText();
            Double price = Double.valueOf(Price.getText());
            String status = Status.getSelectionModel().getSelectedItem().toString();
            Car car = new Car(brand,model,bien,price,status);
            if (CarRepository.getInstance().create(car)){
                throw new  Exception("Thêm Xe Mới Thành Công!!");
            }

        }catch (Exception e ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.show();
        }
       ThemXe(null);
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
        ThemXe(null);
    }

    public void Update(ActionEvent actionEvent) throws Exception {
        try {
                int id = Integer.parseInt(Id.getText());
                String brand = Brand.getText();
                String model = Model.getText();
                String bien = Bien.getText();
                Double price = Double.valueOf(Price.getText());
                String status = Status.getSelectionModel().getSelectedItem().toString();
                Car car = new Car(id,brand, model, bien, price, status);
                if (CarRepository.getInstance().update(car)) {
                    throw new Exception("Cập Nhật Thành Công!!");
                }

        }catch (Exception e ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        ThemXe(null);
    }

    public void Select(ActionEvent actionEvent) {
        Car carsl = tbView.getSelectionModel().getSelectedItem();
        if (carsl != null) {
            Id.setText(String.valueOf(carsl.getCarid()));
            Brand.setText(carsl.getBrand());
            Model.setText(carsl.getModel());
            Bien.setText(carsl.getBien());
            Price.setText(String.valueOf(carsl.getPrice()));
        }
    }

    public void ThemXe(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("themxe.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280,475));
    }
}

