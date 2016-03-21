package travelexperts.controller;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import travelexperts.model.BookingDetail;

import travelexperts.model.Fee;
import travelexperts.model.Region;

import travelexperts.model.TripClass;

public class BookingDetailsDialogController
{
	private BookingDetail oldDetail;
    private Stage dialogStage;
    private boolean okClicked = false;
    
	@FXML
	private ComboBox<TripClass> classCombo;
	@FXML
	private ComboBox<Region> regionCombo;
	@FXML
	private ComboBox<Fee> feeCombo;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	@FXML
	private TextField itNumberField;
	@FXML
	private TextField descriptionField;
	@FXML
	private TextField destinationField;
	@FXML
	private TextField prodSupField;
	@FXML
	private TextField basePriceField;
	@FXML
	private TextField commissionField;
	@FXML
	private Label totalLabel;
	ObservableList<Fee> feeList = FXCollections.observableArrayList(Fee.getAllFees());
	ObservableList<Region> regionList = FXCollections.observableArrayList(Region.getAllRegions());
	ObservableList<TripClass> classList = FXCollections.observableArrayList(TripClass.getAllClasses());
	
	

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	feeCombo.getItems().addAll(feeList);
    	regionCombo.getItems().addAll(regionList);
    	classCombo.getItems().addAll(classList);
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
    public void setBookingDetail(BookingDetail detail) {
        this.oldDetail = detail;
        try
		{
			feeCombo.setValue(Fee.getFeeById(oldDetail.getFeeId()));
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Instant instant = Instant.ofEpochMilli(oldDetail.getTripStart().getDate());
        LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        startDate.setValue( res);
 endDate = null;
 itNumberField.setText(oldDetail.getItineraryNo().toString());
descriptionField.setText(oldDetail.getDescription());
destinationField.setText(oldDetail.getDestination());
prodSupField.setText(oldDetail.getProductSupplierId().toString());
 basePriceField.setText(oldDetail.getBasePrice().toString());
 commissionField.setText(oldDetail.getAgencyCommission().toString());
 totalLabel.setText((oldDetail.getBasePrice().doubleValue() + oldDetail.getAgencyCommission().doubleValue() + feeCombo.getSelectionModel().getSelectedItem().getFeeAmt().doubleValue()) + "");

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

/*        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }*/
        return true;
    }
}
