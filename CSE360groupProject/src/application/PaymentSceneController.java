package application;

import java.io.IOException;
import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentSceneController {

	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML private Text cardNumberText;
	 @FXML private Text cardExpirationText;
	 @FXML private Text cvcText;
	 @FXML private TextArea cartTextArea;
	 @FXML private Button submitPaymentBtn;
	 @FXML private Label waitListLabel;
	 @FXML private Label totalCostLabel;
	 
	 //credit card payment text fields
	 @FXML private TextField cardNumField;
	 @FXML private TextField cardExpField;
	 @FXML private TextField cardCVCField;
	 
	 private DecimalFormat df = new DecimalFormat("0.00");
	
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
	
	public void switchToConfirmation(ActionEvent event) throws IOException
	{
		if(isCardValid())
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("confirmation.fxml"));
			root = loader.load();
			submitPayment();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			ConfirmationSceneController controller = loader.getController();
			controller.initData();
			stage.setScene(scene);
			stage.show();  
		}
		else
		{
			System.out.println("Card Info cannot be blank.");
		}
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
	
	public void setSavedCardInfo(ActionEvent event) throws IOException
	{
		cardNumField.setText(LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardNumber());
		cardCVCField.setText(LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardCVC());
		cardExpField.setText(LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardExpiration());
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
		if(LoggedInAccountData.loggedInCustomer.hasCoupon())
		{
			LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
		}
		
		totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		cardNumberText.setText("Card Number: " + LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardNumber());
		cardExpirationText.setText("Card Expiration: " + LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardExpiration());
		cvcText.setText("CVC: " + LoggedInAccountData.loggedInCustomer.getCreditInfo().getCardCVC());
		waitListLabel.setText("Queue: " + LoggedInAccountData.getTotalWaitTime() + " min" + "     # Orders: " + LoggedInAccountData.orderTotal);
		populateCart();
	}
	
	private void submitPayment()
	{
		LoggedInAccountData.orderTotal++ ;
		createOrder();
		setNewCardInfo();
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
	
	private void setNewCardInfo()
	{
		LoggedInAccountData.loggedInCustomer.getCreditInfo().setCardNumber(cardNumField.getText());
		LoggedInAccountData.loggedInCustomer.getCreditInfo().setCardCVC(cardCVCField.getText());
		LoggedInAccountData.loggedInCustomer.getCreditInfo().setCardExpiration(cardExpField.getText());
	}
	
	private boolean isCardValid()
	{
		if(cardNumField.getText().isEmpty() || cardExpField.getText().isEmpty() || cardCVCField.getText().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
}
