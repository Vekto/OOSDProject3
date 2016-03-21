package travelexperts.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import travelexperts.constants.Province;
import travelexperts.model.Agent;
import travelexperts.model.Booking;
import travelexperts.model.BookingDetail;
import travelexperts.model.Customer;
import travelexperts.model.Reward;

public class CustomersController
{
	
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField postalField;
    @FXML
    private TextField homePhoneField;
    @FXML
    private TextField busPhoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<Province> provComboBox;
    @FXML
    private ComboBox<HashMap<Integer,String>> agentComboBox;
    @FXML
    private ListView<Reward> rewardListView;
    @FXML
    private TableView<Booking> BookingTable;
    @FXML
    private TableColumn<Booking,Integer> BookingIdCol;
    @FXML
    private TableColumn<Booking,Date> BookingDateCol;
    @FXML
    private TableColumn<Booking,String> BookingNoCol;
    @FXML
    private TableColumn<Booking,Integer> TravelerCountCol;
    @FXML
    private TableColumn<Booking,String> TripTypeIdCol;
    @FXML
    private TableColumn<Booking,Integer> PackageIdCol;
    
    
    private Customer currentCust;

    private ObservableList<Reward> rewardsList;
    private ObservableList<HashMap<Integer,String>> agentList;
    private ObservableList<Booking> bookingData;
	private MainApp mainApp;
	
    public CustomersController()
    {       
    }
    @FXML
	private void initialize()
	{
    	//bookingData.add(new Booking());
		provComboBox.getItems().setAll(Province.values());
		try
		{
			agentComboBox.getItems().setAll(Agent.getAgentComboList());
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BookingIdCol.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("BookingId"));
        BookingDateCol.setCellValueFactory(new PropertyValueFactory<Booking,Date>("BookingDate"));
        BookingNoCol.setCellValueFactory(new PropertyValueFactory<Booking,String>("BookingNo"));
        TravelerCountCol.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("TravelerCount"));
        TripTypeIdCol.setCellValueFactory(new PropertyValueFactory<Booking,String>("TripTypeId"));
        PackageIdCol.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("PackageId"));

	}
	//is called by the main application to give a reference back to itself
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
			currentCust = Customer.getCustomerById(id);
			if (currentCust != null)
			{
				this.populateScene(currentCust);
			}
		}
		catch(Exception e)
		{
		Alert alert = new Alert(Alert.AlertType.INFORMATION,"Please Enter a Customer #" + e.getMessage());
		//e.printStackTrace();
		alert.setHeaderText("No Customer with that ID");
		alert.setTitle("Invalid Customer");
		alert.show();
		}
	}
	
	@FXML
	private void addRewardClick()
	{
	        Reward tempReward = new Reward();
	        tempReward.setCustomerId(currentCust.getCustomerId());
	        boolean okClicked = mainApp.showEditRewardDialog(tempReward);
	        if (okClicked) {
	        	
	            rewardListView.getItems().add(tempReward);
	            Reward.insertReward(tempReward);
	        }
	    
	}
	@FXML
	private void loadDetailClick()
	{	BookingDetail detail = null;
		try
		{
			detail = BookingDetail.getDetailByBookingId(BookingTable.getSelectionModel().getSelectedItem().getBookingId() +"");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (detail != null)
		{
	        boolean okClicked = mainApp.showDetailDialog(detail);
		}
			
	}
	
	@FXML
	private void ViewBookinDetails()
	{
		
	}
	
	@FXML
	private void deleteRewardClick()
	{
		Reward.deleteReward(rewardListView.getSelectionModel().getSelectedItem());
	}
	private void populateScene(Customer cust)
	{
		firstNameField.setText(cust.getCustFirstName());
		lastNameField.setText(cust.getCustLastName());
		addressField.setText(cust.getCustAddress());
		cityField.setText(cust.getCustCity());
		provComboBox.setValue(Province.valueOf(cust.getCustProv()));
		emailField.setText(cust.getCustEmail());
		homePhoneField.setText(cust.getCustHomePhone());
		busPhoneField.setText(cust.getCustBusPhone());
		countryField.setText(cust.getCustCountry());
		postalField.setText(cust.getCustPostal());
		rewardsList = Reward.getCustomerRewards(cust.getCustomerId());
		rewardListView.getItems().clear();
		rewardListView.getItems().addAll(rewardsList);
		if(bookingData !=null){bookingData.clear();}
		
		bookingData = FXCollections.observableArrayList(Booking.getAllCustBookings(cust.getCustomerId()));
		BookingTable.setItems(bookingData);
	}
	
}