package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeSceneController {

	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	//Labels on home scene
	@FXML private Label numberLabelHome;
	@FXML private Label userLabelHome;
	@FXML private Label userHomeMain;
	@FXML private TextField newNumberField;
	@FXML private TextArea orderHistoryTextArea;
	
	public void switchToHomeNonValidate(ActionEvent event) throws IOException { 
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("home.fxml"));
		 root = loader.load();
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 
		 HomeSceneController controller = loader.getController();
		 controller.initData();
		 
		 stage.setScene(scene);
		 stage.show(); 
	}
	 
	public void switchToMenu(ActionEvent event) throws IOException
	{
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("menu.fxml"));
		 root = loader.load();
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 
		 MenuSceneController controller = loader.getController();
		 controller.initData();
		 
		 stage.setScene(scene);
		 stage.show();  
	}
	 
	public void logOut(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		LoggedInAccountData.cachedCustomers.add(LoggedInAccountData.loggedInCustomer);
		LoggedInAccountData.loggedInCustomer = null;
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void updatePhoneNumber() throws IOException
    {
		String oldNumber = LoggedInAccountData.loggedInCustomer.getPhoneNumber();
		String newNumber = newNumberField.getText();
        LoggedInAccountData.loggedInCustomer.setPhoneNumber(newNumberField.getText());
        //Edit text file
        UserNumberFileReader.updatePhoneNumberInFile(newNumber,oldNumber);
        
        System.out.println("User Number was updated successfully");
        
    }
	
	public void populateOrderHistory()
	{		
		orderHistoryTextArea.setText(LoggedInAccountData.loggedInCustomer.getFormattedOrderHistory());
	}
	
	public void initData()
	{
		numberLabelHome.setText(LoggedInAccountData.loggedInCustomer.getFormattedNumber());
		userLabelHome.setText("Hello, " + LoggedInAccountData.loggedInCustomer.getUserName());
		userHomeMain.setText("Hello, " + LoggedInAccountData.loggedInCustomer.getUserName());
		
		populateOrderHistory();
	}
	
}
