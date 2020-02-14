package pl.trans.app.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "TRUCK")

public class Truck implements BaseModel{

    public Truck(){

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "BRAND", canBeNull = false)
    private String brand;

    @DatabaseField(columnName = "MODEL", canBeNull = false)
    private String model;

    @DatabaseField(columnName = "POWER", canBeNull = false)
    private String power;

    @DatabaseField(columnName = "YEAR", canBeNull = false)
    private Date year;

    @DatabaseField(columnName = "MILEAGE", canBeNull = false)
    private String mileage;

    @DatabaseField(columnName = "ROAD", canBeNull = false)
    private String road;

    @DatabaseField(columnName = "BODY", canBeNull = false)
    private String body;

    @DatabaseField(columnName = "PACKAGE", canBeNull = false)
    private String pack;

    @DatabaseField(columnName = "CAPACITY", canBeNull = false)
    private String capacity;

    @DatabaseField(columnName = "LENGHT", canBeNull = false)
    private String lenght;

    @DatabaseField(columnName = "WIDTH", canBeNull = false)
    private String width;

    @DatabaseField(columnName = "TRANSMISSION", canBeNull = false)
    private String transmission;

    @DatabaseField(columnName = "FUEL", canBeNull = false)
    private String fuel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
