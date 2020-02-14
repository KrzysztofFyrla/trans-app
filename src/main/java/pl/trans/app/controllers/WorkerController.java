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
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.WorkerDao;
import pl.trans.app.database.models.Worker;
import pl.trans.app.modelFx.ListWorkerModel;
import pl.trans.app.modelFx.WorkerFx;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.FxmlUtils;
import pl.trans.app.utils.converters.ConverterWorker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkerController {

    ObservableList<WorkerFx> worker = FXCollections.observableArrayList();
    private List<WorkerFx> workerFxList = new ArrayList<>();

    @FXML
    public TableColumn<WorkerFx, String> nameColumnWorker;
    @FXML
    public TableColumn<WorkerFx, String> surnameColumnWorker;
    @FXML
    public TableColumn<WorkerFx, String> workplaceColumnWorker;
    @FXML
    public TableColumn<WorkerFx, LocalDate> dateOfEmploymentColumnWorker;
    @FXML
    public TableColumn<WorkerFx, String> positionColumnWorker;
    @FXML
    public TableColumn<WorkerFx, String> paymentColumnWorker;
    @FXML
    public TableView<WorkerFx> workerTableView;
    @FXML
    public TextField nameFilterTextField;
    @FXML
    public TextField surnameFilterTextField;
    @FXML
    public TextField workplaceFilterTextField;
    @FXML
    public TextField dateOfEmploymentFilterTextField;
    @FXML
    public TextField positionFilterTextField;
    @FXML
    public TextField paymentFilterTextField;
    @FXML
    public TableColumn<WorkerFx, WorkerFx> editButtonWorker;
    @FXML
    public TableColumn<WorkerFx, WorkerFx> deleteButtonWorker;
    public Label time;


    private ListWorkerModel listWorkerModel;

    private int minute;
    private int hour;
    private int second;

    @FXML
    void initialize() throws ApplicationException {

        WorkerDao workerDao = new WorkerDao();
        List<Worker> worker2 = workerDao.queryForAll((Worker.class));
        workerFxList.clear();
        worker2.forEach(workers -> {
            this.workerFxList.add(ConverterWorker.converterToWorkerFx(workers));
        });
        this.worker.setAll(workerFxList);

        this.listWorkerModel = new ListWorkerModel();
        try {
            this.listWorkerModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        this.workerTableView.setItems(this.listWorkerModel.getWorkerFxObservableList());
        this.nameColumnWorker.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.surnameColumnWorker.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        this.workplaceColumnWorker.setCellValueFactory(cellData -> cellData.getValue().workplaceProperty());
        this.dateOfEmploymentColumnWorker.setCellValueFactory(cellData -> cellData.getValue().dateOfEmploymentProperty());
        this.positionColumnWorker.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        this.paymentColumnWorker.setCellValueFactory(cellData -> cellData.getValue().paymentProperty());

        this.workerTableView.getSelectionModel().selectedItemProperty().addListener((observable, aldValue, newValue) -> {
            this.listWorkerModel.setWorkerFxObjectPropertyEdit(newValue);
        });

        this.workerTableView.setItems(this.listWorkerModel.getWorkerFxObservableList());

        this.editButtonWorker.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.deleteButtonWorker.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        this.editButtonWorker.setCellFactory(cellData -> new TableCell<WorkerFx, WorkerFx>(){
            Button button = createEditButton();
            @Override
            protected void updateItem(WorkerFx item, boolean empty){
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty){
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = FxmlUtils.getLoader("/fxml/EditWorker.fxml");
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                        EditWorkerController controller = loader.getController();
                        controller.getWorkerModel().setWorkerFxObjectProperty(item);
                        controller.addWorkerBind();
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        closeStage();
                    });
                }
            }
        });

        this.deleteButtonWorker.setCellFactory(param -> new TableCell<WorkerFx, WorkerFx>(){
            Button button = createDeleteButton();
            @Override
            protected void updateItem(WorkerFx item, boolean empty){
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty){
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            listWorkerModel.deleteWorker(item);
                            workerTableView.refresh();
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });
        searchWorker();

        clock();
    }

    public void addWorkersOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/AddWorker.fxml");
        Stage stage = new Stage();
        stage.setTitle("Pracownik");
        stage.setScene(new Scene(parent));
        stage.show();
        closeStage();
    }

    private void closeStage(){
        ((Stage) workerTableView.getScene().getWindow()).close();
    }

    public void searchWorker(){
        FilteredList<WorkerFx> filteredList = new FilteredList<>(listWorkerModel.getWorkerFxObservableList(), p -> true);

        nameFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });

        surnameFilterTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getSurname().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        }));

        workplaceFilterTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getWorkplace().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        }));

        dateOfEmploymentFilterTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getDateOfEmployment().toString().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        }));

        positionFilterTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getPosition().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        }));

        paymentFilterTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getPayment().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        }));

        SortedList<WorkerFx> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(workerTableView.comparatorProperty());

        workerTableView.setItems(sortedData);
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

    public void HomeWorkersOnAction() {
        closeStage();
    }

    public void pdfWorkersOnAction() throws IOException, DocumentException {
        System.out.println("Testowo");
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
        for (WorkerFx workerFx : worker){
            table.addCell(new PdfPCell(new Phrase(workerFx.getName(), font)));
            table.addCell(new PdfPCell(new Phrase(workerFx.getSurname(), font)));
            table.addCell(new PdfPCell(new Phrase(workerFx.getWorkplace(), font)));
            table.addCell(String.valueOf(workerFx.getDateOfEmployment()));
            table.addCell(new PdfPCell(new Phrase(workerFx.getPosition(), font)));
            table.addCell(new PdfPCell(new Phrase(workerFx.getPayment(), font)));
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

    public ObservableList<WorkerFx> getWorker() {
        return worker;
    }

    public void setWorker(ObservableList<WorkerFx> worker) {
        this.worker = worker;
    }
}
