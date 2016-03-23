package travelexperts.controller;

import javafx.event.Event;
import javafx.fxml.FXML;

public class AgentsController
{
	private MainApp mainApp;
	
    public AgentsController()
    {       
    }
    @FXML
	private void initialize()
	{
	}
	
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
	
	@FXML
	private void onBackClick(Event event)
	{
		mainApp.showMainMenu();
	}
}
