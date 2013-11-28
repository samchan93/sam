package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

public class SaveModel {
	String storeDate, storeHeight, storeWeight, storetotalCal, storeBmi;
	JFrame frame;

	public SaveModel(String date, String height, String weight, String total,
			String bmi1) {
		storeDate = date;
		storeHeight = height;
		storeWeight = weight;
		storetotalCal = total;
		storeBmi = bmi1;
	}

	public SaveModel(String date) {
		storeDate = date;
	}

	public void save() throws IOException { // function to save others data
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"src/application/store.csv"), true));
		bw.write(storeDate);
		bw.write(",");
		bw.write(storeHeight);
		bw.write(",");
		bw.write(storeWeight);
		bw.write(",");
		bw.write(storetotalCal);
		bw.write(",");
		bw.write(storeBmi);
		bw.newLine();
		bw.close();
	}

	public String[] read() throws FileNotFoundException { // function to
															// retrieve data
															// from file
		String line = "";
		String[] stringA = new String[6];
		String[] stringB = new String[6];
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"src/application/store.csv")));
		try {
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				stringA = line.split(",");
				if (storeDate.equals(stringA[0])) { // check for the date user
													// input
					stringB = stringA;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringB; // return as array
	}
}
