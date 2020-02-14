package pl.trans.app.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.CORBA.portable.ApplicationException;

import pl.trans.app.database.dao.TruckDao;
import pl.trans.app.database.models.Truck;
import pl.trans.app.modelFx.ListTruckModel;
import pl.trans.app.modelFx.TruckFx;
import pl.trans.app.modelFx.TruckModel;
import pl.trans.app.modelFx.WorkerFx;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.FxmlUtils;
import pl.trans.app.utils.converters.ConverterTruck;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TruckController {

    ObservableList<TruckFx> truck = FXCollections.observableArrayList();
    private List<TruckFx> truckFxList = new ArrayList<>();

    public Label time;
    @FXML
    public TableColumn<TruckFx, String> brandColumnTruck;
    @FXML
    public TableColumn<TruckFx, String> modelColumnTruck;
    @FXML
    public TableColumn<TruckFx, String> powerColumnTruck;
    @FXML
    public TableColumn<TruckFx, LocalDate> yearColumnTruck;
    @FXML
    public TableColumn<TruckFx, String> mileageColumnTruck;
    @FXML
    public TableColumn<TruckFx, String> roadColumnTruck;
    @FXML
    public TableView<TruckFx> truckTableView;
    @FXML
    public TableColumn<TruckFx, TruckFx> editButtonTruck;
    @FXML
    public TableColumn<TruckFx, TruckFx> deleteButtonTruck;
    @FXML
    public TextField brandFilterTextField;
    @FXML
    public TextField modelFilterTextField;
    @FXML
    public TextField powerFilterTextField;
    @FXML
    public TextField yearFilterTextField;
    @FXML
    public TextField mileageFilterTextField;
    @FXML
    public TextField roadFilterTextField;


    ListTruckModel listTruckModel;

    private int minute;
    private int hour;
    private int second;

    @FXML
    void initialize() throws ApplicationException {
        TruckDao truckDao = new TruckDao();
        List<Truck> truck2 = truckDao.queryForAll((Truck.class));
        truckFxList.clear();
        truck2.forEach((trucks -> {
            this.truckFxList.add(ConverterTruck.converterToTruckFx(trucks));
        }));
        this.truck.setAll(truckFxList);

        this.listTruckModel = new ListTruckModel();
        try {
            this.listTruckModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        this.truckTableView.setItems(this.listTruckModel.getTruckFxObservableList());
        this.brandColumnTruck.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        this.modelColumnTruck.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        this.powerColumnTruck.setCellValueFactory(cellData -> cellData.getValue().powerProperty());
        this.yearColumnTruck.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        this.mileageColumnTruck.setCellValueFactory(cellData -> cellData.getValue().mileageProperty());
        this.roadColumnTruck.setCellValueFactory(cellData -> cellData.getValue().roadProperty());

        this.truckTableView.getSelectionModel().selectedItemProperty().addListener((observable, aldValue, newValue) ->{
            this.listTruckModel.setTruckFxObjectPropertyEdit(newValue);
        });

        this.truckTableView.setItems(this.listTruckModel.getTruckFxObservableList());

        this.editButtonTruck.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.deleteButtonTruck.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        this.editButtonTruck.setCellFactory(cellData -> new TableCell<TruckFx, TruckFx>(){
            Button button = createEditButton();
            @Override
            protected void updateItem(TruckFx item, boolean empty){
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty){
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = FxmlUtils.getLoader("/fxml/EditTruck.fxml");
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }

                        EditTruckController controller = loader.getController();
                        controller.getTruckModel().setTruckFxObjectProperty(item);
                        controller.addTruckBind();
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        closeStage();
                    });
                }
            }
        });

        this.deleteButtonTruck.setCellFactory(param -> new TableCell<TruckFx, TruckFx>(){
            Button button = createDeleteButton();
            @Override
            protected void updateItem(TruckFx item, boolean empty){
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty){
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            listTruckModel.deleteTruck(item);
                            truckTableView.refresh();
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });
        searchTruck();
        clock();
    }

    public void HomeTruckOnAction() {
        ((Stage) truckTableView.getScene().getWindow()).close();
    }

    public void addTruckOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/AddTruck.fxml");
        Stage stage = new Stage();
        stage.setTitle("Ciągnik");
        stage.setScene(new Scene(parent));
        stage.show();
        closeStage();
    }

    private Button createEditButton(){
        Button button = new Button();
        Image image = new Image(this.getClass().getResource("/icons/editWorkerButton.jpg").toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }

    private Button createDeleteButton(){
        Button button = new Button();
        Image image = new Image(this.getClass().getResource("/icons/deleteButton.jpg").toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }

    public void clock(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            second = LocalDateTime.now().getSecond();
            minute = LocalDateTime.now().getMinute();
            hour = LocalDateTime.now().getHour();
            time.setText(hour + ":" + (minute) + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void closeStage(){
        ((Stage) truckTableView.getScene().getWindow()).close();
    }

    public void searchTruck() {
        FilteredList<TruckFx> filteredList = new FilteredList<>(listTruckModel.getTruckFxObservableList(), p -> true);

        brandFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getBrand().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });

        modelFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getModel().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });

        powerFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getPower().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });

        yearFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getYear().toString().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });

        mileageFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getMileage().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });

        roadFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getRoad().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });

        SortedList<TruckFx> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(truckTableView.comparatorProperty());
        truckTableView.setItems(sortedData);
    }

    public void pdfTruckOnAction() throws IOException, DocumentException{
        Document document = new Document();

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1.1f, 1f, 1f, 1f, 1f, 1f};
        table.setWidths(columnWidths);

        BaseFont courier = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font font = new Font(courier);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(new PdfPCell(new Phrase("Marka", font)));
        table.addCell(new PdfPCell(new Phrase("Model", font)));
        table.addCell(new PdfPCell(new Phrase("Moc", font)));
        table.addCell(new PdfPCell(new Phrase("Rok produkcji", font)));
        table.addCell(new PdfPCell(new Phrase("Przebieg", font)));
        table.addCell(new PdfPCell(new Phrase("W trasie", font)));
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j=0; j<cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (TruckFx truckFx : truck){
            table.addCell(new PdfPCell(new Phrase(truckFx.getBrand(), font)));
            table.addCell(new PdfPCell(new Phrase(truckFx.getModel(), font)));
            table.addCell(new PdfPCell(new Phrase(truckFx.getPower(), font)));
            table.addCell(String.valueOf(truckFx.getYear()));
            table.addCell(new PdfPCell(new Phrase(truckFx.getMileage(), font)));
            table.addCell(new PdfPCell(new Phrase(truckFx.getRoad(), font)));
        }

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz PDF");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File saveLoc = fileChooser.showSaveDialog(stage);

        PdfWriter.getInstance(document, new FileOutputStream(saveLoc));
        document.open();
        document.add(table);
        document.close();
    }
}
