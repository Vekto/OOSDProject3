package travelexperts.controller;

import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import travelexperts.constants.CardTypes;
import travelexperts.constants.Province;
import travelexperts.model.Agency;
import travelexperts.model.Agent;
import travelexperts.model.Booking;
import travelexperts.model.CreditCard;
import travelexperts.model.Customer;
import travelexperts.model.Reward;
import travelexperts.util.ComboPair;

public class AgentsController
{
	private MainApp mainApp;
	
	
	@FXML
	private ComboBox<Agency> agencyCombo;
	@FXML
	private TextField agtPositionField;
	@FXML
	private TextField agtEmailField;
	@FXML
	private TextField agtBusPhoneField;
	@FXML
	private TextField agtLastNameField;
	@FXML
	private TextField agtFirstNameField;
	@FXML
	private TextField agtMiddleInitialField;
	@FXML
	private CheckBox activeCheck;
	@FXML
	private TextField searchField;
	
	@FXML
	private TableView<Customer> customersTable;
    @FXML
    private TableColumn<Customer,Integer> custIdCol;
    @FXML
    private TableColumn<Customer,String> custFirstNameCol;
    @FXML
    private TableColumn<Customer,String> custLastNameCol;
    @FXML
    private TableColumn<Customer,String> custCityCol;
    @FXML
    private TableColumn<Customer,String> custHomePhoneCol;
    @FXML
    private TabPane myTabPane;

	private Agent currentAgent;
	private ObservableList<Customer> customerData;
	
    public AgentsController()
    {       
    }
    @FXML
	private void initialize()
	{
    	//customerData.add(new Customer());
		//provComboBox.getItems().setAll(Province.values());
		//CardTypeCombo.getItems().setAll(CardTypes.values());
		//CardTypeCombo.getSelectionModel().select(0);
		try
		{	

		for (ComboPair pair : Agent.getAgentComboList())
		{
			//agentComboBox.getItems().addAll(pair);
		}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        custIdCol.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("CustomerId"));
        custFirstNameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustFirstName"));
       	custLastNameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustLastName"));
      	custCityCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustCity"));
     	custHomePhoneCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustHomePhone"));

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
	
	@FXML
	private void onSearchClick(ActionEvent event)
	{	
		try
		{
			int id = Integer.parseInt(searchField.getText());
			Agent newAgent = Agent.getAgentById(id);
			System.out.println(newAgent);
			if (newAgent != null)
			{
				currentAgent = newAgent;
				this.populateScene(currentAgent);
				myTabPane.setDisable(false);
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION,"No Agent Found!");
				
				alert.setHeaderText("No Agent with that ID");
				alert.setTitle("Invalid Agent");
				alert.show();
			}
				
		}
		catch(Exception e)
		{
		e.printStackTrace();
		Alert alert = new Alert(Alert.AlertType.INFORMATION,"Invalid Agent #");
		
		alert.setHeaderText("No Agent with that ID");
		alert.setTitle("Invalid Agent");
		alert.show();
		}
		searchField.setText(currentAgent.getAgentId() + "");
	}
	@FXML
	private void onTransferCustomerClick()
	{

		ComboPair thisPair = new ComboPair(currentAgent.getAgentId(),currentAgent.getAgtFirstName());
        boolean okClicked = mainApp.showTransferCustomerDialog(thisPair);
        if (okClicked) {
            System.out.println(thisPair + "2");
        	Customer newCustomer = new Customer();
        
        	newCustomer = customersTable.getSelectionModel().getSelectedItem();
        	System.out.println(thisPair);
        	newCustomer.setAgentId((int)thisPair.getKey());
        	
        	Customer oldCustomer = customersTable.getSelectionModel().getSelectedItem();
        	Customer.updateCustomer(newCustomer, oldCustomer);
        }
	}
	
	private void populateScene(Agent agent)
	{
		agtFirstNameField.setText(agent.getAgtFirstName());
		agtLastNameField.setText(agent.getAgtLastName());
		agtMiddleInitialField.setText(agent.getAgtMiddleInitial());
		
		agtPositionField.setText(agent.getAgtPosition());
		agtEmailField.setText(agent.getAgtEmail());
		
		agtBusPhoneField.setText(agent.getAgtBusPhone());
		

		activeCheck.setSelected(agent.isActive());

		if(customerData !=null){customerData.clear();}
		customerData = FXCollections.observableArrayList(Customer.getAllAgentCustomers(agent.getAgentId()));
		customersTable.setItems(customerData);
		
		
	}
}
