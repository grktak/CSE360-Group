package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	
	public void initData()
	{
		userLabelMenu.setText("Hello, " + LoggedInAccountData.loggedInCustomer.getUserName());
		
		populateMenu();
	}
	
	public void populateMenu()
	{		
		appetizerMenuTextArea.setText(LoggedInAccountData.appetizerMenu.getFormattedMenu());
		entreeMenuTextArea.setText(LoggedInAccountData.entreeMenu.getFormattedMenu());
		spacialsMenuTextArea.setText(LoggedInAccountData.spacialsMenu.getFormattedMenu());
		desertMenuTextArea.setText(LoggedInAccountData.desertMenu.getFormattedMenu());
		drinkMenuTextArea.setText(LoggedInAccountData.drinksMenu.getFormattedMenu());
	}
	
}
