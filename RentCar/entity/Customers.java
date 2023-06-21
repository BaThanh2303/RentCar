package RentCar.entity;

import java.sql.Date;

public class Customers {
    private int CusId;
    private String CusName;
    private String CusTel;
    private String Brand;
    private String Model;
    private String License;
    private Date DateReturned;
    private Double Price;

    public Customers(int cusId, String cusName, String cusTel, String brand, String model, String license, Date dateReturned, Double price) {
        CusId = cusId;
        CusName = cusName;
        CusTel = cusTel;
        Brand = brand;
        Model = model;
        License = license;
        DateReturned = dateReturned;
        Price = price;
    }

    public Customers(String cusName, String cusTel, String brand, String model, String license, Date dateReturned, Double price) {
        CusName = cusName;
        CusTel = cusTel;
        Brand = brand;
        Model = model;
        License = license;
        DateReturned = dateReturned;
        Price = price;
    }

    public Date getDateReturned() {
        return DateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        DateReturned = dateReturned;
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

    public String getCusTel() {
        return CusTel;
    }

    public void setCusTel(String cusTel) {
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

    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
