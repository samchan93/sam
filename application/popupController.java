package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class popupController {
@FXML private TextField editUser;
@FXML private TextArea descrip;
@FXML private Button saveproBtn;
@FXML private Button cancelBtn;
@FXML private Button browseBtn;
@FXML ImageView editPic;


@FXML
public void cancelBtn(ActionEvent ae) {	//button to close pop up window
Node source = (Node) ae.getSource();
Stage stage = (Stage) source.getScene().getWindow();
stage.close();
}

@FXML
public void saveproBtn(ActionEvent ae) {	//button to save description in user profile
	String temp1 = descrip.getText();
	TextareaModel temp = new TextareaModel();
	try {
		temp.save();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Node source = (Node) ae.getSource();	//after save close the window
	Stage stage = (Stage) source.getScene().getWindow();
	stage.close();
}

@FXML
public void browseBtn(ActionEvent ae) {	//button to upload profile picture
	FileChooser choose = new FileChooser();
	choose.setTitle("upload photo");

	FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter(
			"JPG files (*.jpg)", "*.JPG");
	FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(
			"PNG files (*.png)", "*.PNG");
	choose.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

	File f = choose.showOpenDialog(null);
	try {
		if (f != null) {
			BufferedImage bufferedImage = ImageIO.read(f);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			editPic.setImage(image);
		}
	} catch (IOException ex) {
		System.out.println("cant load");
	}
}

}
