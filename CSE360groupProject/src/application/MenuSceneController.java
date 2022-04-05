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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuSceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML private Text userLabelMenu;
	@FXML private TextArea appetizerMenuTextArea;
	@FXML private TextArea entreeMenuTextArea;
	@FXML private TextArea spacialsMenuTextArea;
	@FXML private TextArea desertMenuTextArea;
	@FXML private TextArea drinkMenuTextArea;
	@FXML private TextArea cartTextArea;
	
	//combo boxes for ordering
	@FXML private ComboBox<String> cBoxAppetizer;
	@FXML private ComboBox<String> cBoxEntree;
	@FXML private ComboBox<String> cBoxSpacial;
	@FXML private ComboBox<String> cBoxDessert;
	@FXML private ComboBox<String> cBoxDrink;
	
	//Add and remove buttons for ordering
	@FXML private Button addButtonAppetizer;
	
	
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
	
	public void initData()
	{
		userLabelMenu.setText("Hello, " + LoggedInAccountData.loggedInCustomer.getUserName());
		
		populateMenu();
		populateCart();
		populateComboBoxes();
	}
	
	public void populateMenu()
	{		
		appetizerMenuTextArea.setText(LoggedInAccountData.appetizerMenu.getFormattedMenu());
		entreeMenuTextArea.setText(LoggedInAccountData.entreeMenu.getFormattedMenu());
		spacialsMenuTextArea.setText(LoggedInAccountData.spacialsMenu.getFormattedMenu());
		desertMenuTextArea.setText(LoggedInAccountData.desertMenu.getFormattedMenu());
		drinkMenuTextArea.setText(LoggedInAccountData.drinksMenu.getFormattedMenu());
	}
	
	public void populateCart()
	{		
		if(LoggedInAccountData.loggedInCustomer.getCustomerCart().isEmpty() == false)
		{
			cartTextArea.setText(LoggedInAccountData.loggedInCustomer.getCustomerCart().getFormattedFoodItemsInCart());
		}
	}
	
	public void populateComboBoxes()
	{	
		//Appetizer menu
		ArrayList<String> appetizerMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.appetizerMenu.items.size(); i++)
		{
			appetizerMenuItemNames.add(LoggedInAccountData.appetizerMenu.items.get(i).getName());
		}
		ObservableList<String> observableList = FXCollections.observableList(appetizerMenuItemNames);
		cBoxAppetizer.setItems(observableList);
		
		//Entree menu
		ArrayList<String> entreeMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.entreeMenu.items.size(); i++)
		{
			entreeMenuItemNames.add(LoggedInAccountData.entreeMenu.items.get(i).getName());
		}
		ObservableList<String> observableList2 = FXCollections.observableList(entreeMenuItemNames);
		cBoxEntree.setItems(observableList2);
		
		//Spacials menu
		ArrayList<String> spacialMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.spacialsMenu.items.size(); i++)
		{
			spacialMenuItemNames.add(LoggedInAccountData.spacialsMenu.items.get(i).getName());
		}
		ObservableList<String> observableList3 = FXCollections.observableList(spacialMenuItemNames);
		cBoxSpacial.setItems(observableList3);
		
		//Desserts menu
		ArrayList<String> dessertMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.desertMenu.items.size(); i++)
		{
			dessertMenuItemNames.add(LoggedInAccountData.desertMenu.items.get(i).getName());
		}
		ObservableList<String> observableList4 = FXCollections.observableList(dessertMenuItemNames);
		cBoxDessert.setItems(observableList4);
		
		//Drinks menu
		ArrayList<String> drinkMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.drinksMenu.items.size(); i++)
		{
			drinkMenuItemNames.add(LoggedInAccountData.drinksMenu.items.get(i).getName());
		}
		ObservableList<String> observableList5 = FXCollections.observableList(drinkMenuItemNames);
		cBoxDrink.setItems(observableList5);
	}
	
	public void addItemToCartAppetizer() throws IOException
	{
		//Get the item that we are adding
		String selectedItemName = cBoxAppetizer.getValue();
		FoodItem itemToAdd = LoggedInAccountData.appetizerMenu.getItemByName(selectedItemName);
		
		//update the logged in users cart by adding the item
		LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
		//display the added item in the cart area
		cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
	}
	
	public void addItemToCartEntree() throws IOException
	{
		//Get the item that we are adding
		String selectedItemName = cBoxEntree.getValue();
		FoodItem itemToAdd = LoggedInAccountData.entreeMenu.getItemByName(selectedItemName);
		
		//update the logged in users cart by adding the item
		LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
		//display the added item in the cart area
		cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
	}
	
	public void addItemToCartSpacial() throws IOException
	{
		//Get the item that we are adding
		String selectedItemName = cBoxSpacial.getValue();
		FoodItem itemToAdd = LoggedInAccountData.spacialsMenu.getItemByName(selectedItemName);
		
		//update the logged in users cart by adding the item
		LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
		//display the added item in the cart area
		cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
	}
	
	public void addItemToCartDessert() throws IOException
	{
		//Get the item that we are adding
		String selectedItemName = cBoxDessert.getValue();
		FoodItem itemToAdd = LoggedInAccountData.desertMenu.getItemByName(selectedItemName);
		
		//update the logged in users cart by adding the item
		LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
		//display the added item in the cart area
		cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
	}
	
	public void addItemToCartDrink() throws IOException
	{
		//Get the item that we are adding
		String selectedItemName = cBoxDrink.getValue();
		FoodItem itemToAdd = LoggedInAccountData.drinksMenu.getItemByName(selectedItemName);
		
		//update the logged in users cart by adding the item
		LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
		//display the added item in the cart area
		cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
	}
	
}
