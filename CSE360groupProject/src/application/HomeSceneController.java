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

public class HomeSceneController {

	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	//Labels on home scene
	@FXML private Label numberLabelHome;
	@FXML private Label userLabelHome;
	@FXML private Label userHomeMain;
	
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
	
	public void initData()
	{
		numberLabelHome.setText(LoggedInAccountData.loggedInUser.getFormattedNumber());
		userLabelHome.setText("Hello, " + LoggedInAccountData.loggedInUser.getUserName());
		userHomeMain.setText("Hello, " + LoggedInAccountData.loggedInUser.getUserName());
	}
	
}
