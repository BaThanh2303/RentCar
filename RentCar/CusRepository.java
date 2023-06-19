package RentCar;

import database.Connector;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class CusRepository implements SRepository<Customers>{
    private static CusRepository instance;
    private CusRepository(){

    }
    public static CusRepository getInstance(){
        if (instance == null){
            instance = new CusRepository();
        }
        return instance;
    }
    @Override
    public ArrayList<Customers> getAll() {
        ArrayList<Customers> ListCustomer = new ArrayList<>();
        try {
            Connection conn = Connector.getInstance().getConn();
            Statement stt = conn.createStatement();
            String sql = "select * from customers";
            ResultSet rs = stt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("cusid");
                String cusName = rs.getString("cusname");
                String cusTel = rs.getString("custel");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                Double price = rs.getDouble("price");
                Date date = rs.getDate("NgayTra");
                String bien = rs.getString("bien");
                Customers cus = new Customers(id,cusName,cusTel,brand,model,bien,date,price);
                ListCustomer.add(cus);
            }
        }catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return ListCustomer;

    }

    @Override
    public Boolean create(Customers c) {
        try {
            Connection conn = Connector.getInstance().getConn();
            String sql = "insert into customers(cusname,custel,brand,model,bien,ngaytra,price) values(?,?,?,?,?,?,?)";
            PreparedStatement stt = conn.prepareStatement(sql);
            stt.setString(1,c.getCusName());
            stt.setString(2,c.getCusTel());
            stt.setString(3,c.getBrand());
            stt.setString(4,c.getModel());
            stt.setString(5,c.getBien());
            stt.setString(6,c.getBien());
            stt.setDouble(7,c.getPrice());
            stt.executeUpdate();
        }catch (Exception e){
            System.out.println("error" + e.getMessage());
        }
        return false;
    }


    @Override
    public Boolean update(Customers c) {
        return null;
    }

    @Override
    public Boolean delete(Customers customers) {
        return null;
    }

    @Override
    public Customers find(String brand) {
        return null;
    }
}
