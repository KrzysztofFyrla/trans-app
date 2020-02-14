package pl.trans.app.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "SEMI_TRAILERS")

public class SemiTrailer implements BaseModel {

    public SemiTrailer(){

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "MARK", canBeNull = false)
    private String mark;

    @DatabaseField(columnName = "MODEL", canBeNull = false)
    private String model;

    @DatabaseField(columnName = "CATEGORY", canBeNull = false)
    private String category;

    @DatabaseField(columnName = "YEAR", canBeNull = false)
    private Date year;

    @DatabaseField(columnName = "ROAD", canBeNull = false)
    private String road;

    @DatabaseField(columnName = "PERMISSIBLE_MASS", canBeNull = false)
    private String permissibleMass;

    @DatabaseField(columnName = "NUMBER_OF_AXLES", canBeNull = false)
    private String numberOfAxles;

    @DatabaseField(columnName = "MASS", canBeNull = false)
    private String mass;

    @DatabaseField(columnName = "WIDTH", canBeNull = false)
    private String width;

    @DatabaseField(columnName = "LENGHT", canBeNull = false)
    private String lenght;

    @DatabaseField(columnName = "HEIGHT", canBeNull = false)
    private String height;

    @DatabaseField(columnName = "SADDLE LOAD", canBeNull = false)
    private String saddleLoad;

    @DatabaseField(columnName = "AXLE_LOAD", canBeNull = false)
    private String axleLoad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getPermissibleMass() {
        return permissibleMass;
    }

    public void setPermissibleMass(String permissibleMass) {
        this.permissibleMass = permissibleMass;
    }

    public String getNumberOfAxles() {
        return numberOfAxles;
    }

    public void setNumberOfAxles(String numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSaddleLoad() {
        return saddleLoad;
    }

    public void setSaddleLoad(String saddleLoad) {
        this.saddleLoad = saddleLoad;
    }

    public String getAxleLoad() {
        return axleLoad;
    }

    public void setAxleLoad(String axleLoad) {
        this.axleLoad = axleLoad;
    }
}
