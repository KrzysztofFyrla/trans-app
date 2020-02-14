package pl.trans.app.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "WORKERS")
public class Worker implements BaseModel{

    public Worker(){

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "SURNAME", canBeNull = false)
    private String surname;

    @DatabaseField(columnName = "WORKPLACE", canBeNull = false)
    private String workplace;

    @DatabaseField(columnName = "DATE_OF_EMPLOYMENT", canBeNull = false)
    private Date dateOfEmployment;

    @DatabaseField(columnName = "POSITION", canBeNull = false)
    private String position;

    @DatabaseField(columnName = "PAYMENT", canBeNull = false)
    private String payment;

    @DatabaseField(columnName = "ADDRESS", canBeNull = false)
    private String address;

    @DatabaseField(columnName = "EDUCATION", canBeNull = false)
    private String education;

    @DatabaseField(columnName = "PESEL", canBeNull = false)
    private String pesel;

    @DatabaseField(columnName = "DATE_BIRTHDAY", canBeNull =  false)
    private Date dateBirthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday = dateBirthday;
    }
}
