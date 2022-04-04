package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneContoller {

 private Stage stage;
 private Scene scene;
 private Parent root;
 
 
 
 
 
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
 
 public void switchToHome(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("home.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
 
 public void editPhoneNumber(ActionEvent event) throws IOException {

	 System.out.println("Number Change Successfull");
	 //add an account management class that has an editPhoneNumber method
	 		//editPhoneNumber(label.getText()); // method passes in new number and changes the current accounts number 
	 		//to the new desired number
	 	
	 }


 
 
 
}