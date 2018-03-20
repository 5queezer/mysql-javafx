package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ShowTableController implements Initializable {
	@FXML Button buttonClose;
	@FXML TableView table;
	private Stage dialogStage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@FXML
	private void handleClose() {
		this.dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
