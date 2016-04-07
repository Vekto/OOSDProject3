package travelexperts.controller;

import java.math.BigDecimal;

import java.text.NumberFormat;
import java.util.ArrayList;

import java.util.Optional;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.org.apache.bcel.internal.generic.IfInstruction;
import com.sun.org.apache.xpath.internal.operations.And;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import javafx.util.StringConverter;
import sun.management.resources.agent;
import travelexperts.constants.Province;
import travelexperts.constants.WorkStatus;
import travelexperts.model.*;
import travelexperts.util.ComboPair;

public class AgentsController
{
	private MainApp mainApp;
	
    @FXML
    private TabPane myTabPane;
    @FXML
    private Tab agentTab;
    @FXML
    private Tab agencyTab;
	
    
	//AgentTab
  //****************************************************************************************************

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

	//AgnecyTab
  //****************************************************************************************************

    @FXML
    private ListView<Agency> agencyListView;
    @FXML
    private TextField agencyAddressField;
    @FXML
    private TextField agencyCityField;
    @FXML
    private TextField agencyCountryField;
    @FXML
    private TextField agencyPostalField;
    @FXML
    private TextField agencyPhoneField;
    @FXML
    private TextField agencyFaxField;
    @FXML
    private ComboBox<Province> agencyProvComboBox;
    @FXML
    private TableView<Agent> agentTableView;
    @FXML
    private TableColumn<Agent, Integer> agtIdCol;
    @FXML
    private TableColumn<Agent, String> agtFirstNameCol;
    @FXML
    private TableColumn<Agent, String> agtLastNameCol;
    @FXML
    private TableColumn<Agent, String> agtPhoneCol;
    @FXML
    private TableColumn<Agent, String> agtPositionCol;
    @FXML
    private TableColumn<Agent, String> agtEmailCol;
    
    
	//Employees Tab
  //****************************************************************************************************

    @FXML
    private TableView<Employee> empTableView;
    @FXML
    private TableColumn<Employee, Integer> empIdCol;
    @FXML
    private TableColumn<Employee, String> empFirstCol;
    @FXML
    private TableColumn<Employee, String> empLastCol;
    @FXML
    private TableColumn<Employee, String> empPhoneCol;
    @FXML
    private TableColumn<Employee, String> empEmailCol;
    @FXML
    private TableColumn<Employee, String> empPositionCol;
    @FXML
    private TableColumn<Employee, Double> empSalaryCol;
    @FXML
    private TableColumn<Employee,BigDecimal> EmpHourCol;
    @FXML
    private TableColumn<Employee, String> empStatusCol;
    @FXML
    private TextField empFirstField;
    @FXML
    private TextField empLastField;
    @FXML
    private TextField empHoursField;
    @FXML
    private TextField empSalaryField;
    @FXML
    private ComboBox<WorkStatus> empStatusCombo;
    @FXML
    private ComboBox<Position> empPositionCombo;
    @FXML
    private TextField empPhoneField;
    @FXML
    private TextField empEmailField;
    @FXML
    private TextField empMiddleInitialField;
    @FXML
    private CheckBox isAgentCheck;

	//Global Variables
  //****************************************************************************************************

    private ComboPair transferAgent;
	private Agent currentAgent;
	private Agency currentAgency;
	private Employee currentEmployee;
	private ObservableList<Customer> customerData;
	private ObservableList<Agency> agencyData;
	private ObservableList<Agent> agencyAgentData;
	private ObservableList<Employee> employeeData;
	private ObservableList<Position> PositionList;
	private ArrayList<Pair> agtCustBackup;
	private boolean holdRefresh = false;
	
	//Constructors and Initialize
	//****************************************************************************************************

    public AgentsController()
    {       
    }
    @FXML
	private void initialize()
	{
		agencyProvComboBox.getItems().setAll(Province.values());

			if(agencyData !=null){agencyData.clear();}
			agencyData = FXCollections.observableArrayList(Agency.getAgencies());	
		agencyListView.setItems(agencyData);
		agencyCombo.setItems(agencyData);
        custIdCol.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("CustomerId"));
        custFirstNameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustFirstName"));
       	custLastNameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustLastName"));
      	custCityCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustCity"));
     	custHomePhoneCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustHomePhone"));
     	
        agtIdCol.setCellValueFactory(new PropertyValueFactory<Agent,Integer>("AgentId"));
        agtFirstNameCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("AgtFirstName"));
       	agtLastNameCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("AgtLastName"));
      	agtPhoneCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("AgtBusPhone"));
     	agtPositionCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("AgtPosition"));
     	agtEmailCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("AgtEmail"));
     	agencyListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agency>()
     	{
     		@Override
     		public void changed(ObservableValue<? extends Agency> observable,Agency oldAgency, Agency newAgency)
     		{	
     			AgencyChanged(newAgency);
     		}
     	}); 	    	
     	agencyListView.getSelectionModel().select(0);
     	
     	employeeData = FXCollections.observableArrayList(Employee.getEmployees());
     	System.out.println(employeeData.toString());
     	
     	empStatusCombo.getItems().addAll(WorkStatus.values());
        empIdCol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("EmpId"));
        empFirstCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("EmpFirstName"));
       	empLastCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("EmpLastName"));
      	empPhoneCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("EmpBusPhone"));
     	empPositionCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("EmpPosition"));
     	empEmailCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("EmpEmail"));
     	EmpHourCol.setCellValueFactory(new PropertyValueFactory<Employee,BigDecimal>("EmpAvgHours"));
     	empStatusCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("EmpWorkStatus"));
     	empSalaryCol.setCellValueFactory(new PropertyValueFactory<Employee,Double>("EmpSalary"));
     	
        empSalaryCol.setCellFactory(TextFieldTableCell.<Employee, Double>forTableColumn(new StringConverter<Double>() {
            private final NumberFormat nf = 
                    NumberFormat.getCurrencyInstance();

            @Override public String toString(final Double value) {
                return nf.format(value);
            }

            @Override public Double fromString(final String s) {
                // Don't need this, unless table is editable, see DoubleStringConverter if needed
                return null; 
            }
        }));
        empTableView.setItems(employeeData);
        empTableView.getSelectionModel().selectionModeProperty().set(SelectionMode.SINGLE);
     	empTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>()
     	{
     		@Override
     		public void changed(ObservableValue<? extends Employee> observable,Employee oldEmployee, Employee newEmployee)
     		{	
     			EmployeeChanged(newEmployee);
     		}
     	}); 
        PositionList = FXCollections.observableArrayList(Position.getPositionList());
        empPositionCombo.getItems().addAll(PositionList);
     
        
	}
	
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
	
	public void setTransferAgent(ComboPair transferAgent)
	{
		this.transferAgent = transferAgent;
	}
	//Methods/Functions
//****************************************************************************************************


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
				this.populateAgentTab(currentAgent);
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
		if(currentAgent != null)
		{
		searchField.setText(currentAgent.getAgentId() + "");
		}
	}
	@FXML
	private void onTransferCustomerClick()
	{
		ComboPair thisPair = new ComboPair(currentAgent.getAgentId(),currentAgent.getAgtFirstName());
		Customer oldCustomer = customersTable.getSelectionModel().getSelectedItem();
        boolean okClicked = mainApp.showTransferCustomerDialog(thisPair,oldCustomer, this);
        if (okClicked) {
        	Customer newCustomer = new Customer();
        	newCustomer = customersTable.getSelectionModel().getSelectedItem();
        	System.out.println(transferAgent);
        	newCustomer.setAgentId((int)transferAgent.getKey());

        	Customer.updateCustomer(newCustomer, oldCustomer);
        	if(holdRefresh ==false)
        	{
        		customerData = FXCollections.observableArrayList(Customer.getAllAgentCustomers(currentAgent.getAgentId()));
        		customersTable.setItems(customerData);
        	}
        }
	}
	
	@FXML
	private void onAgentTableClick(MouseEvent event)
	{
		if(event.getClickCount() == 2)
		{
			try
			{
				int id = agentTableView.getSelectionModel().getSelectedItem().getAgentId();
				Agent newAgent = Agent.getAgentById(id);
				System.out.println(newAgent);
				if (newAgent != null)
				{
					currentAgent = newAgent;
					this.populateAgentTab(currentAgent);
					myTabPane.setDisable(false);
					myTabPane.getSelectionModel().select(agentTab);
				}
				else
				{
					throw new Exception("newAgent is null");
				}
			}
				catch(Exception e)
				{
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.INFORMATION,"Unable to Display Agent");
				
				alert.setHeaderText("Failed to Load Agent");
				alert.setTitle("Invalid Agent");
				alert.show();
				}
				searchField.setText(currentAgent.getAgentId() + "");
		}
	}
	@FXML
	private void sortSelect(ActionEvent event)
	{
		RadioButton Button = (RadioButton)event.getSource();
		System.out.println(Button.getId());
		if(Button.getId().equals("PayrollRad"))
		{
		    empPositionCol.setVisible(true);
		    empSalaryCol.setVisible(true);
			EmpHourCol.setVisible(true);
			empStatusCol.setVisible(true);
		    empPhoneCol.setVisible(false);	    
		    empEmailCol.setVisible(false);;
		}
		else
		{
		    empPositionCol.setVisible(false);
		    empSalaryCol.setVisible(false);
			EmpHourCol.setVisible(false);
			empStatusCol.setVisible(false);
		    empPhoneCol.setVisible(true);	    
		    empEmailCol.setVisible(true);
		    
		}
	}
	@FXML
	private void onIsAgentChecked(ActionEvent event)
	{
		if (isAgentCheck.selectedProperty().getValue())
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
			        "Are you sure you wish to make this employee an Agent?");
			alert.setHeaderText("Confirmation");
			alert.setTitle("Make Agent");
			alert.initStyle(StageStyle.UTILITY);
			DialogPane dialogPane = alert.getDialogPane();

			dialogPane.getStylesheets().add(myTabPane.getParent().getStylesheets().get(0));
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.CANCEL)
			{
				isAgentCheck.setSelected(false);
			}
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The agent will be made inactive. The employee will need to be re-made an agent before re-activating their agent status...would you like to continue?");
			alert.setHeaderText("Confirmation");
			alert.setTitle("Remove Agent");
			alert.initStyle(StageStyle.UTILITY);
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.setPrefWidth(500);
			dialogPane.getStylesheets().add(myTabPane.getParent().getStylesheets().get(0));
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.CANCEL)
			{
				isAgentCheck.setSelected(true);
			}
			
		}
		
	}
	
	@FXML
	private void agtSaveClick(ActionEvent event)
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to save your changes?");
		alert.setHeaderText("Confirmation");
		alert.setTitle("Save Agent");
		alert.initStyle(StageStyle.UTILITY);
		DialogPane dialogPane = alert.getDialogPane();

		dialogPane.getStylesheets().add(myTabPane.getParent().getStylesheets().get(0));
		dialogPane.getStyleClass().add("myDialog");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK)
		{
			currentEmployee = Employee.getEmployeeById(currentAgent.getAgentId());
			Employee updatedEmployee = Employee.getEmployeeById(currentAgent.getAgentId());
			updatedEmployee.setEmpFirstName(agtFirstNameField.getText());
			updatedEmployee.setEmpLastName(agtLastNameField.getText());
			updatedEmployee.setEmpMiddleInitial(agtMiddleInitialField.getText());
			updatedEmployee.setEmpBusPhone(agtBusPhoneField.getText());
			updatedEmployee.setEmpEmail(agtEmailField.getText());

			Agent updatedAgent = new Agent();
			updatedAgent.setAgtFirstName(agtFirstNameField.getText());
			updatedAgent.setAgtLastName(agtLastNameField.getText());
			updatedAgent.setAgtMiddleInitial(agtMiddleInitialField.getText());
			updatedAgent.setAgtBusPhone(agtBusPhoneField.getText());
			updatedAgent.setAgtEmail(agtEmailField.getText());
			updatedAgent.setAgtPosition(agtPositionField.getText());
			updatedAgent.setAgencyId(agencyCombo.getSelectionModel().getSelectedItem().getAgencyId());
			updatedAgent.setAgentId(currentAgent.getAgentId());
			updatedAgent.setActive(activeCheck.isSelected());
			if (currentAgent.isActive() && updatedAgent.isActive() == false)
			{
				ArrayList<Pair<Integer, Integer>> custstobackup = transferCusts(updatedAgent.getAgentId());
				if (custstobackup != null)
				{
					for (Pair<Integer, Integer> myPair : custstobackup)
					{
						Agent.custBackup(myPair);
					}
				}

			}
			if (updatedAgent.isActive())
			{
				updatedEmployee.setAgent(true);
			}
			if (currentAgent.getAgentId() == updatedAgent.getAgentId())
			{
				currentAgent = updatedAgent;
				this.populateAgentTab(updatedAgent);
			}
			Agent.updateAgent(updatedAgent, currentAgent);
			Employee.updateEmployee(updatedEmployee, currentEmployee);
			employeeData.clear();
			employeeData = FXCollections.observableArrayList(Employee.getEmployees());
			currentEmployee = null;
			employeeData = FXCollections.observableArrayList(Employee.getEmployees());
			empTableView.setItems(employeeData);
			clearEmpForm();

		}
	}
	
	@FXML
	private void empSaveClick(ActionEvent event)
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to save your changes?");
		alert.setHeaderText("Confirmation");
		alert.setTitle("Save Agent");
		alert.initStyle(StageStyle.UTILITY);
		DialogPane dialogPane = alert.getDialogPane();

		dialogPane.getStylesheets().add(myTabPane.getParent().getStylesheets().get(0));
		dialogPane.getStyleClass().add("myDialog");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK)
		{

			Employee updatedEmployee = new Employee();
			updatedEmployee.setEmpId(currentEmployee.getEmpId());
			updatedEmployee.setEmpFirstName(empFirstField.getText());
			updatedEmployee.setEmpLastName(empLastField.getText());
			updatedEmployee.setEmpMiddleInitial(empMiddleInitialField.getText());
			updatedEmployee.setEmpBusPhone(empPhoneField.getText());
			updatedEmployee.setEmpEmail(empEmailField.getText());
			updatedEmployee.setEmpPosition(empPositionCombo.getSelectionModel().getSelectedItem().toString());
			updatedEmployee.setEmpAvgHours(BigDecimal.valueOf(Double.parseDouble(empHoursField.getText())));
			updatedEmployee.setEmpSalary(Double.valueOf(empSalaryField.getText(1, empSalaryField.getLength())));
			updatedEmployee.setEmpWorkStatus(empStatusCombo.getSelectionModel().getSelectedItem().toString());
			updatedEmployee.setAgent(isAgentCheck.isSelected());
			Agent oldAgent = Agent.getAgentById(currentEmployee.getEmpId());
			Agent newAgent = new Agent();
			if (oldAgent != null || updatedEmployee.IsAgent())
			{

				newAgent.setAgtFirstName(empFirstField.getText());
				newAgent.setAgtLastName(empLastField.getText());
				newAgent.setAgtMiddleInitial(empMiddleInitialField.getText());
				newAgent.setAgtBusPhone(empPhoneField.getText());
				newAgent.setAgtEmail(empEmailField.getText());
				newAgent.setAgtPosition(empPositionCombo.getSelectionModel().getSelectedItem().toString());
				if (oldAgent == null)
				{
					newAgent.setAgencyId(1);
					newAgent.setAgentId(currentEmployee.getEmpId());
					newAgent.setActive(true);
					Agent.insertAgent(newAgent);

				}
				else
				{
					newAgent.setAgencyId(oldAgent.getAgencyId());
					newAgent.setAgentId(currentEmployee.getEmpId());

					if (updatedEmployee.IsAgent() == false)
					{
						newAgent.setActive(false);
						if (oldAgent.isActive())
						{
							transferCusts(oldAgent.getAgentId());
							ArrayList<Pair<Integer, Integer>> custstobackup = transferCusts(newAgent.getAgentId());
							if (custstobackup != null)
							{
								for (Pair<Integer, Integer> myPair : custstobackup)
								{
									Agent.custBackup(myPair);
								}
							}

						}
					}
					Agent.updateAgent(newAgent, oldAgent);
				}
				if (currentAgent.getAgentId() == oldAgent.getAgentId())
				{
					currentAgent = newAgent;
					this.populateAgentTab(newAgent);
				}

			}
			int temp = 0;
			temp = empTableView.getSelectionModel().getSelectedIndex();
			Employee.updateEmployee(updatedEmployee, currentEmployee);
			employeeData = FXCollections.observableArrayList(Employee.getEmployees());
			empTableView.setItems(employeeData);
			empTableView.getSelectionModel().clearAndSelect(temp);
			
		}

	}
		
	
	
	private void AgencyChanged(Agency newAgency)
	{
		currentAgency = newAgency;
		if(agencyAgentData !=null){agencyAgentData.clear();}
		agencyAgentData = FXCollections.observableArrayList((Agent.getAgentsByAgency(newAgency.getAgencyId())));
		agentTableView.setItems(agencyAgentData);
		agencyAddressField.setText(newAgency.getAgncyAddress());
		agencyCityField.setText(newAgency.getAgncyCity());
		agencyCountryField.setText(newAgency.getAgncyCountry());
		agencyPostalField.setText(newAgency.getAgncyPostal());
		agencyPhoneField.setText(newAgency.getAgncyPhone());
		agencyFaxField.setText(newAgency.getAgncyFax());
		if (newAgency.getAgncyProv() != null)
		{
		agencyProvComboBox.getSelectionModel().select(Province.valueOf(newAgency.getAgncyProv()));
		}
		else
		{
			agencyProvComboBox.getSelectionModel().select(-1);
		}
	}
		
	
	private void EmployeeChanged(Employee newEmployee)
	{
		if(newEmployee != null)
		{
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		currentEmployee = newEmployee;
		empLastField.setText(currentEmployee.getEmpLastName());
		empFirstField.setText(newEmployee.getEmpFirstName());
		empPhoneField.setText(newEmployee.getEmpBusPhone());
		empEmailField.setText(newEmployee.getEmpEmail());
		empMiddleInitialField.setText(newEmployee.getEmpMiddleInitial());
		empHoursField.setText(newEmployee.getEmpAvgHours().toString());
		empSalaryField.setText(nf.format(newEmployee.getEmpSalary()));
		isAgentCheck.selectedProperty().set(newEmployee.IsAgent());
		if(newEmployee.getEmpPosition()!=null)
		{
		empPositionCombo.getSelectionModel().select(new Position(newEmployee.getEmpPosition()));
		}
		else
		{
		empPositionCombo.getSelectionModel().select(-1);
		}
		empStatusCombo.getSelectionModel().select(WorkStatus.valueOf(newEmployee.getEmpWorkStatus()));
		}
		
		
	}
	private void populateAgentTab(Agent agent)
	{
		agtFirstNameField.setText(agent.getAgtFirstName());
		agtLastNameField.setText(agent.getAgtLastName());
		agtMiddleInitialField.setText(agent.getAgtMiddleInitial());
		agtPositionField.setText(agent.getAgtPosition());
		agtEmailField.setText(agent.getAgtEmail());	
		agtBusPhoneField.setText(agent.getAgtBusPhone());
		activeCheck.setSelected(agent.isActive());
		agencyCombo.getSelectionModel().select(Agency.getAgencyById(agent.getAgencyId()));
		if(customerData !=null){customerData.clear();}
		customerData = FXCollections.observableArrayList(Customer.getAllAgentCustomers(agent.getAgentId()));
		customersTable.setItems(customerData);
	}
	

	
	private ArrayList<Pair<Integer,Integer>> transferCusts(int agentId)
	{
		ArrayList<Pair<Integer, Integer>> agentPairs = new ArrayList<Pair<Integer,Integer>>();
		ArrayList<Pair<Customer, Customer>> customersChanged = new ArrayList<Pair<Customer, Customer>>();
		ComboPair thisPair = new ComboPair(currentAgent.getAgentId(), currentAgent.getAgtFirstName());
		for (Customer customer : Customer.getAllAgentCustomers(agentId))
		{
			boolean okClicked = mainApp.showTransferCustomerDialog(thisPair, customer,this);
			if (okClicked)
			{
				System.out.println(transferAgent.getValue() + "2");
				Customer newCustomer = new Customer();
				newCustomer = customer;
				System.out.println(transferAgent);
				newCustomer.setAgentId((int) transferAgent.getKey());
				customersChanged.add(new Pair<Customer, Customer>(newCustomer, customer));
				System.out.println("New customer:" + newCustomer + "Old Customer:" + customer);
			}
			else
			{
				return null;
			}
		}
		for (Pair<Customer, Customer> customer : customersChanged)
		{	
			agentPairs.add(new Pair<Integer,Integer>(agentId,customer.getValue().getCustomerId()));
			Customer.updateCustomer(customer.getKey(), customer.getValue());
		}
		System.out.println(agentPairs);
		return agentPairs;
	}
	
	public void clearEmpForm()
	{
	    empFirstField.setText("");
	    empLastField.setText("");
	    empSalaryField.setText("");
	    empHoursField.setText("");
	    empStatusCombo.getSelectionModel().select(null);
	    empPositionCombo.getSelectionModel().select(null);
	    empPhoneField.setText("");
	    empEmailField.setText("");
	    empMiddleInitialField.setText("");
	    isAgentCheck.setSelected(false);
	    empTableView.getSelectionModel().clearSelection();
	}
}
