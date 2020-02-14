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
import pl.trans.app.modelFx.TruckModel;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.FxmlUtils;

public class TruckAddController {
    @FXML
    public TextField brandAddTruck;
    @FXML
    public TextField modelAddTruck;
    @FXML
    public TextField mileageAddTruck;
    @FXML
    public TextField bodyAddTruck;
    @FXML
    public TextField packageAddTruck;
    @FXML
    public DatePicker yearAddTruck;
    @FXML
    public TextField powerAddTruck;
    @FXML
    public TextField capacityAddTruck;
    @FXML
    public TextField lenghtAddTruck;
    @FXML
    public TextField widthAddTruck;
    @FXML
    public TextField transmissionAddTruck;
    @FXML
    public TextField fuelTypeAddTruck;
    @FXML
    public TextField roadAddTruck;
    @FXML
    public Button addButtonTruck;
    @FXML
    public Label brandLabelTruck;
    @FXML
    public Label modelLabelTruck;
    @FXML
    public Label mileageLabelTruck;
    @FXML
    public Label bodyLabelTruck;
    @FXML
    public Label roadLabelTruck;
    @FXML
    public Label powerLabelTruck;
    @FXML
    public Label capacityLabelTruck;
    @FXML
    public Label lenghtLabelTruck;
    @FXML
    public Label widthLabelTruck;
    @FXML
    public Label transmissionLabelTruck;
    @FXML
    public Label fuelLabelTruck;
    @FXML
    public Label packageLabelTruck;


    TruckModel truckModel;

    @FXML
    public void initialize(){
        this.truckModel = new TruckModel();
        try {
            this.truckModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        addTruckBind();
        validation();
    }

    public void addTruckBind(){
        this.brandAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().brandProperty());
        this.modelAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().modelProperty());
        this.mileageAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().mileageProperty());
        this.bodyAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().bodyProperty());
        this.packageAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().packProperty());
        this.yearAddTruck.valueProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().yearProperty());
        this.powerAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().powerProperty());
        this.capacityAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().capacityProperty());
        this.lenghtAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().lenghtProperty());
        this.widthAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().widthProperty());
        this.transmissionAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().transmissionProperty());
        this.fuelTypeAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().fuelProperty());
        this.roadAddTruck.textProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().roadProperty());
    }

    private void validation(){
        this.addButtonTruck.disableProperty().bind(this.brandAddTruck.textProperty().isEmpty()
            .or(this.modelAddTruck.textProperty().isEmpty())
            .or(this.mileageAddTruck.textProperty().isEmpty())
            .or(this.bodyAddTruck.textProperty().isEmpty())
            .or(this.packageAddTruck.textProperty().isEmpty())
            .or(this.yearAddTruck.valueProperty().isNull())
            .or(this.powerAddTruck.textProperty().isEmpty())
            .or(this.capacityAddTruck.textProperty().isEmpty())
            .or(this.lenghtAddTruck.textProperty().isEmpty())
            .or(this.widthAddTruck.textProperty().isEmpty())
            .or(this.transmissionAddTruck.textProperty().isEmpty())
            .or(this.fuelTypeAddTruck.textProperty().isEmpty())
            .or(this.roadAddTruck.textProperty().isEmpty()));
    }

    private void closeStage(){
        ((Stage) brandAddTruck.getScene().getWindow()).close();
    }

    public void openTruckOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/Truck.fxml");
        Stage stage = new Stage();
        stage.setTitle("Ciągnik");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void addTruckOnAction() {
        System.out.println(this.truckModel.getTruckFxObjectProperty().toString());

        try{
            String regex = "\\D+";
            String regexNumber = "\\d+";

            if(brandAddTruck.getText().matches(regex) && modelAddTruck.getText().matches(regex) && bodyAddTruck.getText().matches(regex)
                    && packageAddTruck.getText().matches(regex) && transmissionAddTruck.getText().matches(regex) && fuelTypeAddTruck.getText().matches(regex)
                    && roadAddTruck.getText().matches(regex)){

                brandLabelTruck.setText("");
                modelLabelTruck.setText("");
                bodyLabelTruck.setText("");
                packageLabelTruck.setText("");
                transmissionLabelTruck.setText("");
                fuelLabelTruck.setText("");
                roadLabelTruck.setText("");

                if(mileageAddTruck.getText().matches(regexNumber) && powerAddTruck.getText().matches(regexNumber) && capacityAddTruck.getText().matches(regexNumber)
                        && lenghtAddTruck.getText().matches(regexNumber) && widthAddTruck.getText().matches(regexNumber)){
                    this.truckModel.saveTruckInDataBase();
                    DialogUtils.informationDialogTruck();

                    mileageLabelTruck.setText("");
                    powerLabelTruck.setText("");
                    capacityLabelTruck.setText("");
                    lenghtLabelTruck.setText("");
                    widthLabelTruck.setText("");
                    closeStage();
                    openTruckOnAction();

                } else {
                    DialogUtils.warningAddTruck();

                    mileageLabelTruck.setText("Proszę wpisać cyfry");
                    powerLabelTruck.setText("Proszę wpisać cyfry");
                    capacityLabelTruck.setText("Proszę wpisać cyfry");
                    lenghtLabelTruck.setText("Proszę wpisać cyfry");
                    widthLabelTruck.setText("Proszę wpisać cyfry");

                }
            } else {
                DialogUtils.warningAddTruck();

                brandLabelTruck.setText("Proszę wpisać litery");
                modelLabelTruck.setText("Proszę wpisać litery");
                bodyLabelTruck.setText("Proszę wpisać litery");
                packageLabelTruck.setText("Proszę wpisać litery");
                transmissionLabelTruck.setText("Proszę wpisać litery");
                fuelLabelTruck.setText("Proszę wpisać litery");
                roadLabelTruck.setText("Proszę wpisać litery");

            }
        }catch (Exception e){
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void backTruckButtonOnAction() {
        closeStage();
        openTruckOnAction();
    }
}
