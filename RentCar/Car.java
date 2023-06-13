package RentCar;

import java.sql.Date;

public class Car {
    private int Carid;
    private String Brand;
    private String Model;
    private double Price;
    private String Status;
    private Date Date;

    public Car(int carid, String brand, String model, double price, String status, java.sql.Date date) {
        Carid = carid;
        Brand = brand;
        Model = model;
        Price = price;
        Status = status;
        Date = date;
    }

    public int getCarid() {
        return Carid;
    }

    public void setCarid(int carid) {
        Carid = carid;
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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }
    public String toString(){
        return Carid+"-"+Brand+"-"+Model+"-"+Price+"-"+Status;
    }
}
