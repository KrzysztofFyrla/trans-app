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
import pl.trans.app.database.dao.SemiTrailerDao;
import pl.trans.app.database.models.SemiTrailer;
import pl.trans.app.modelFx.ListSemiTrailerModel;
import pl.trans.app.modelFx.SemiTrailerFx;
import pl.trans.app.modelFx.TruckFx;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.FxmlUtils;
import pl.trans.app.utils.converters.ConverterSemiTrailer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SemiTrailerController {

    ObservableList<SemiTrailerFx> semi = FXCollections.observableArrayList();
    private List<SemiTrailerFx> semiFxList = new ArrayList<>();

    @FXML
    public TableView<SemiTrailerFx> semiTrailerTableView;
    @FXML
    public TableColumn<SemiTrailerFx, String> markColumnSemiTrailer;
    @FXML
    public TableColumn<SemiTrailerFx, String> modelColumnSemiTrailer;
    @FXML
    public TableColumn<SemiTrailerFx, String> categoryColumnSemiTrailer;
    @FXML
    public TableColumn<SemiTrailerFx, LocalDate> yearColumnSemiTrailer;
    @FXML
    public TableColumn<SemiTrailerFx, String> roadColumnSemiTrailer;
    @FXML
    public TableColumn<SemiTrailerFx, SemiTrailerFx> editButtonSemiTrailer;
    @FXML
    public TableColumn<SemiTrailerFx, SemiTrailerFx> deleteButtonSemiTrailer;
    @FXML
    public TextField markFilterTextField;
    @FXML
    public TextField modelFilterTextField;
    @FXML
    public TextField categoryFilterTextField;
    @FXML
    public TextField yearFilterTextField;
    @FXML
    public TextField roadFilterTextField;
    public Label time;

    ListSemiTrailerModel listSemiTrailerModel;

    private int minute;
    private int hour;
    private int second;

    @FXML
    void initialize() throws ApplicationException {
        SemiTrailerDao semiTrailerDao = new SemiTrailerDao();
        List<SemiTrailer> semi2 = semiTrailerDao.queryForAll(SemiTrailer.class);
        semiFxList.clear();
        semi2.forEach(semiTrailer -> {
            this.semiFxList.add(ConverterSemiTrailer.converterToSemiTrailerFx(semiTrailer));
        });
        this.semi.setAll(semiFxList);

        this.listSemiTrailerModel = new ListSemiTrailerModel();
        try {
            this.listSemiTrailerModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        this.semiTrailerTableView.setItems(this.listSemiTrailerModel.getSemiTrailerFxObservableList());
        this.markColumnSemiTrailer.setCellValueFactory(cellData -> cellData.getValue().markProperty());
        this.modelColumnSemiTrailer.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        this.categoryColumnSemiTrailer.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        this.yearColumnSemiTrailer.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        this.roadColumnSemiTrailer.setCellValueFactory(cellData -> cellData.getValue().roadProperty());

        this.semiTrailerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          this.listSemiTrailerModel.setSemiTrailerFxObjectPropertyEdit(newValue);
        });

        this.semiTrailerTableView.setItems(this.listSemiTrailerModel.getSemiTrailerFxObservableList());

        this.editButtonSemiTrailer.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.deleteButtonSemiTrailer.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        this.editButtonSemiTrailer.setCellFactory(cellData -> new TableCell<SemiTrailerFx, SemiTrailerFx>(){
            Button button = createEditButton();
            @Override
            protected void updateItem(SemiTrailerFx item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = FxmlUtils.getLoader("/fxml/EditSemiTrailer.fxml");
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }

                        EditSemiTrailerController controller = loader.getController();
                        controller.getSemiTrailerModel().setSemiTrailerFxObjectProperty(item);
                        controller.addSemiTrailer();
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        closeStage();
                    });
                }
            }
        });

        this.deleteButtonSemiTrailer.setCellFactory(param -> new TableCell<SemiTrailerFx, SemiTrailerFx>(){
            Button button = createDeleteButton();
            @Override
            protected void updateItem(SemiTrailerFx item, boolean empty){
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty){
                    setGraphic(button);
                    button.setOnAction(event -> {
                        listSemiTrailerModel.deleteSemiTrailer(item);
                        semiTrailerTableView.refresh();
                    });
                }
            }
        });
        searchSemiTrailer();
        clock();
    }

    public void homeSemiTailerOnAction() {
        ((Stage) semiTrailerTableView.getScene().getWindow()).close();
    }

    public void addSemiTrainOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/AddSemiTrailer.fxml");
        Stage stage = new Stage();
        stage.setTitle("Naczepa");
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

    private void closeStage(){
        ((Stage) semiTrailerTableView.getScene().getWindow()).close();
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

    public void searchSemiTrailer() {
        FilteredList<SemiTrailerFx> filteredList = new FilteredList<>(listSemiTrailerModel.getSemiTrailerFxObservableList(), p -> true);

        markFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getMark().toLowerCase().indexOf(lowerCaseFilter) != -1){
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

        categoryFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getCategory().toLowerCase().indexOf(lowerCaseFilter) != -1){
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

        SortedList<SemiTrailerFx> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(semiTrailerTableView.comparatorProperty());
        semiTrailerTableView.setItems(sortedData);
    }

    public void pdfSemiTrainOnAction() throws IOException, DocumentException {
        System.out.println("Testowo");
        Document document = new Document();

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1.1f, 1f, 1f, 1f, 1f};
        table.setWidths(columnWidths);

        BaseFont courier = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font font = new Font(courier);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(new PdfPCell(new Phrase("Imię", font)));
        table.addCell(new PdfPCell(new Phrase("Nazwisko", font)));
        table.addCell(new PdfPCell(new Phrase("Miejsce pracy", font)));
        table.addCell(new PdfPCell(new Phrase("Wstąpienie", font)));
        table.addCell(new PdfPCell(new Phrase("Wystąpienie", font)));
        table.addCell(new PdfPCell(new Phrase("Emerytura", font)));
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j=0; j<cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (SemiTrailerFx semiTrailerFx : semi){
            table.addCell(new PdfPCell(new Phrase(semiTrailerFx.getMark(), font)));
            table.addCell(new PdfPCell(new Phrase(semiTrailerFx.getModel(), font)));
            table.addCell(new PdfPCell(new Phrase(semiTrailerFx.getCategory(), font)));
            table.addCell(String.valueOf(semiTrailerFx.getYear()));
            table.addCell(new PdfPCell(new Phrase(semiTrailerFx.getRoad(), font)));
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
