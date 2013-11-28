package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextareaModel {
	String saveInput;
	public TextareaModel () {
		
	}
	public TextareaModel (String input) {
		saveInput = input;
	}
	
	public void save() throws IOException {	//function to save data
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"src/application/bookmark.txt")));
		bw.write(saveInput);
		bw.newLine();
		bw.close();
	
	}
	
	public String read() throws IOException {	//function toretrieve data
		String line ="";
		int i=0;
		ArrayList<String> al=new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File("src/application/bookmark.txt")));
		try {
			while ((line = br.readLine()) != null) {
				al.add(line);
				al.add("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String generateArray ="";
		for (i=0;i<al.size();i++) {
			generateArray += al.get(i);
		}
		br.close();
		return generateArray;
	}
}
