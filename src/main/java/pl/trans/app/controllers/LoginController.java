package pl.trans.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.FxmlUtils;

public class LoginController {

    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    @FXML
    public TextField loginLogin;
    @FXML
    public PasswordField passwordLogin;
    public Button loginClick;

    public void loginOnAction() {
        String uname = loginLogin.getText();
        String pword = passwordLogin.getText();

        if (uname.equals("admin") && pword.equals("admin")) {
            System.out.println("Zalogowany");
            closeStage();
            loadMain();
        }
        else {
            DialogUtils.errorRegister("Podany login lub hasło są nieprawidłowe");
        }
    }

    private void loadMain() {
        Parent parent = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);
        Stage stage = new Stage();
        //Scene scene = new Scene(parent);
        //scene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());
        stage.setTitle("TransApp");
        stage.getIcons().add(new Image("/icons/favicon.png"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void closeStage() {
        ((Stage) passwordLogin.getScene().getWindow()).close();
    }
}
