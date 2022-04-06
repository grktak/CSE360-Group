package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
	@FXML private Label waitTimeLabel;
	
	//Combo box for order again
	@FXML ComboBox<Integer> selectOrderComboBox;
	
	public void switchToHomeNonValidate(ActionEvent event) throws IOException { 
		if(LoggedInAccountData.loggedInCustomer.getUserName().equals("manager")) {
			FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(getClass().getResource("manager.fxml"));
			 root = loader.load();
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 
			 ManagerSceneController controller = loader.getController();
			 controller.initData();
			 
			 stage.setScene(scene);
			 stage.show(); 
		}else {
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
	
	public void switchToPayment(ActionEvent event) throws IOException { 
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("payment.fxml"));
		 root = loader.load();
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 
		 PaymentSceneController controller = loader.getController();
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
        
        numberLabelHome.setText(LoggedInAccountData.loggedInCustomer.getFormattedNumber());
        
        System.out.println("User Number was updated successfully");
        
    }
	
	public void orderAgain(ActionEvent event) throws IOException
	{
		if(selectOrderComboBox.getValue() != null)
		{
			int selectedItem = selectOrderComboBox.getValue();
			
			Order selectedOrder = null;
			//Find corresponding order
			for(int i = 0; i < LoggedInAccountData.loggedInCustomer.getOrderHistory().size(); i++)
			{
				if(LoggedInAccountData.loggedInCustomer.getOrderHistory().get(i).getOrderByNumber(selectedItem) != null)
				{
					selectedOrder = LoggedInAccountData.loggedInCustomer.getOrderHistory().get(i).getOrderByNumber(selectedItem);
				}
			}
			
			//Clear cart and update with order
			if(selectedOrder != null)
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().addCartItemsFromOrder(selectedOrder);
			}
			
			//Bring to payment scene
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(getClass().getResource("payment.fxml"));
			 root = loader.load();
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 
			 PaymentSceneController controller = loader.getController();
			 controller.initData();
			 
			 stage.setScene(scene);
			 stage.show(); 
		}
	}
	
	private void populateOrderHistory()
	{		
		orderHistoryTextArea.setText(LoggedInAccountData.loggedInCustomer.getFormattedOrderHistory());
	}
	
	public void initData()
	{
		numberLabelHome.setText(LoggedInAccountData.loggedInCustomer.getFormattedNumber());
		userHomeMain.setText("Hello, " + LoggedInAccountData.loggedInCustomer.getUserName());
		waitTimeLabel.setText("Queue: " + LoggedInAccountData.getTotalWaitTime() + " min" + "     # Orders: " + LoggedInAccountData.orderTotal);
		
		populateOrderHistory();
		populateOrderAgainComboBox();
	}
	
	private void populateOrderAgainComboBox()
	{
		ArrayList<Integer> numsToAddToBox = new ArrayList<Integer>();
		
		for(int i = 0;i < LoggedInAccountData.loggedInCustomer.getOrderHistory().size(); i++)
		{
			numsToAddToBox.add(LoggedInAccountData.loggedInCustomer.getOrderHistory().get(i).getOrderNumber());
		}
		
		ObservableList<Integer> observableList = FXCollections.observableList(numsToAddToBox);
		selectOrderComboBox.setItems(observableList);
	}
	
}
