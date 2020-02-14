package pl.trans.app.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class SemiTrailerFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty mark = new SimpleStringProperty();
    private SimpleStringProperty model = new SimpleStringProperty();
    private SimpleStringProperty category = new SimpleStringProperty();
    private ObjectProperty<LocalDate> year = new SimpleObjectProperty<>();
    private SimpleStringProperty road = new SimpleStringProperty();
    private SimpleStringProperty permissibleMass = new SimpleStringProperty();
    private SimpleStringProperty numberOfAxles = new SimpleStringProperty();
    private SimpleStringProperty mass = new SimpleStringProperty();
    private SimpleStringProperty width = new SimpleStringProperty();
    private SimpleStringProperty length = new SimpleStringProperty();
    private SimpleStringProperty height = new SimpleStringProperty();
    private SimpleStringProperty saddleLoad = new SimpleStringProperty();
    private SimpleStringProperty axleLoad = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getMark() {
        return mark.get();
    }

    public SimpleStringProperty markProperty() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.set(mark);
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

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
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

    public String getRoad() {
        return road.get();
    }

    public SimpleStringProperty roadProperty() {
        return road;
    }

    public void setRoad(String road) {
        this.road.set(road);
    }

    public String getPermissibleMass() {
        return permissibleMass.get();
    }

    public SimpleStringProperty permissibleMassProperty() {
        return permissibleMass;
    }

    public void setPermissibleMass(String permissibleMass) {
        this.permissibleMass.set(permissibleMass);
    }

    public String getNumberOfAxles() {
        return numberOfAxles.get();
    }

    public SimpleStringProperty numberOfAxlesProperty() {
        return numberOfAxles;
    }

    public void setNumberOfAxles(String numberOfAxles) {
        this.numberOfAxles.set(numberOfAxles);
    }

    public String getMass() {
        return mass.get();
    }

    public SimpleStringProperty massProperty() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass.set(mass);
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

    public String getLength() {
        return length.get();
    }

    public SimpleStringProperty lengthProperty() {
        return length;
    }

    public void setLength(String length) {
        this.length.set(length);
    }

    public String getHeight() {
        return height.get();
    }

    public SimpleStringProperty heightProperty() {
        return height;
    }

    public void setHeight(String height) {
        this.height.set(height);
    }

    public String getSaddleLoad() {
        return saddleLoad.get();
    }

    public SimpleStringProperty saddleLoadProperty() {
        return saddleLoad;
    }

    public void setSaddleLoad(String saddleLoad) {
        this.saddleLoad.set(saddleLoad);
    }

    public String getAxleLoad() {
        return axleLoad.get();
    }

    public SimpleStringProperty axleLoadProperty() {
        return axleLoad;
    }

    public void setAxleLoad(String axleLoad) {
        this.axleLoad.set(axleLoad);
    }

    @Override
    public String toString(){
        return "SemiTrailerFx{" +
                "id=" + id.get() +
                ", mark=" + mark.get() +
                ", model=" + model.get() +
                ", category=" + category.get() +
                ", year=" + year.get() +
                ", road=" + road.get() +
                ", permissible mass=" + permissibleMass.get() +
                ", number of axles=" + numberOfAxles.get() +
                ", mass=" + mass.get() +
                ", width=" + width.get() +
                ", length=" + length.get() +
                ", heigth=" + height.get() +
                ", saddle load=" + saddleLoad.get() +
                ", axleload=" + axleLoad.get() +
                '}';
    }
}
