package application;


import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class Logincontroller implements Initializable, ControlledScreen { 

ScreensController myController; 
@FXML private TextField username;
@FXML private TextField password;
@FXML private Button loginbtn;
@FXML private Label comment;


MealModel model;
JFrame frame;
@Override
public void initialize(URL url, ResourceBundle rb) {

}

@FXML 
public void login(ActionEvent a){	//login button 

	if(username.getText().equals("test")){	//
		if (password.getText().equals("pass")) {
			comment.setText("Screen is loading");
			myController.setScreen(ScreensFramework.screen2ID); 	//switch to mainpage
			
		}
		else {
		JOptionPane.showMessageDialog(frame, "Invalid password!",	//pop up alert window if password invalid
    "Warning", JOptionPane.WARNING_MESSAGE);
		}
	} 
	else {
		JOptionPane.showMessageDialog(frame, "Invalid username!",	//pop up alert window if username invalid
    "Warning",
    JOptionPane.WARNING_MESSAGE);
		}
	
}

    
    public void setScreenParent(ScreensController screenParent){	
        myController = screenParent;
    }

}
