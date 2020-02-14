package pl.trans.app.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class WorkerFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();
    private SimpleStringProperty workplace = new SimpleStringProperty();
    private ObjectProperty<LocalDate> dateOfEmployment = new SimpleObjectProperty<>();
    private SimpleStringProperty position = new SimpleStringProperty();
    private SimpleStringProperty payment = new SimpleStringProperty();
    private SimpleStringProperty adress = new SimpleStringProperty();
    private SimpleStringProperty education = new SimpleStringProperty();
    private SimpleStringProperty pesel = new SimpleStringProperty();
    private ObjectProperty<LocalDate> dateBirthday = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getWorkplace() {
        return workplace.get();
    }

    public SimpleStringProperty workplaceProperty() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace.set(workplace);
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment.get();
    }

    public ObjectProperty<LocalDate> dateOfEmploymentProperty() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment.set(dateOfEmployment);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getPayment() {
        return payment.get();
    }

    public SimpleStringProperty paymentProperty() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment.set(payment);
    }

    public String getAdress() {
        return adress.get();
    }

    public SimpleStringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }

    public String getEducation() {
        return education.get();
    }

    public SimpleStringProperty educationProperty() {
        return education;
    }

    public void setEducation(String education) {
        this.education.set(education);
    }

    public String getPesel() {
        return pesel.get();
    }

    public SimpleStringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public LocalDate getDateBirthday() {
        return dateBirthday.get();
    }

    public ObjectProperty<LocalDate> dateBirthdayProperty() {
        return dateBirthday;
    }

    public void setDateBirthday(LocalDate dateBirthday) {
        this.dateBirthday.set(dateBirthday);
    }

    @Override
    public String toString(){
        return "WorkerFx{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                ", surname=" + surname.get() +
                ", workplace=" + workplace.get() +
                ", dateOfEmployment=" + dateOfEmployment.get() +
                ", position=" + position.get() +
                ", payment=" + payment.get() +
                ", adress=" + adress.get() +
                ", education=" + education.get() +
                ", pesel=" + pesel.get() +
                ", dateBirthday=" + dateBirthday.get() +
                '}';
    }
}
