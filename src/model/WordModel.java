package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*
 * @author Haroon Ashraf
 * 
 * This class reads the input file and stores the words in a string of array. Best practice would be 
 * to store the word file in a database and to execute a business service to retrieve the words
 * 
 * */
public class WordModel {

	private int count;
	private String words[] = new String[100];

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	//Reading the file and storing the words into array
	public void readFile() throws IOException {

		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(
				"C:/Users/Haroon/Desktop/puzzles.txt"));
		while ((line = br.readLine()) != null) {
			words[count] = line;
			count++;
		}
	}

}
