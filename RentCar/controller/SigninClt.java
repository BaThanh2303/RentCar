package RentCar.controller;

import RentCar.Main;
import database.Connector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SigninClt {
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    public void Submit(ActionEvent event) {
        try {
            Connection conn = Connector.getInstance().getConn();

            String sql = "select * from admin where username = ? and password = ?";
            PreparedStatement prepare = conn.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2,password.getText());
            ResultSet rs = prepare.executeQuery();
            if (username.getText().isEmpty() || password.getText().isEmpty()){
                throw new Exception("Hãy Nhập Thông Tin!!");
            }else {
                if (rs.next()) {
                    Parent root = FXMLLoader.load(getClass().getResource("fxml/homev2.fxml"));
                    Main.mainStage.setScene(new Scene(root, 1280,475));
                }else {
                    throw new Exception("Sai UserName Hoặc Password");
                }
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}
