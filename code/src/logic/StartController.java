package source;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class StartController {
	
	// Views
	@FXML	private AnchorPane background;
	@FXML	private VBox SS_VBOX;
	@FXML	private HBox SS_HBox_Headline;
	@FXML	private Label Headline;
	@FXML	private HBox SS_HBox_Buttons;
	@FXML	private Button SS_Button_Quit;
	@FXML	private Button SS_Button_Start;
	@FXML	private HBox SS_HBox_Tutorial;
	@FXML	private TextArea SS_Tutorial;
	
	
	
	//eine instanz der main class
	public Main main; 
	public void setMain(Main main){
		this.main = main;
	}
	
	
	

	@FXML
	public void handleStart(){
		
	}
	
	@FXML
	public void handleQuit(){
		
	}
	
}











