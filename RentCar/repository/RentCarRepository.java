package RentCar.repository;

import RentCar.entity.Car;
import RentCar.entity.RentCar;
import database.Connector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import static RentCar.controller.RentCarClt.*;
import static RentCar.controller.ReturnCarClt.ModelSlc;
import static RentCar.controller.ReturnCarClt.tel;

public class RentCarRepository implements SRepository<RentCar>{
    private  static RentCarRepository instance;
    private RentCarRepository(){

    }
    public static RentCarRepository getInstance(){
        if(instance == null){
            instance = new RentCarRepository();
        }
        return instance;
    }
    @Override
    public ArrayList<RentCar> getAll() {
        ArrayList<RentCar> ListRentCar = new ArrayList<>();
        try {
            Connection conn = Connector.getInstance().getConn();
            Statement stt = conn.createStatement();
            String sql = "select * from rentalcar inner join car on rentalcar.carid = car.carid and rental = 'Renting'";
            ResultSet rs = stt.executeQuery(sql);

            while (rs.next()) {
                int carid = rs.getInt("carid");
                String cusName = rs.getString("cusname");
                int cusTel = rs.getInt("custel");
                Double price = rs.getDouble("price");
                Date daterent = rs.getDate("rentdate");
                Date datereturn = rs.getDate("returndate");
                String carModel = rs.getString("model");
                String carBrand = rs.getString("brand");
                String carLicense = rs.getString("license");
                String rental = rs.getString("rental");
                RentCar rentCar = new RentCar(carid,cusName,cusTel,daterent,datereturn,price,carModel,carBrand,carLicense,rental);
                ListRentCar.add(rentCar);
            }
        }catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return ListRentCar;

    }

    @Override
    public Boolean create(RentCar r) {
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "insert into rentalcar(carid, cusname,custel,rentdate,returndate,price,rental) values(?,?,?,?,?,?,?)";
            PreparedStatement stt = conn.prepareStatement(sql);
            stt.setInt(1,r.getCarId());
            stt.setString(2,r.getCusname());
            stt.setInt(3,r.getCustel());
            stt.setDate(4,r.getRentDate());
            stt.setDate(5,r.getReturnDate());
            stt.setDouble(6,r.getPrice());
            stt.setString(7,r.getRental());
            stt.executeUpdate();
        }catch (Exception e){
            System.out.println("error" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(RentCar r) {
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "update rentalcar set price=?,rental=?,returndate=?  where custel=?";
            PreparedStatement stt = conn.prepareStatement(sql);
            stt.setDouble(1,r.getPrice());
            stt.setString(2,r.getRental());
            stt.setDate(3,r.getReturnDate());
            stt.setInt(4,r.getCustel());
            stt.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(RentCar rentCar) {
        return null;
    }

    @Override
    public RentCar find(String brand) {
        return null;
    }
    public static Double rentPrice() {
        Double price = null;
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "select price from car where model = '" + ModelSlc + "' ";
            PreparedStatement stt = conn.prepareStatement(sql);
            ResultSet rs = stt.executeQuery(sql);
            price = null;
            while (rs.next()) {
                price = rs.getDouble("price");
            }
            return price;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return price;
    }
    public static Double pricePlus() {
        double totalprice = 0;
        LocalDate dateplus = LocalDate.now();
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "select returndate from rentalcar where custel = '" + tel + "' ";
            PreparedStatement stt = conn.prepareStatement(sql);
            ResultSet rs = stt.executeQuery(sql);
            Date rtDate = null;
            while (rs.next()) {
                rtDate = rs.getDate("returndate");
            }
            LocalDate reDate = rtDate.toLocalDate();
            int date = 0;
            if (dateplus.getDayOfYear() >= reDate.getDayOfYear()) {
                date = dateplus.getDayOfYear() - reDate.getDayOfYear();
            } else if(dateplus.getDayOfYear() == reDate.getDayOfYear()) {
                date++;
            }else {
                date = dateplus.getDayOfYear() - reDate.getDayOfYear();
                date++;
            }
            totalprice = (rentPrice() * date);
            return totalprice;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return totalprice;
    }
}
