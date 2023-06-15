package RentCar;

import database.Connector;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

import static RentCar.ThueXeClt.*;


public class CarRepository implements SRepository<Car>{
    private static CarRepository instance;
    private CarRepository(){

    }
    public static CarRepository getInstance(){
        if (instance == null){
            instance = new CarRepository();
        }
        return instance;
    }


    public static ArrayList<String> getlistModel() {

        ArrayList<String> listModel = new ArrayList<>();
        try {
            Connection conn = Connector.getInstance().getConn();
            Statement stt = conn.createStatement();
            String sql = "select * from car where brand = '"+getModel+"'";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()){
                String model  = rs.getString("model");
                listModel.add(model);
            }
        }catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return listModel;
    }


    public static ArrayList<String> getListBrand() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Connection conn = Connector.getInstance().getConn();
            Statement stt = conn.createStatement();
            String sql = "select * from car where status = 'Available' ";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()){
                String brand  = rs.getString("brand");
                list.add(brand);
            }


        }catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return list;
    }

    @Override
    public ArrayList<Car> getAll() {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            Connection conn = Connector.getInstance().getConn();
            Statement stt = conn.createStatement();
            String sql = "select * from car";
            ResultSet rs = stt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("carid");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                Double price = rs.getDouble("price");
                String bien = rs.getString("bien");
                String status = rs.getString("status");
                Date date = rs.getDate("date");
                Car car = new Car(id,brand,model,bien,price,status,date);
                cars.add(car);
            }
        }catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return cars;

    }


    public static int countRentDate() {
        int date = 0;
        try {
                if (ngayTra.isAfter(ngayThue)) {
                    date = ngayTra.compareTo(ngayThue);
                    if (date == 0){
                        date++;
                        return date;
                    }
                    return date;
                }else {
                    throw new Exception("Ngày Trả Phải Sau Ngày Thuê!!!");
                }

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

        return date;
    }


    public static Double totalPrice() {
        countRentDate();
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "select price, model from car where model = '"+getModel+"'";
            PreparedStatement stt = conn.prepareStatement(sql);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public Boolean create(Car c) {
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "insert into car(brand,model,bien,price,status) values(?,?,?,?,?)";
            PreparedStatement stt = conn.prepareStatement(sql);
            stt.setString(1,c.getBrand());
            stt.setString(2,c.getModel());
            stt.setString(3,c.getBien());
            stt.setDouble(4,c.getPrice());
            stt.setString(5,c.getStatus());
            stt.executeUpdate();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return false;
    }

    @Override
    public Boolean update(Car c) {
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "update car set brand=?,model=?,bien=?,price=? where id=?";
            PreparedStatement stt = conn.prepareStatement(sql);
            stt.setInt(1,c.getCarid());
            stt.setString(2,c.getBrand());
            stt.setString(3,c.getModel());
            stt.setString(4,c.getBien());
            stt.setDouble(5,c.getPrice());
            stt.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(Car c) {
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "delete from car where id=?";
            PreparedStatement stt = conn.prepareStatement(sql);
            stt.setInt(1,c.getCarid());
            stt.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }




    @Override
    public Car find(String brand) {
        try{
            Connection conn = Connector.getInstance().getConn();
            String sql = "select * from car where brand=?";
            PreparedStatement stt = conn.prepareStatement(sql);
            stt.setString(1,brand);
            ResultSet rs = stt.executeQuery();
            while (rs.next()){
                int carid = rs.getInt("carid");
                String brandcar = rs.getString("brand");
                String model = rs.getString("model");
                String bien = rs.getString("bien");
                Double price = rs.getDouble("price");
                String status = rs.getString("status");
                Date date = rs.getDate("date");
                Car car = new Car(carid,brandcar,model,bien,price,status,date);
                return car;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
