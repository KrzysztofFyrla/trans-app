package pl.trans.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.trans.app.modelFx.SemiTrailerModel;
import pl.trans.app.utils.ApplicationException;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.FxmlUtils;

public class SemiTrailerAddController {

    @FXML
    public Button addButtonSemiTrailer;
    @FXML
    public TextField markAddSemiTrailer;
    @FXML
    public TextField modelAddSemiTrailer;
    @FXML
    public TextField categoryAddSemiTrailer;
    @FXML
    public TextField roadAddSemiTrailer;
    @FXML
    public DatePicker yearAddSemiTrailer;
    @FXML
    public TextField permissibleMassAddSemiTrailer;
    @FXML
    public TextField massAddSemiTrailer;
    @FXML
    public TextField numberOfAxlesAddSemiTrailer;
    @FXML
    public TextField widthAddSemiTrailer;
    @FXML
    public TextField lengthAddSemiTrailer;
    @FXML
    public TextField heightAddSemiTrailer;
    @FXML
    public TextField saddleloadAddSemiTrailer;
    @FXML
    public TextField axleLoadAddSemiTrailer;
    @FXML
    public Label markLabelSemiTrailer;
    @FXML
    public Label modelLabelSemiTrailer;
    @FXML
    public Label categoryLabelSemiTrailer;
    @FXML
    public Label roadLabelSemiTrailer;
    @FXML
    public Label permissibleMassLabelSemiTrailer;
    @FXML
    public Label massLabelSemiTrailer;
    @FXML
    public Label numberOfAxlesLabelSemiTrailer;
    @FXML
    public Label widthLabelSemiTrailer;
    @FXML
    public Label lengthLabelSemiTrailer;
    @FXML
    public Label heightLabelSemiTrailer;
    @FXML
    public Label saddleLoadLabelSemiTrailer;
    @FXML
    public Label axleLoadLabelSemiTrailer;

    private SemiTrailerModel semiTrailerModel;

    @FXML
    public void initialize(){
        this.semiTrailerModel = new SemiTrailerModel();
        this.semiTrailerModel.init();
        addSemiTrailer();
        validation();
    }

    public void addSemiTrailer(){
        /* this.yearAddTruck.valueProperty().bindBidirectional(this.truckModel.getTruckFxObjectProperty().yearProperty());*/
        this.markAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().markProperty());
        this.modelAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().modelProperty());
        this.categoryAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().categoryProperty());
        this.yearAddSemiTrailer.valueProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().yearProperty());
        this.roadAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().roadProperty());
        this.permissibleMassAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().permissibleMassProperty());
        this.numberOfAxlesAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().numberOfAxlesProperty());
        this.massAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().massProperty());
        this.widthAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().widthProperty());
        this.lengthAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().lengthProperty());
        this.heightAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().heightProperty());
        this.saddleloadAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().saddleLoadProperty());
        this.axleLoadAddSemiTrailer.textProperty().bindBidirectional(this.semiTrailerModel.getSemiTrailerFxObjectProperty().axleLoadProperty());
    }

    private void validation(){
        this.addButtonSemiTrailer.disableProperty().bind(this.markAddSemiTrailer.textProperty().isEmpty()
            .or(this.modelAddSemiTrailer.textProperty().isEmpty())
            .or(this.categoryAddSemiTrailer.textProperty().isEmpty())
            .or(this.yearAddSemiTrailer.valueProperty().isNull())
            .or(this.roadAddSemiTrailer.textProperty().isEmpty())
            .or(this.permissibleMassAddSemiTrailer.textProperty().isEmpty())
            .or(this.numberOfAxlesAddSemiTrailer.textProperty().isEmpty())
            .or(this.massAddSemiTrailer.textProperty().isEmpty())
            .or(this.widthAddSemiTrailer.textProperty().isEmpty())
            .or(this.lengthAddSemiTrailer.textProperty().isEmpty())
            .or(this.heightAddSemiTrailer.textProperty().isEmpty())
            .or(this.saddleloadAddSemiTrailer.textProperty().isEmpty())
            .or(this.axleLoadAddSemiTrailer.textProperty().isEmpty()));
    }

    private void closeStage(){
        ((Stage) markAddSemiTrailer.getScene().getWindow()).close();
    }

    public void openTruckOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/SemiTrailer.fxml");
        Stage stage = new Stage();
        stage.setTitle("Ciągnik");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void addSemiTrailerOnAction() {
        System.out.println(this.semiTrailerModel.getSemiTrailerFxObjectProperty().toString());

        try{
            String regex = "\\D+";
            String regexNumber = "\\d+";

            if(markAddSemiTrailer.getText().matches(regex) && modelAddSemiTrailer.getText().matches(regex)
                    && categoryAddSemiTrailer.getText().matches(regex) && roadAddSemiTrailer.getText().matches(regex)){

                markLabelSemiTrailer.setText("");
                modelLabelSemiTrailer.setText("");
                categoryLabelSemiTrailer.setText("");
                roadLabelSemiTrailer.setText("");

                if(permissibleMassAddSemiTrailer.getText().matches(regexNumber) && massAddSemiTrailer.getText().matches(regexNumber)
                        && numberOfAxlesAddSemiTrailer.getText().matches(regexNumber) && widthAddSemiTrailer.getText().matches(regexNumber)
                        && lengthAddSemiTrailer.getText().matches(regexNumber) && heightAddSemiTrailer.getText().matches(regexNumber)
                        && saddleloadAddSemiTrailer.getText().matches(regexNumber) && axleLoadAddSemiTrailer.getText().matches(regexNumber)){

                    this.semiTrailerModel.saveSemiTrailerInDataBase();
                    DialogUtils.informationDialogSemiTrailer();
                    permissibleMassLabelSemiTrailer.setText("");
                    massLabelSemiTrailer.setText("");
                    numberOfAxlesLabelSemiTrailer.setText("");
                    widthLabelSemiTrailer.setText("");
                    lengthLabelSemiTrailer.setText("");
                    saddleLoadLabelSemiTrailer.setText("");
                    axleLoadAddSemiTrailer.setText("");
                    closeStage();
                    openTruckOnAction();

                }else {
                    DialogUtils.warningAddSemiTrailer();
                    permissibleMassLabelSemiTrailer.setText("Proszę wpisać cyfry");
                    massLabelSemiTrailer.setText("Proszę wpisać cyfry");
                    numberOfAxlesLabelSemiTrailer.setText("Proszę wpisać cyfry");
                    widthLabelSemiTrailer.setText("Proszę wpisać cyfry");
                    lengthLabelSemiTrailer.setText("Proszę wpisać cyfry");
                    saddleLoadLabelSemiTrailer.setText("Proszę wpisać cyfry");
                    axleLoadLabelSemiTrailer.setText("Proszę wpisać cyfry");

                }

            }else{
                DialogUtils.warningAddSemiTrailer();
                markLabelSemiTrailer.setText("Proszę wpisać litery");
                modelLabelSemiTrailer.setText("Proszę wpisać litery");
                categoryLabelSemiTrailer.setText("Proszę wpisać litery");
                roadLabelSemiTrailer.setText("Proszę wpisać litery");
            }
        }catch (Exception e){
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void backSemiTrailerOnAction() {
        closeStage();
        openTruckOnAction();
    }
}
