package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentSceneController {

	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML private Text userLabelMenu;
	 @FXML private Text cardNumberText;
	 @FXML private Text cardExpirationText;
	 @FXML private Text cvcText;
	 @FXML private TextArea cartTextArea;
	 @FXML private Button submitPaymentBtn;
	 @FXML private Label waitListLabel;
	
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
	
	public void switchToConfirmation(ActionEvent event) throws IOException
	{
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("confirmation.fxml"));
		 root = loader.load();
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 submitPayment();
		 ConfirmationSceneController controller = loader.getController();
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
	
	public void populateCart()
	{		
		if(LoggedInAccountData.loggedInCustomer.getCustomerCart().isEmpty() == false)
		{
			cartTextArea.setText(LoggedInAccountData.loggedInCustomer.getCustomerCart().getFormattedFoodItemsInCart());
		}
	}
	
	public void initData()
	{
		userLabelMenu.setText("Hello, " + LoggedInAccountData.loggedInCustomer.getUserName());
		cardNumberText.setText("Card Number: " + LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardNumber());
		cardExpirationText.setText("Card Expiration: " + LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardExpiration());
		cvcText.setText("CVC: " + LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardCVC());
		populateCart();
	}
	
	private void submitPayment()
	{
		Customer.waitListNum += 1;
		waitListLabel.setText("Wait List: " + Customer.waitListNum);
		createOrder();
	}
	
	private void createOrder()
	{
		//Take items from cart and create a new order
		Order orderToAdd = new Order();
		for(int i = 0; i < LoggedInAccountData.loggedInCustomer.getCustomerCart().getItemsInCart().size(); i++)
		{
			orderToAdd.getOrderItems().add(LoggedInAccountData.loggedInCustomer.getCustomerCart().getItemsInCart().get(i));
		}
		LoggedInAccountData.loggedInCustomer.getOrderHistory().add(orderToAdd);
		LoggedInAccountData.loggedInCustomer.getCustomerCart().emptyCart();
	}
	
	
}
