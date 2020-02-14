package pl.trans.app.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class TruckFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty brand = new SimpleStringProperty();
    private SimpleStringProperty model = new SimpleStringProperty();
    private SimpleStringProperty power = new SimpleStringProperty();
    private ObjectProperty<LocalDate> year = new SimpleObjectProperty<>();
    private SimpleStringProperty mileage = new SimpleStringProperty();
    private SimpleStringProperty road = new SimpleStringProperty();
    private SimpleStringProperty body = new SimpleStringProperty();
    private SimpleStringProperty pack = new SimpleStringProperty();
    private SimpleStringProperty capacity = new SimpleStringProperty();
    private SimpleStringProperty lenght = new SimpleStringProperty();
    private SimpleStringProperty width = new SimpleStringProperty();
    private SimpleStringProperty transmission = new SimpleStringProperty();
    private SimpleStringProperty fuel = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getPower() {
        return power.get();
    }

    public SimpleStringProperty powerProperty() {
        return power;
    }

    public void setPower(String power) {
        this.power.set(power);
    }

    public LocalDate getYear() {
        return year.get();
    }

    public ObjectProperty<LocalDate> yearProperty() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year.set(year);
    }

    public String getMileage() {
        return mileage.get();
    }

    public SimpleStringProperty mileageProperty() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage.set(mileage);
    }

    public String getRoad() {
        return road.get();
    }

    public SimpleStringProperty roadProperty() {
        return road;
    }

    public void setRoad(String road) {
        this.road.set(road);
    }

    public String getBody() {
        return body.get();
    }

    public SimpleStringProperty bodyProperty() {
        return body;
    }

    public void setBody(String body) {
        this.body.set(body);
    }

    public String getPack() {
        return pack.get();
    }

    public SimpleStringProperty packProperty() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack.set(pack);
    }

    public String getCapacity() {
        return capacity.get();
    }

    public SimpleStringProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity.set(capacity);
    }

    public String getLenght() {
        return lenght.get();
    }

    public SimpleStringProperty lenghtProperty() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght.set(lenght);
    }

    public String getWidth() {
        return width.get();
    }

    public SimpleStringProperty widthProperty() {
        return width;
    }

    public void setWidth(String width) {
        this.width.set(width);
    }

    public String getTransmission() {
        return transmission.get();
    }

    public SimpleStringProperty transmissionProperty() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission.set(transmission);
    }

    public String getFuel() {
        return fuel.get();
    }

    public SimpleStringProperty fuelProperty() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel.set(fuel);
    }

    @Override
    public String toString(){
        return "TruckFx{" +
                "id=" + id.get() +
                ", brand=" + brand.get() +
                ", model=" + model.get() +
                ", power=" + power.get() +
                ", year=" + year.get() +
                ", mileage=" + mileage.get() +
                ", road=" + road.get() +
                ", body=" + body.get() +
                ", package=" + pack.get() +
                ", capacity=" + capacity.get() +
                ", lenght=" + lenght.get() +
                ", width=" + width.get() +
                ", fuel=" + fuel.get() +
                '}';
    }
}
