package controller;

import java.util.Random;
/*
 * 
 * @author Haroon Ashraf
 * 
 * This class generates random word for the puzzle. String of words is given as input 
 * to the function generate and it returns a random word
 * */

public class WordGenerator {

	private Random selector;
	private String word;
	private int index;
	private String gameWord;

	public WordGenerator() {
		selector = new Random(System.currentTimeMillis());
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void generate(String words[], int len) {
		index = selector.nextInt(len);
		word = words[index];
		gameWord = word.replaceAll("[A-Za-z]", "-");
	}

	public String getGameWord() {
		return gameWord;
	}

	public void setGameWord(String gameWord) {
		this.gameWord = gameWord;
	}

}
