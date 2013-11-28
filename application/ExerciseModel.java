package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExerciseModel { //save or read exercise, time, and calories burn

		String storeDate,storeExercise,storeexeTime,caloBurn;
		BufferedReader br;
		BufferedWriter bw;
		
		public ExerciseModel(String date, String exe, String exetime,String burn){
			storeDate=date;
			storeExercise=exe;
			storeexeTime=exetime;
			caloBurn=burn;
		}
		
		public ExerciseModel(String date){
			storeDate=date;
		}
		
	public void save() throws IOException{	//function to save user input
		bw = new BufferedWriter(new FileWriter(new File("src/application/exercise.csv"),true));
		bw.write(storeDate);
		bw.write(",");
		bw.write(storeExercise);
		bw.write(",");
		bw.write(storeexeTime);
		bw.write(",");
		bw.write(caloBurn);
		bw.newLine();
		bw.close();
		
	}
	
	public String read() throws FileNotFoundException {	//function to read user record
		String line ="";
		int i=0;
		ArrayList<String> al=new ArrayList<String>();	//create arraylist to contain all data
		br = new BufferedReader(new FileReader(new File("src/application/exercise.csv")));
		try {
			while ((line = br.readLine()) != null) {	//read thru every line that file contain
				String[]inputs=line.split(",");			//split into array when "," exist
				if(inputs[0].equals(storeDate)){		//match date in file with user input
					al.add(inputs[1]);					//contain exercise
					al.add("  ");						//manually show a space on textarea
					al.add(inputs[2]);					//contain time
					al.add("  ");
					al.add(inputs[3] + "Kj");			//contain calories burn
					al.add("\n");						//manually show a new line for next display
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String generateArray ="";
		for (i=0;i<al.size();i++) {						
			generateArray += al.get(i);			//add all arraylist data into a string
		}

		return generateArray;			//return the string to be display	
	}
	}



