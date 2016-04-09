package travelexperts.controller;

import java.awt.Event;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.rowset.internal.Row;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import travelexperts.constants.WorkStatus;
import travelexperts.model.Agent;
import travelexperts.model.Customer;
import travelexperts.model.DataBase;
import travelexperts.model.Employee;

public class ReturnCustsDialogController
{
    @FXML
    private ListView<Customer> oldCustList;
    @FXML
    private ListView<Customer> returnCustList;
    @FXML
    private Label AgentLabel;
    private Agent myAgent;
    private ArrayList<Integer> myCusts;
    private ObservableList<Customer> custData = FXCollections.observableArrayList();
    private ObservableList<Customer> returnCustData = FXCollections.observableArrayList();
    private Stage dialogStage;
    private boolean okClicked = false;
    @FXML
    private void initialize() {
    	oldCustList.setItems(custData);
    	returnCustList.setItems(returnCustData);
    }
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void handleOk(ActionEvent event) {
        okClicked = true;
        for(Customer customer : returnCustData)
        {
        	Customer newCust = Customer.getCustomerById(customer.getCustomerId());
        	newCust.setAgentId(myAgent.getAgentId());
        	System.out.println("New: "+newCust);
        	System.out.println("Old: "+customer);
        	Customer.updateCustomer(newCust, customer);
        }
        Agent.clearOldCusts(myAgent.getAgentId());
        dialogStage.close();
    }
    
    public  void SetLists(ArrayList<Integer> myCusts, Agent myAgent)
    {
    	this.myCusts = myCusts;
    	this.myAgent = myAgent;
    	for(Integer myCustId : myCusts)
    	{
    		System.out.println(myCustId);
    		custData.add(Customer.getCustomerById(myCustId));
    	}
     	myCusts.clear();
     	AgentLabel.setText(myAgent.getAgtFirstName() + " " + myAgent.getAgtLastName());
     	
    }    
    @FXML
    public void addClick(ActionEvent event)
    {
    	Customer custToMove = oldCustList.getSelectionModel().getSelectedItem();
    	returnCustData.add(custToMove);
    	custData.remove(custToMove);
    }
    @FXML
    public void removeClick(ActionEvent event)
    {
    	Customer custToMove = returnCustList.getSelectionModel().getSelectedItem();
    	returnCustData.remove(custToMove);
    	custData.add(custToMove);
    }
    




}
