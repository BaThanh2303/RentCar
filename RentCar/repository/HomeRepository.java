package RentCar.repository;

import RentCar.entity.RentCar;
import database.Connector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class HomeRepository implements SRepository<RentCar>{
    private  static HomeRepository instance;
    private HomeRepository(){

    }
    public static HomeRepository getInstance(){
        if(instance == null){
            instance = new HomeRepository();
        }
        return instance;
    }
    @Override
    public ArrayList<RentCar> getAll() {
        ArrayList<RentCar> ListRentCar = new ArrayList<>();
        try {
            Connection conn = Connector.getInstance().getConn();
            Statement stt = conn.createStatement();
            String sql = "select * from rentalcar inner join car on rentalcar.carid = car.carid and rental = 'Return'";
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
    public Boolean create(RentCar rentCar) {
        return null;
    }

    @Override
    public Boolean update(RentCar rentCar) {
        return null;
    }

    @Override
    public Boolean delete(RentCar rentCar) {
        return null;
    }

    @Override
    public RentCar find(String brand) {
        return null;
    }
}
