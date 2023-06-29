package RentCar.entity;

import java.sql.Date;

public class RentCar {
    private int rentCarId;
    private int carId;
    private String cusname;
    private int custel;
    private Date rentDate;
    private Date returnDate;
    private double price;
    private String carModel;
    private String carBrand;
    private String carLicense;
    private String rental;

    public RentCar(int carid,String cusname, int custel, Date rentDate, Date returnDate, double price, String carModel, String carBrand, String carLicense,String rental) {
        this.carId = carid;
        this.cusname = cusname;
        this.custel = custel;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.price = price;
        this.carModel = carModel;
        this.carBrand = carBrand;
        this.carLicense = carLicense;
        this.rental = rental;
    }

    public RentCar(int rentCarId, int carId, String cusname, int custel, Date rentDate, Date returnDate, double price,String rental ) {
        this.rentCarId = rentCarId;
        this.carId = carId;
        this.cusname = cusname;
        this.custel = custel;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.price = price;
        this.rental = rental;

    }

    public RentCar(int carId, String cusname, int custel, Date rentDate, Date returnDate, double price,String rental) {
        this.carId = carId;
        this.cusname = cusname;
        this.custel = custel;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.price = price;
        this.rental = rental;

    }

    public int getRentCarId() {
        return rentCarId;
    }

    public void setRentCarId(int rentCarId) {
        this.rentCarId = rentCarId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public int getCustel() {
        return custel;
    }

    public void setCustel(int custel) {
        this.custel = custel;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }
}
