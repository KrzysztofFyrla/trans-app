package pl.trans.app.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.FxmlUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainController {

    public void openWorkerOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/Worker.fxml");
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/icons/favicon.png"));
        stage.setTitle("Pracownik");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void openTruckOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/Truck.fxml");
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/icons/favicon.png"));
        stage.setTitle("Ciągnik siodłowy");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void openSemiTrailerOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/SemiTrailer.fxml");
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/icons/favicon.png"));
        stage.setTitle("Naczepa");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void openGrafikOnAction() {
        DialogUtils.webOpen();
    }

    public void exitOnAction() {
        Platform.exit();
        //System.exit(0);
    }

    public void informationOnAction() {
        Parent parent = FxmlUtils.fxmlLoader("/fxml/Information.fxml");
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/icons/favicon.png"));
        stage.setTitle("Informacja");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
