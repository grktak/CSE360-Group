package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmationSceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML private Label waitListLabel;
	@FXML private Label estimatedWaitTime;
	
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
	public void initData()
	{
			waitListLabel.setText("Queue: " + LoggedInAccountData.getTotalWaitTime() + " min" + "     # Orders: " + LoggedInAccountData.orderTotal);
			int ordersAhead = LoggedInAccountData.orderTotal;
			if(ordersAhead == 1) {
				estimatedWaitTime.setText("Time: " + LoggedInAccountData.getTotalWaitTime() + "   There is " + ordersAhead + " order ahead of you");
			}
			estimatedWaitTime.setText("Time: " + LoggedInAccountData.getTotalWaitTime() + "   There are " + ordersAhead + " orders ahead of you");
	}
		
}
