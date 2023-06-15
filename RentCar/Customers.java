package RentCar;

public class Customers {
    private int CusId;
    private String CusName;
    private int CusTel;
    private String Brand;
    private String Model;
    private String Bien;
    private Double Price;

    public Customers(int cusId, String cusName, int cusTel, String brand, String model, String bien, Double price) {
        CusId = cusId;
        CusName = cusName;
        CusTel = cusTel;
        Brand = brand;
        Model = model;
        Bien = bien;
        Price = price;
    }

    public int getCusId() {
        return CusId;
    }

    public void setCusId(int cusId) {
        CusId = cusId;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public int getCusTel() {
        return CusTel;
    }

    public void setCusTel(int cusTel) {
        CusTel = cusTel;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getBien() {
        return Bien;
    }

    public void setBien(String bien) {
        Bien = bien;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
