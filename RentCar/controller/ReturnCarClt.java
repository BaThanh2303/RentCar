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

import static RentCar.repository.CarRepository.totalPrice;
import static RentCar.repository.RentCarRepository.pricePlus;


public class ReturnCarClt implements Initializable {
    public static String tel;
    public static String ModelSlc;
    public Button submit;
    public Button clear;
    public Double endPrice;
    public Double totalPrice = 0.0;
    public Double money;
    public Double failurePrice ;
    public TextField cusName;
    public TextField cusTel;
    public TextField Model;
    public String Brand;
    public int carID;
    public  TextField License;
    public TextField Price;
    public TextArea detail;
    public Text detailText;
    public Text total;
    public Text failuretotal;
    public Text finaltotal;
    public ComboBox<String> Status;
    public ComboBox<String> failureSelect;
    public TableView<RentCar> tbView;
    public TableColumn<RentCar, Integer> ID;
    public TableColumn<RentCar, String> TCModel;
    public TableColumn<RentCar, String> TCLicense;
    public TableColumn<RentCar, Double> TCPrice;
    public TableColumn<RentCar, String> TCCusName;
    public TableColumn<RentCar,Integer> TCCusTel;
    public TableColumn<RentCar, Date> TCRentDate;
    public TableColumn<RentCar,Date> TCReturntDate;
    public Date daterent;
    public ObservableList<String> status = FXCollections.observableArrayList("Normal", "Failure");
    public ObservableList<String> failureList = FXCollections.observableArrayList("Không","Hỏng Đèn", "Hỏng Gương", "Trầy Xước");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ID.setCellValueFactory(new PropertyValueFactory<>("carId"));
        TCModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        TCLicense.setCellValueFactory(new PropertyValueFactory<>("carLicense"));
        TCPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        TCCusName.setCellValueFactory(new PropertyValueFactory<>("cusname"));
        TCCusTel.setCellValueFactory(new PropertyValueFactory<>("custel"));
        TCRentDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        TCReturntDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        try{
            ObservableList<RentCar> list = FXCollections.observableArrayList();
            list.addAll(RentCarRepository.getInstance().getAll());
            tbView.setItems(list);
            Status.setItems(status);
            failureSelect.setItems(failureList);
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


    public void ReturnCar(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/carreturn.fxml"));
        Main.mainStage.setScene(new Scene(root, 1280,475));
    }

    public void Click(MouseEvent mouseEvent) {
        RentCar rentCar = tbView.getSelectionModel().getSelectedItem();
        if (rentCar != null){
            Model.setText(rentCar.getCarModel());
            License.setText(rentCar.getCarLicense());
            Price.setText(String.valueOf(rentCar.getPrice()));
            cusName.setText(rentCar.getCusname());
            cusTel.setText(String.valueOf(rentCar.getCustel()));
            tel = String.valueOf(rentCar.getCustel());
            ModelSlc = rentCar.getCarModel();
            total.setText(String.valueOf(rentCar.getPrice()) + " + " + pricePlus());
            money = rentCar.getPrice() + pricePlus();
            endPrice = money;
            finaltotal.setText(String.valueOf(endPrice)+ " $");
            daterent = rentCar.getRentDate();
            Brand = rentCar.getCarBrand();
            carID = rentCar.getCarId();
            Status.setDisable(false);
        }
    }

    public void SignOut(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/signin.fxml"));
        Main.mainStage.setScene(new Scene(root, 366, 503));
    }

    public void SelectStatus(ActionEvent event) {
        if (Status.getSelectionModel().getSelectedItem() == "Failure"){
            failureSelect.setVisible(true);
            detail.setVisible(true);
            detailText.setVisible(true);
            clear.setVisible(true);
            submit.setDisable(false);
        }else {
            failureSelect.setVisible(false);
            detail.setVisible(false);
            detailText.setVisible(false);
            clear.setVisible(false);
            submit.setDisable(false);
        }
    }


    public void failure(ActionEvent event) {

        if (failureSelect.getValue() == "Hỏng Đèn"){
            detail.appendText(failureSelect.getSelectionModel().getSelectedItem() +"\n");
            failurePrice = 1000.0;
            totalPrice += failurePrice;
            failuretotal.setText(String.valueOf(totalPrice) + " $");
            endPrice = money + totalPrice;
            finaltotal.setText(endPrice + " $");
        }else if (failureSelect.getValue() == "Hỏng Gương") {
            detail.appendText(failureSelect.getSelectionModel().getSelectedItem() +"\n");
            failurePrice = 500.0;
            totalPrice += failurePrice;
            failuretotal.setText(String.valueOf(totalPrice)+ " $");
            endPrice = money + totalPrice;
            finaltotal.setText(endPrice + " $");
        }else if (failureSelect.getValue() == "Trầy Xước") {
            detail.appendText(failureSelect.getSelectionModel().getSelectedItem() +"\n");
            failurePrice = 250.0;
            totalPrice += failurePrice;
            failuretotal.setText(String.valueOf(totalPrice)+ " $");
            endPrice = money + totalPrice;
            finaltotal.setText(endPrice + " $");
        }
    }

    public void clear(ActionEvent event) {
        totalPrice = 0.0;
        detail.clear();
        failuretotal.setText(String.valueOf(totalPrice)+ " $");
        finaltotal.setText(money + totalPrice + " $");
    }

    public void Submit(ActionEvent event) {
        try {
                String nameCus = cusName.getText();
                int custel = Integer.valueOf(cusTel.getText());
                Date rentdate = Date.valueOf(daterent.toLocalDate());
                Date returndate = Date.valueOf(LocalDate.now());
                Double price = Double.valueOf(String.valueOf(endPrice));
                int id = carID;
                String brand = Brand;
                String model = Model.getText();
                String license = License.getText();
                String rental = "Return";
                String status = "Available";
                Car car = new Car(id,brand,model,license,price,status);
                RentCar rentCar = new RentCar(id,nameCus,custel,rentdate,returndate,price,model,brand,license,rental);
                if (RentCarRepository.getInstance().update(rentCar)){
                    CarRepository.getInstance().update(car);
                    ReturnCar(null);
                    throw new Exception("Đã Trả Xe Thành Công!!!");
                }
        }catch (Exception e ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}

