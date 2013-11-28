package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MealModel {
	String storeDate, foodEat, caloEat;

	public MealModel(String date, String food, String calo) {
		storeDate = date;
		foodEat = food;
		caloEat = calo;
	}

	public MealModel(String date) {
		storeDate = date;
	}

	public void save() throws IOException {	//function to save meals
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"src/application/meal.csv"), true));
		bw.write(storeDate);
		bw.write(",");
		bw.write(foodEat);
		bw.write(",");
		bw.write(caloEat);
		bw.newLine();
		bw.close();

	}

	public String read() throws IOException {	//function to read meals from file
		String line = "";
		int i = 0;
		ArrayList<String> al = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"src/application/meal.csv")));
		try {
			while ((line = br.readLine()) != null) {
				// line.split(",");
				String[] inputs = line.split(",");
				if (inputs[0].equals(storeDate)) {
					al.add(inputs[1]);
					al.add(" ");
					al.add(inputs[2]);
					al.add("\n");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String generateArray = "";
		for (i = 0; i < al.size(); i++) {
			generateArray += al.get(i);
		}
		br.close();
		return generateArray;
	}
}
