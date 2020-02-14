package pl.trans.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.trans.app.database.dbutils.DbManager;
import pl.trans.app.utils.FxmlUtils;
import pl.trans.app.utils.fillDatabase.FillDatabaseSemiTrailer;
import pl.trans.app.utils.fillDatabase.FillDatabaseTruck;
import pl.trans.app.utils.fillDatabase.FillDatabaseWorker;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Krzysztof Fyrla
 * TransApp version 1.0
 */

public class Main extends Application {

    public static final String LOGIN_FXML = "/fxml/Login.fxml";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Locale.setDefault(new Locale("en"));
        /*FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/BorderPaneMain.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.getIcons().add(new Image("/icons/favicon.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("title.test"));
        primaryStage.show();*/

        Pane borderPane = FxmlUtils.fxmlLoader(LOGIN_FXML);
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("login.title"));
        primaryStage.getIcons().add(new Image("/icons/favicon.png"));
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();

        DbManager.initDatabase();
        FillDatabaseWorker.fillDatabase();
        FillDatabaseTruck.fillDatabase();
        FillDatabaseSemiTrailer.fillDatabase();
    }
}
