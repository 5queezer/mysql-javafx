package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import application.view.MainFrmController;
import application.view.ShowTableController;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class MainApp extends Application {
	private Stage primaryStage;
	private Connection con = null;
	private Statement stmt = null;
	private BorderPane rootLayout;
	public BooleanProperty isDisconnected = new SimpleBooleanProperty(true);
	MainFrmController controller;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Testbeispiel mit MySQL und JavaFX");
		
		try {
			// Load root layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainFrm.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// Show the scene containing the root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			// Give the controller access to the main app
			controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public boolean connect() {
		Properties prop = new Properties();
		String connectionString = "jdbc:mysql://";
		prop.setProperty("database", "ATN97_JavaTest");
		prop.setProperty("loginTimeout", "2");
		prop.setProperty("user", "atn97");
		prop.setProperty("password", "abcd");
		prop.setProperty("host", "localhost");
		prop.setProperty("port", "3306");
		
		// Verbinden
		try {
			Class.forName("org.gjt.mm.mysql.Driver"); // register driver
			con = DriverManager.getConnection(connectionString, prop);
			stmt = con.createStatement();
			System.out.println("Verbindung erfolgreich aufgebaut");
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler " + e.getErrorCode());
			alert.setHeaderText("SQL Verbindung fehlgeschlagen");
			alert.setContentText(e.getLocalizedMessage());
			alert.showAndWait();
			isDisconnected.set(true);
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			isDisconnected.set(true);
			return false;
		}
		
		isDisconnected.set(false);
		return true;
	}
	
	public void disconnect() {
		try {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();	
			stmt = null;
			con = null;
			isDisconnected.set(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		isDisconnected.set(true);
		System.out.println("Verbindung getrennt");
	}
	

	public void showTable(String command) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ShowTable.fxml"));
			AnchorPane showTable = (AnchorPane) loader.load();
			
			// Create the dialog Stage.
			Stage stage = new Stage();
			stage.setTitle("Daten anzeigen");
			stage.setScene(new Scene(showTable));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage);
			
			ShowTableController controller = loader.getController();
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
