package RentCar;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ThueXeClt implements Initializable {
    public static String getModel;
    public DatePicker dateReturn;
    public DatePicker dateRented;
    public ComboBox rentCarBrand;
    public ComboBox rentCarModel;
    public TableView<Car> tbView;
    public TableColumn<Car, Integer> TCId;
    public TableColumn<Car, String> TCBrand;
    public TableColumn<Car, String> TCModel;
    public TableColumn<Car, String> TCBien;
    public TableColumn<Car, Double> TCPrice;
    public TableColumn<Car, String> TCStatus;
     public static LocalDate ngayTra;
    public static LocalDate ngayThue;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TCId.setCellValueFactory(new PropertyValueFactory<>("Carid"));
        TCBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        TCModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        TCBien.setCellValueFactory(new PropertyValueFactory<>("Bien"));
        TCPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        TCStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        try{
            ObservableList<Car> list = FXCollections.observableArrayList();
            ObservableList<String> ListBrand = FXCollections.observableArrayList();
            ObservableList<String> listModel = FXCollections.observableArrayList();
            listModel.addAll(CarRepository.getInstance().getlistModel());
            ListBrand.addAll(CarRepository.getInstance().getListBrand());
            list.addAll(CarRepository.getInstance().getAll());
            tbView.setItems(list);
            rentCarBrand.setItems(ListBrand);
            rentCarModel.setItems(listModel);
            ngayTra = dateReturn.getValue();
            ngayThue = dateRented.getValue();


        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }



    public void Submit(ActionEvent actionEvent) {

    }
}
