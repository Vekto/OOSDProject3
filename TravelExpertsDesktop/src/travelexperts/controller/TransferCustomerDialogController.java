package travelexperts.controller;

import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import travelexperts.model.Agent;
import travelexperts.model.Customer;
import travelexperts.model.DataBase;
import travelexperts.model.Reward;
import travelexperts.util.ComboPair;

public class TransferCustomerDialogController
{
	@FXML
	private ComboBox<ComboPair> agentCombo;
	@FXML
	private Label CustomerLabel;
	private AgentsController agentsController;
    private Stage dialogStage;
    private ComboPair myPair;
    private boolean okClicked = false;
    private ArrayList<ComboPair> typeHash;
    private ObservableList<ComboPair> agentList;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the reward to be edited in the dialog.
     * 
     * @param reward
     */
    public void setAgent(ComboPair myPair) {
    	this.myPair = myPair;
    	agentList = FXCollections.observableArrayList(Agent.getActiveAgentComboList());
    	agentList.remove(myPair);
    	agentCombo.getItems().addAll(agentList);

    }
    
    public void setCustomer(Customer customer)
    		{
    			CustomerLabel.setText("Select a new agent for " + customer.getCustFirstName() + " " + customer.getCustLastName());
    		}
    public void setAgentController(AgentsController agentsController)
    {
    	this.agentsController = agentsController;
    }
    		

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
    	
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	ComboPair newPair = agentCombo.getSelectionModel().getSelectedItem();
        	agentsController.setTransferAgent(newPair);        	
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        return true;
    }
}
