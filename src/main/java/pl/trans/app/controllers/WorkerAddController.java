package pl.trans.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.modelFx.ListWorkerModel;
import pl.trans.app.modelFx.WorkerModel;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.FxmlUtils;

public class WorkerAddController {
    @FXML
    public TextField nameAddWorker;
    @FXML
    public TextField surnameAddWorker;
    @FXML
    public TextField workplaceAddWorker;
    @FXML
    public DatePicker dateOfEmploymentAddWorker;
    @FXML
    public TextField positionAddWorker;
    @FXML
    public TextField paymentAddWorker;
    @FXML
    public Button addButtonWorker;
    public Label nameLabelWorker;
    @FXML
    public Label surnameLabelWorker;
    @FXML
    public Label workplaceLabelWorker;
    @FXML
    public Label positionLabelWorker;
    @FXML
    public Label paymentLabelWorker;
    @FXML
    public TextField addressAddWorker;
    @FXML
    public TextField educationAddWorker;
    @FXML
    public TextField peselAddWorker;
    @FXML
    public DatePicker dateBirthdayAddWorker;
    @FXML
    public Label addressLabelWorker;
    @FXML
    public Label educationLabelWorker;
    @FXML
    public Label peselLabelWorker;

    private WorkerModel workerModel;

    @FXML
    public void initialize(){
        this.workerModel = new WorkerModel();
        this.workerModel.init();
        addWorkerBind();
        validaation();
    }

    public void addWorkerBind(){
        this.nameAddWorker.textProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().nameProperty());
        this.surnameAddWorker.textProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().surnameProperty());
        this.workplaceAddWorker.textProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().workplaceProperty());
        this.dateOfEmploymentAddWorker.valueProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().dateOfEmploymentProperty());
        this.positionAddWorker.textProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().positionProperty());
        this.paymentAddWorker.textProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().paymentProperty());
        this.addressAddWorker.textProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().adressProperty());
        this.educationAddWorker.textProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().educationProperty());
        this.peselAddWorker.textProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().peselProperty());
        this.dateBirthdayAddWorker.valueProperty().bindBidirectional(this.workerModel.getWorkerFxObjectProperty().dateBirthdayProperty());
    }

    public void addWorkerOnAction() {
        System.out.println(this.workerModel.getWorkerFxObjectProperty().toString());

        try{
            String regex = "\\D+";
            String regexNumber = "\\d+";

            if(nameAddWorker.getText().matches(regex) && surnameAddWorker.getText().matches(regex)
                    && workplaceAddWorker.getText().matches(regex) && positionAddWorker.getText().matches(regex)
                    && addressAddWorker.getText().matches(regex) && educationAddWorker.getText().matches(regex)){
                nameLabelWorker.setText("");
                surnameLabelWorker.setText("");
                workplaceLabelWorker.setText("");
                positionLabelWorker.setText("");
                addressLabelWorker.setText("");
                educationLabelWorker.setText("");

                if(paymentAddWorker.getText().matches(regexNumber) && peselAddWorker.getText().matches(regexNumber)){
                    this.workerModel.saveWorkerInDataBase();
                    DialogUtils.informationDialog();
                    paymentLabelWorker.setText("");
                    peselLabelWorker.setText("");
                    closeStage();
                    openWorkerOnAction();
                } else{
                    DialogUtils.warningAdWorkerPayment();
                    paymentLabelWorker.setText("Proszę wpisać cyfry");
                    peselLabelWorker.setText("Proszę wpisać cyfry");
                }

            } else {
                DialogUtils.warningAddWorker();
                nameLabelWorker.setText("Proszę wpisać litery");
                surnameLabelWorker.setText("Proszę wpisać litery");
                workplaceLabelWorker.setText("Proszę wpisać litery");
                positionLabelWorker.setText("Proszę wpisać litery");
                addressLabelWorker.setText("Proszę wpisać litery");
                educationLabelWorker.setText("Proszę wpisać litery");
            }

        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        clearFields();
    }

    private void clearFields(){
        this.nameAddWorker.clear();
        this.surnameAddWorker.clear();
        this.workplaceAddWorker.clear();
        this.dateOfEmploymentAddWorker.getEditor().clear();
        this.positionAddWorker.clear();
        this.paymentAddWorker.clear();
        this.addressAddWorker.clear();
        this.educationAddWorker.clear();
        this.peselAddWorker.clear();
        this.dateBirthdayAddWorker.getEditor().clear();
    }

    private void validaation(){
        this.addButtonWorker.disableProperty().bind(this.nameAddWorker.textProperty().isEmpty()
            .or(this.surnameAddWorker.textProperty().isEmpty())
            .or(this.workplaceAddWorker.textProperty().isEmpty())
            .or(this.dateOfEmploymentAddWorker.valueProperty().isNull())
            .or(this.positionAddWorker.textProperty().isEmpty())
            .or(this.paymentAddWorker.textProperty().isEmpty())
            .or(this.addressAddWorker.textProperty().isEmpty())
            .or(this.educationAddWorker.textProperty().isEmpty())
            .or(this.peselAddWorker.textProperty().isEmpty())
            .or(this.dateBirthdayAddWorker.valueProperty().isNull()));
    }

    private void closeStage(){
        ((Stage) paymentAddWorker.getScene().getWindow()).close();
    }

    public void openWorkerOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/Worker.fxml");
        Stage stage = new Stage();
        stage.setTitle("Pracownik");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void backWorkerButtonOnAction() {
        closeStage();
        openWorkerOnAction();
    }
}
