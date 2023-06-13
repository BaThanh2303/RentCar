package RentCar;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ThueXeClt implements Initializable {
    public static String getStringModel;
    public ComboBox rentCarBrand;
    public ComboBox rentCarModel;
    public TableView<Car> tbView;
    public TableColumn<Car, Integer> TCId;
    public TableColumn<Car, String> TCBrand;
    public TableColumn<Car, String> TCModel;
    public TableColumn<Car, Double> TCPrice;
    public TableColumn<Car, String> TCStatus;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TCId.setCellValueFactory(new PropertyValueFactory<>("Carid"));
        TCBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        TCModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
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


        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
    }



    public void Submit(ActionEvent actionEvent) {
    }
}
