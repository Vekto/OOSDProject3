package travelexperts.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import travelexperts.util.ComboPair;
import travelexperts.constants.CardTypes;
import travelexperts.constants.Province;
import travelexperts.model.Agency;
import travelexperts.model.Agent;
import travelexperts.model.Booking;
import travelexperts.model.BookingDetail;
import travelexperts.model.CreditCard;
import travelexperts.model.Customer;
import travelexperts.model.DataBase;
import travelexperts.model.Reward;


public class CustomersController
{
	@FXML
	private TabPane myTabPane;
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
    private ComboBox<ComboPair> agentComboBox;
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
    
    
    @FXML
    private ListView<CreditCard> cardListView;
    @FXML
    private ComboBox<CardTypes> cardTypeCombo;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField cardExpMonthField;
    @FXML
    private TextField cardExpYearField;
    @FXML
    private Button cardDeleteButton;
    
    private Customer currentCust;

    private ObservableList<Reward> rewardsList;
    private ObservableList<CreditCard> cardsList;
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
		cardTypeCombo.getItems().setAll(CardTypes.values());
		cardTypeCombo.getSelectionModel().select(0);
		try
		{	

		for (ComboPair pair : Agent.getAgentComboList())
		{
			agentComboBox.getItems().addAll(pair);
			
		}
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
			Customer newCust = Customer.getCustomerById(id);
			 
			if (newCust != null)
			{
				currentCust = newCust;
				this.populateScene(currentCust);
				myTabPane.setDisable(false);
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION,"No Customer Found!");
				
				alert.setHeaderText("No Customer with that ID");
				alert.setTitle("Invalid Customer");
				alert.show();
			}
				
		}
		catch(Exception e)
		{
		e.printStackTrace();
		Alert alert = new Alert(Alert.AlertType.INFORMATION,"Invalid Customer #");
		
		alert.setHeaderText("No Customer with that ID");
		alert.setTitle("Invalid Customer");
		alert.show();
		}
		searchField.setText(currentCust.getCustomerId() + "");
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
	private void addCardClick()
	{
			SimpleDateFormat formatter = new SimpleDateFormat("MMyy");
			Date cardDate = null;
			try
			{
				cardDate = formatter.parse(cardExpMonthField.getText().trim()+ cardExpYearField.getText().trim());
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CreditCard newCard= new CreditCard(cardTypeCombo.getSelectionModel().getSelectedItem().name(), cardNumberField.getText(), cardDate, currentCust.getCustomerId());
	         int newId = CreditCard.insertCreditCard(newCard);
	            if (newId != 0)
	            {
	            	newCard.setCreditCardId(newId);
	            	cardListView.getItems().add(newCard);
	            }    
	}
	
	@FXML
	private void deleteCardClick()
	{
	CreditCard deletedCard = cardListView.getSelectionModel().getSelectedItem();
	CreditCard.deleteCard(deletedCard);
	cardListView.getItems().remove(deletedCard);
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
		Reward deletedReward = rewardListView.getSelectionModel().getSelectedItem();
		Reward.deleteReward(deletedReward);
		rewardListView.getItems().remove(deletedReward);
	}
	
	@FXML
	private void saveCustomer()
	{
		Customer updatedCust = new Customer();
		System.out.println(updatedCust);
		updatedCust.setCustomerId(currentCust.getCustomerId());
		updatedCust.setAgentId(Integer.parseInt(agentComboBox.getSelectionModel().getSelectedItem().getKey().toString()));
		updatedCust.setCustFirstName(firstNameField.getText());
		updatedCust.setCustLastName(lastNameField.getText());
		updatedCust.setCustAddress(addressField.getText());
		updatedCust.setCustCity(cityField.getText());
		updatedCust.setCustProv(provComboBox.getSelectionModel().getSelectedItem().toString());
		updatedCust.setCustEmail(emailField.getText());
		updatedCust.setCustHomePhone(homePhoneField.getText());
		updatedCust.setCustBusPhone(busPhoneField.getText());
		updatedCust.setCustPostal(postalField.getText());
		updatedCust.setCustCountry(countryField.getText());
		System.out.println(updatedCust);
		if(Customer.updateCustomer(updatedCust, currentCust) != 0)
		{
			currentCust=updatedCust;
		}
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
		cardsList = FXCollections.observableArrayList(CreditCard.getCreditCards(cust.getCustomerId()));
		cardListView.getItems().clear();
		cardListView.getItems().addAll(cardsList);
		if(cardListView.getItems().isEmpty())
		{
			cardDeleteButton.setDisable(true);
		}
		else
		{
			cardDeleteButton.setDisable(false);
		}
		if(bookingData !=null){bookingData.clear();}
		bookingData = FXCollections.observableArrayList(Booking.getAllCustBookings(cust.getCustomerId()));
		BookingTable.setItems(bookingData);
		ComboPair agentPair = null;
		try
		{
			agentPair = new ComboPair(cust.getAgentId(),(Agent.getAgentById(cust.getAgentId()).getAgtFirstName()));
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agentComboBox.getSelectionModel().select(agentPair);
		
	}
	
	
}