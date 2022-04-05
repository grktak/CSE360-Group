package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneContoller {

private Stage stage;
private Scene scene;
private Parent root;
	
//Labels on login scene
@FXML private TextField usernameField;
@FXML private TextField numberField;

private boolean isManagerLogin = false;
 
 public void switchToLogin(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("login.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
}
 
 
 public void switchToGuestHome(ActionEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("guestHome.fxml"));
	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 scene = new Scene(root);
	 stage.setScene(scene);
	 stage.show();
 }
 
 public void switchToHomeValidate(ActionEvent event) throws IOException {
	 if(validateLogin())
	 {
		 if(isManagerLogin)
		 {
			 System.out.println("Manager login");
			 updateLoggedInAccountData(usernameField.getText(), numberField.getText());
			 
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(getClass().getResource("manager.fxml"));
			 root = loader.load();
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 
			 stage.setScene(scene);
			 stage.show(); 
		 }else
		 {
			 updateLoggedInAccountData(usernameField.getText(), numberField.getText());
			 
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
}
 
 private boolean validateLogin()
 {	 
	 //Hard coded manager logins
	 if(usernameField.getText().equals("manager") && numberField.getText().equals("123456789"))
	 {
		 isManagerLogin = true;
		 return true;
	 }
	 
	 if(UserNumberFileReader.userToPhoneNumbers.containsKey(usernameField.getText()))
	 {
		 if(UserNumberFileReader.userToPhoneNumbers.get(usernameField.getText()).equals(numberField.getText()))
		 {
			 isManagerLogin = false;
			 return true;
		 }
		 else
		 {
			 //invalid phone number
			 System.out.println("Invalid phone number");
			 return false;
		 }
	 }
	 else
	 {
		 //Invalid username
		 isManagerLogin = false;
		 UserNumberFileReader.WriteNewUserAndNumber(usernameField.getText(), numberField.getText());
		 return true;
	 }
 }
 
 private void updateLoggedInAccountData(String username, String number)
 {
	 boolean userAlreadyExists = false;
	 int savedIndex = 0;
	 
	 for(int i = 0; i < LoggedInAccountData.cachedCustomers.size(); i++)
	 {
		 if(LoggedInAccountData.cachedCustomers.get(i).getUserName().equals(username))
		 {
			 userAlreadyExists = true;
			 savedIndex = i;
		 }
	 }
	 
	 if(userAlreadyExists)
	 {
		 System.out.println("Grabbing cached user.");
		 LoggedInAccountData.loggedInCustomer = LoggedInAccountData.cachedCustomers.get(savedIndex);
	 }
	 else
	 {
		 System.out.println("User doesn't exist in cache");
		 LoggedInAccountData.loggedInCustomer = new Customer(username, number);
	 }
 }
 
}