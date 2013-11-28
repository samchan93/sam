package application;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//controller for main page
public class maincontroller implements Initializable, ControlledScreen {

	ScreensController myController;
	@FXML private TextField height, weight, userbmi;
	@FXML private TextField date, food, caloriesEat, newHeight, newWeight;
	@FXML private TextField totalCalories, caloriesBurn;
	@FXML private TextField exercise, exerciseTime,saveBmi;
	@FXML private TextField viewDate1,viewHeight1,viewWeight1,viewCalo,viewBmi;
	@FXML private Button calculatebtn;
	@FXML private Button saveBtn,newRecord,exerciseBtn,bookmarkBtn;
	@FXML private ImageView photoview;
	@FXML private Button savebmBtn,mealBtn,viewBtn,weightBtn,heightBtn;
	@FXML private Button viewrecord;
	@FXML private Label graphText;
	@FXML private LineChart graph;
	@FXML TextArea descrip,viewMealrecord,viewExerciserecord,bookmark;
	JFrame frame;
	

	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub

	}

	@FXML
	void viewrecordBtn(ActionEvent ae) throws IOException {	//button in view record tab
		String tempDate = viewDate1.getText();
		SaveModel m = new SaveModel(tempDate);		
		MealModel m1 = new MealModel(tempDate);
		ExerciseModel m2 = new ExerciseModel(tempDate);
		String[] newArray;
		String tempMeal = m1.read();	//retrieve data for meals
		viewMealrecord.setText(tempMeal);	
		String tempExe = m2.read();		//retrieve data for exercises
		viewExerciserecord.setText(tempExe);	
		try {
			newArray = m.read();	//retrieve data for others data field
			if (newArray != null)
			{
				viewHeight1.setText(newArray[1]);
				viewWeight1.setText(newArray[2]);
				viewCalo.setText(newArray[3]);
				viewBmi.setText(newArray[4]);
			}

			else {
				JOptionPane.showMessageDialog(frame, "Date not exist!",
						"Warning", JOptionPane.WARNING_MESSAGE);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	//end of viewrecordBtn

	@FXML
	void weightBtn(ActionEvent ae) {	//button to display weight graph
		ObservableList wList = FXCollections.observableArrayList();	//make a observablelist to contain data to be plot
		graphText.setText("Graph of weight");
		String line = "";
		BufferedReader br;
		XYChart.Series series = new XYChart.Series();	//line graph
		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\eclipse use/gui cw2/src/application/store.csv")));

			Series weight = new Series();
			weight.setName("Weight");
			while ((line = br.readLine()) != null) {
				//System.out.println(line); to check all data
				String[] stringA = line.split(",");
				double i = Double.parseDouble(stringA[2]);
				weight.getData().add(new XYChart.Data<>(stringA[0], i));
			}
			wList.addAll(weight);	//set all data to observablelist 
			graph.setData(wList);	//plot out the graph
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void heightBtn(ActionEvent ae) {	//button to display height graph
		ObservableList hList = FXCollections.observableArrayList();
		graphText.setText("Graph of height");
		String line = "";
		BufferedReader br;
		XYChart.Series series = new XYChart.Series();
		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\eclipse use/gui cw2/src/application/store.csv")));

			Series height = new Series();
			height.setName("height");
			while ((line = br.readLine()) != null) {
				//System.out.println(line); to check all data
				String[] stringA = line.split(",");
				double i = Double.parseDouble(stringA[1]);

				height.getData().add(new XYChart.Data<>(stringA[0], i));
			}
			hList.addAll(height);
			graph.setData(hList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	//end of heightBtn

	@FXML
	void mealBtn(ActionEvent ae) throws IOException {	//button for new meal
		String storeDate = date.getText();
		String foodEat = food.getText();
		String caloEat = caloriesEat.getText();
		if (storeDate.equals("")) {	//check if fields have empty
			if (foodEat.equals(""))
				if (caloEat.equals("")) {
					JOptionPane.showMessageDialog(frame,
							"Please input all field", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
		} else {	
			MealModel m = new MealModel(storeDate, foodEat, caloEat);
			m.save();	//save into file
			food.clear();	//clear all field for next input
			caloriesEat.clear();
		}
	}	//end of mealBtn

	@FXML
	void exerciseBtn(ActionEvent ae) throws IOException {	//button for new exercise
		String storeDate = date.getText();
		String storeExercise = exercise.getText();
		String storeexeTime = exerciseTime.getText();
		String caloBurn = caloriesBurn.getText();
		if (storeDate.equals("")) {	//check if fields have empty
			if (storeExercise.equals(""))
				if (storeexeTime.equals(""))
					if (caloBurn.equals("")) {
						JOptionPane.showMessageDialog(frame,
								"Please input all field", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
		} else {
			ExerciseModel m = new ExerciseModel(storeDate, storeExercise,
					storeexeTime, caloBurn);
			m.save();	//save into file
			exercise.clear();	//clear all field for next input
			exerciseTime.clear();
			caloriesBurn.clear();
		}
	}	//end of exerciseBtn

	@FXML
	public void calculate(ActionEvent count) throws IOException {	//button to calculate BMI
		double storeHeight, storeWeight, storeBmi;
		String temp = "";
		String temp1 = height.getText();
		String temp2 = weight.getText();

		if (temp2.equals("") || temp1.equals("")) {	//check field is empty or not

			JOptionPane.showMessageDialog(frame, "Please input all field",
					"Warning", JOptionPane.WARNING_MESSAGE);
		} else {	//not empty
			try {
				storeHeight = Double.parseDouble(temp1);
				storeWeight = Double.parseDouble(temp2);
				storeBmi = storeWeight / Math.pow(storeHeight, 2);	//formula for BMI
				temp = String.format("%.2f", storeBmi);	//output 2 decimal place
			} catch (Exception exp) {	//if input alphabet
				JOptionPane.showMessageDialog(frame,
						"Please input only numbers", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
			userbmi.setText(temp);	//display result
		}
	}	//end of calculate

	@FXML
	public void newRecord(ActionEvent record) {	//button to clear all field
		date.clear();
		food.clear();
		caloriesEat.clear();
		newWeight.clear();
		newHeight.clear();
		totalCalories.clear();
		exercise.clear();
		exerciseTime.clear();
		caloriesBurn.clear();
		saveBmi.clear();
	}	//end of newRecord

	@FXML
	public void bookmarkBtn(ActionEvent book) {	//button to edit bookmark
		bookmark.setDisable(false);	//let user edit text area

	}	//end of bookmarkBtn

	@FXML
	public void savebmBtn(ActionEvent savebook) throws IOException {	//button to save bookmarks
		String input = bookmark.getText();
		TextareaModel text = new TextareaModel(input);
		text.save();	//save into file
		bookmark.setDisable(true);	//limit the access to text area

	}	//end of savebmBtn

	@FXML
	public void editProfile(ActionEvent record) {	//button to edit profile
		Stage stage = new Stage();
		try {	//pop up window
			Parent root = FXMLLoader.load(getClass().getResource(
					"pop out window.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Edit user profile");
			stage.sizeToScene();
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	//end of editProfile

	@FXML
	public void uploadPhoto(ActionEvent record) {	//button to uplaod photo

		FileChooser choose = new FileChooser();
		choose.setTitle("upload photo");
		//limit the file type to .jpg and .png only
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter(
				"JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(
				"PNG files (*.png)", "*.PNG");
		choose.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		File f = choose.showOpenDialog(null);	//open window 
		try {
			if (f != null) {	//if image is choosen
				BufferedImage bufferedImage = ImageIO.read(f);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				photoview.setImage(image);	//show at imageview
			}
		} catch (IOException ex) {
			System.out.println("cant load");
		}

	}	//end of uploadPhoto

	@FXML
	public void saveRecord(ActionEvent save) throws IOException {	//button to save whole record
		String storeDate = date.getText();
		String storeFood = food.getText();
		String storecaloEat = caloriesEat.getText();
		String storeExercise = exercise.getText();
		String storeexeTime = exerciseTime.getText();
		String storeHeight = newHeight.getText();
		String storeWeight = newWeight.getText();
		String storetotalCal = totalCalories.getText();
		String storeCalburn = caloriesBurn.getText();
		String storeBmi1 = saveBmi.getText();
		if (storeDate.equals("")) {	//check for empty fields
			if (storeExercise.equals(""))
				if (storeCalburn.equals(""))
					if (storeBmi1.equals(""))
						if (storeHeight.equals(""))
							if (storeWeight.equals("")) {
								JOptionPane.showMessageDialog(frame,
										"Please input all field", "Warning",
										JOptionPane.WARNING_MESSAGE);
							}
		} else {	//if no empty fields
			SaveModel m = new SaveModel(storeDate, storeHeight, storeWeight,
					storetotalCal, storeBmi1);
			ExerciseModel m1 = new ExerciseModel(storeDate, storeExercise,
					storeexeTime, storeCalburn);
			MealModel m2 = new MealModel(storeDate, storeFood, storecaloEat);
			m.save();	//save for height, weight, bmi, total caloriesburn
			m1.save();	//save for exercises
			m2.save();	//save for meals
		}

	}	//end of saveRecord

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	//load at the startup
		date.setPromptText("Please enter in dd/mm/yy format");
		bookmark.setPromptText("Please space after every line before enter new line");
		TextareaModel temp2 = new TextareaModel();
		try {
			String temp = temp2.read();	//read bookmark that previously saved
			//bookmark.setPrefRowCount(10);
			bookmark.setText(temp);	//display on textarea
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
