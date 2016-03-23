package travelexperts.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController
{
	private MainApp mainApp;
	//is called by the main application to give a reference back to itself
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
	
	@FXML
	private void onCustomersClick(ActionEvent event)
	{
		mainApp.showCustomersView();
	}
	
	@FXML
	private void onAgentsClick(ActionEvent event)
	{
		mainApp.showAgentsView();
	}
}
