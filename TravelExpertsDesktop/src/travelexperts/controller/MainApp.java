package travelexperts.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import travelexperts.model.Agency;
import travelexperts.model.Agent;
import travelexperts.model.BookingDetail;
import travelexperts.model.Customer;
import travelexperts.model.DataBase;
import travelexperts.model.Reward;
import travelexperts.model.TravelPackage;
import travelexperts.util.EncryptionUtil;

public class MainApp extends Application
{
		
		
	    private Stage primaryStage;
	    private BorderPane rootLayout;
	    public boolean loggedIn = false;
	    //private ObservableList<Agent> agentData = FXCollections.observableArrayList();
		
	    
	    
		
		@Override
		public void start(Stage primaryStage)
		{

			this.primaryStage = primaryStage;
			this.primaryStage.setMinWidth(200);
			this.primaryStage.setMinHeight(540);
			this.primaryStage.setTitle("Day7Exercise");
			initRootLayout();
		}

	    /**
	     * Returns the main Stage
	     * @return
	     */
	    public Stage getPrimaryStage()
	    {
	        return primaryStage;
	    }

	    /**
	     * Loads the RootLayout FXML assigns its controller and shows it
	     */
		public void initRootLayout()
		{
			try
			{
				// Load the root layout from the FXML file
				FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/RootLayout.fxml"));
				rootLayout = (BorderPane) loader.load();

				// Show the scene containing the root layout.
				Scene scene = new Scene(rootLayout);
				primaryStage.setScene(scene);

				// Give the controller access to the main app.
				RootLayoutController controller = loader.getController();
				controller.setMainApp(this);
				primaryStage.show();
			}
			catch (IOException e)
			{
				// Exception gets thrown if the fxmlfile could not be loaded
				e.printStackTrace();
			}
		}
	    
		/**
		 * Loads the AgentOverview FXML assigns its controller and shows it
		 */
	    public void showMainMenu()
	    {
	        try
	        {
	         //load the FXML file and set it into the center of the main layout
	            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/MainMenu.fxml"));
	            AnchorPane overviewPage = (AnchorPane) loader.load();
	            rootLayout.setCenter(overviewPage);
	            

	            //Give the controller access to the main app
	            MainMenuController controller = loader.getController();
	            controller.setMainApp(this);
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	    }
	    
		/**
		 * Loads the AgentOverview FXML assigns its controller and shows it
		 */
	    public void showCustomersView()
	    {
	        try
	        {
	         //load the FXML file and set it into the center of the main layout
	            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/Customers.fxml"));
	            AnchorPane customersPage = (AnchorPane) loader.load();
	            rootLayout.setCenter(customersPage);
	            
	            
	            //Give the controller access to the main app
	            CustomersController controller = loader.getController();
	            controller.setMainApp(this);

	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	    }
	    
	    public boolean showEditRewardDialog(Reward reward) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/EditRewardDialog.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Edit Person");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            EditRewardDialogController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setReward(reward);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public boolean showDetailDialog(BookingDetail detail) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/BookingDetailsDialog.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Edit Person");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            BookingDetailsDialogController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setBookingDetail(detail);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        launch(args);
	    }
	    
	   
	    
	    
		/*// TODO Auto-generated method stub
		Agent myAgent = null;
		TravelPackage myPackage = null;
		Customer myCust = null;
		Agency myAgency = null;
		
		try
		{
			for(Agent agent : Agent.getAgents())
			{
				System.out.println(agent.toString());
				
			}
			for (HashMap.Entry<Integer, String> entry : Agent.getAgentComboList().entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println("Key: " + key + " Value:" + value);
			}
			
			myAgent = Agent.getAgentById(2);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myAgent);
		System.out.println(Agent.updateAgent(myAgent, myAgent));
		
		try
		{
			for(TravelPackage pack : TravelPackage.getPackages())
			{
				System.out.println(pack.toString());
				
			}
			for (HashMap.Entry<Integer, String> entry : TravelPackage.getPackageComboList().entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println("Key: " + key + " Value:" + value);
			}
			myPackage = TravelPackage.getPackageById(4);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myPackage.toString());
		
		try
		{
			for(Customer cust : Customer.getCustomers())
			{
				System.out.println(cust.toString());
				
			}
			for (HashMap.Entry<Integer, String> entry : Customer.getCustomerComboList().entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println("Key: " + key + " Value:" + value);
			}
			myCust = Customer.getCustomerById(105);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("An Error has occured in retrieving your data");
		}
		System.out.println(myCust.toString());
		try
		{
			for(Agency agency : Agency.getAgencies())
			{
				System.out.println(agency.toString());
				
			}
			for (HashMap.Entry<Integer, String> entry : Agency.getAgencyComboList().entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println("Key: " + key + " Value:" + value);
			}
			myAgency = Agency.getAgencyById(1);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("An error has occured in retrieving your data");
		}
		System.out.println(myAgency.toString());
		System.out.println(Agency.updateAgency(myAgency, myAgency));
		
	}
	*/


}
