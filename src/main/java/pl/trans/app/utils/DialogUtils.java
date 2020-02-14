package pl.trans.app.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.options.Option;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils {

    private static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static void errorDialog(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.create.update"));
        errorAlert.setHeaderText(bundle.getString("error.create.update"));

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static void warningAddWorker(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(bundle.getString("error.title.add.student"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("error.content.add.student"));
        alert.showAndWait();
    }

    public static void warningAdWorkerPayment(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(bundle.getString("error.title.add.student.age"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("error.content.add.student.age"));
        alert.showAndWait();
    }

    public static void informationDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("about.information"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("content.information"));
        alert.showAndWait();
    }

    public static void editInformationDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("edit.about.information"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("edit.content.information"));
        alert.showAndWait();
    }

    public static void warningAddTruck(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(bundle.getString("error.title.add.truck"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("error.content.add.truck"));
        alert.showAndWait();
    }

    public static void informationDialogTruck(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("add.about.information.truck"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("add.content.information.truck"));
        alert.showAndWait();
    }

    public static void warningAddSemiTrailer(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(bundle.getString("error.title.add.semi.trailer"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("error.content.add.semi.trailer"));
        alert.showAndWait();
    }

    public static void informationDialogSemiTrailer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("add.about.information.semi.trailer"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("add.content.information.semi.trailer"));
        alert.showAndWait();
    }

    public static void informationDialogEdit(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("edit.about.information.semi.trailer"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("edit.content.information.semi.trailer"));
        alert.showAndWait();
    }

    public static void webOpen() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Przekierowanie");
        alert.setHeaderText("Przekierowanie na stronę internetową");
        alert.setContentText("Za chwilę zostaniesz przekierowany na stronę internetową. Czy wyrażasz zgodę?");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/icons/favicon.png"));

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            try {
                Desktop.getDesktop().browse(new URI("http://mytransapp.000webhostapp.com"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        } else {
            System.out.println("test");
        }
    }

    public static void errorRegister(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("login.error.header"));
        errorAlert.setHeaderText(bundle.getString("login.error.title"));

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }
}
