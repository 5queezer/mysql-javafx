package application.view;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import application.MainApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainFrmController implements Initializable {
	MainApp mainApp;
	@FXML CheckMenuItem menuConnect;
	@FXML SplitMenuButton splitMenu;
	@FXML Button buttonAdd;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		splitMenu.getItems().clear();
	
		MenuItem mKunden = new MenuItem("Alle Kunden");
		MenuItem mArtikel = new MenuItem("Alle Artikel");
		MenuItem mBestellungen = new MenuItem("Alle Bestellungen");
		MenuItem mOffeneBestellungen = new MenuItem("Alle offene Bestellungen");
		
		mKunden.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	handleShowAlleKunden();
		    }
		});
		mArtikel.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	handleShowAlleArtikel();
		    }
		});
		mBestellungen.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	handleShowAlleBestellungen();
		    }
		});
		mOffeneBestellungen.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	handleShowAlleOffenenBestellungen();
		    }
		});

		splitMenu.getItems().addAll(mKunden, mArtikel, mBestellungen, mOffeneBestellungen);
		
		
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		splitMenu.disableProperty().bind(mainApp.isDisconnected);
		buttonAdd.disableProperty().bind(mainApp.isDisconnected);
	}
	
	@FXML
	private void handleConnect() {
		boolean success = mainApp.connect();
		if (success) {
			menuConnect.setSelected(true);
			menuConnect.setText("Verbunden");
			menuConnect.setDisable(true);
		} else {
			menuConnect.setDisable(false);
			menuConnect.setText("Verbinden");
			menuConnect.setDisable(false);
			menuConnect.setSelected(false);
		}
	}
	
	@FXML
	private void handleDisconnect() {
		mainApp.disconnect();
		menuConnect.setDisable(false);
		menuConnect.setText("Verbinden");
		menuConnect.setDisable(false);
		menuConnect.setSelected(false);
	}
	
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Testbeispiel SZF");
		alert.setHeaderText("Testbeispiel mit MySQL und JavaFX");
		alert.setContentText("Author: Copyright \u00a9 2015 Christian Pojoni");
		alert.showAndWait();
	}	
	
	@FXML
	private void handleExit() {
		Platform.exit();
	}
	
	@FXML
	private void handleSplitButton() {
		// leer
	}
	
	@FXML
	private void handleAddButton() {
		
	}
	
	@FXML
	private void handleShowAlleKunden() {
		mainApp.showTable("");
	}
	
	@FXML
	private void handleShowAlleArtikel() {
		
	}
	
	@FXML
	private void handleShowAlleBestellungen() {
		
	}
	
	@FXML
	private void handleShowAlleOffenenBestellungen() {
		
	}
}
