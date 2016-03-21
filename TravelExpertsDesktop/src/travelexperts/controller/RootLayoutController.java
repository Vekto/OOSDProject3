package travelexperts.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import travelexperts.model.DataBase;

public class RootLayoutController
{
	private MainApp mainApp;
	
	@FXML
	private TextField txtUser;
	@FXML
	private TextField txtPass;
	@FXML
	private AnchorPane loginPane;
	@FXML
	private Menu menuFile;
	@FXML
	private Menu menuEdit;
	@FXML
	private Menu menuHelp;
	
	//is called by the main application to give a reference back to itself
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
  
    @FXML
    private void handleExit() 
    {
        System.exit(0);
    }
    
    
	//Event for handling when a Save button is clicked
	@FXML
	private void handleLogInClick(ActionEvent event) 
	{

		if(!txtUser.getText().equals("")  && !txtPass.getText().equals(""))
		{
			//if(DataBase.ValidLogin(txtUser.getText(), txtPass.getText()))
			//{
				 menuEdit.setVisible(true);
				 loginPane.setDisable(true);
				 loginPane.setVisible(false);
				 mainApp.loggedIn=true;
				 mainApp.showMainMenu();
			//}
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION,"Please Enter a Employee # and Password");
			alert.setHeaderText("Invalid Login");
			alert.setTitle("Invalid Login");
			alert.show();
		}
	}
}

