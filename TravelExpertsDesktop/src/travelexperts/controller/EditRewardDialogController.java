package travelexperts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import travelexperts.model.DataBase;
import travelexperts.model.Reward;

import travelexperts.util.ComboPair;

public class EditRewardDialogController
{

	@FXML
	private ComboBox<ComboPair> typeCombo;
	@FXML
	private TextField numberField;
	
    private Stage dialogStage;
    private Reward reward;
    private boolean okClicked = false;
    private ArrayList<ComboPair> typeHash;
    private ObservableList<ComboPair> typeList;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	//typeHash = DataBase.getComboList("Rewards","RewardId", "RwdName");
    	typeList = FXCollections.observableArrayList(DataBase.getComboList("Rewards","RewardId", "RwdName"));
    	typeCombo.getItems().addAll(typeList);
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
    public void setReward(Reward reward) {
        this.reward = reward;
        //ComboHash<Integer,String> currentReward = new ComboHash<Integer,String>(reward.getRewardId(),reward.ge)
       //typeCombo.getSelectionModel().select(););;
       numberField.setText(reward.getRwdNumber());

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
            reward.setRwdNumber(numberField.getText());
            reward.setType(typeCombo.getSelectionModel().getSelectedItem().toString());
            reward.setRewardId(typeCombo.getSelectionModel().getSelectedIndex()+1);
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
