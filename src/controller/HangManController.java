package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HangManPuzzle;
import model.WordModel;

/**
 * Servlet implementation class HangManController
 */
/*
 *  @author Haroon Ashraf
 *  
 *  THIS SERVLET CLASS IS MAIN ENTRY POINT TO THE APPLICATION
 *  This is the servlet used as the controller for the MVC pattern. Initializes the model objects and
 *  takes care of intereaction between the JSP view page and Model
 * */

public class HangManController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		// This string variable holds the correct guesses and is saved as session attribute
		String correctLetter = "";
		
		HangManPuzzle puzzle = new HangManPuzzle();
		WordModel wordModel = new WordModel();
		
		//This class is responsible for generating random words from the dictionary 
		WordGenerator wordGenerator = new WordGenerator();
		HttpSession session = request.getSession();
		Boolean win = false;
		Boolean lost = false;
        
		//Calling the model class which reads the file and the object of this class contains all the words read from the file
		wordModel.readFile();
		String words[] = wordModel.getWords();

		//Generating the random word to be used as the puzzle
		wordGenerator.generate(words, wordModel.getCount());
		//This is the original word without the "-"
		String word = wordGenerator.getWord();
		// The word is converted to a puzzle by replacing each character with "-"
		String gameWord = wordGenerator.getGameWord();
        
		//The puzzle word is saved in session to be used later
		request.setAttribute("GameWord", gameWord);
		
		// The model is saved as the session attribute to be used later
		session.setAttribute("Puzzle", puzzle);
		//The original word is saved as the session attribute to be used later 
		session.setAttribute("Word", word);
		//The puzzle word is saved as the session attribute to be used later
		session.setAttribute("GameWord", gameWord);
		// The empty string containing the correct guesses is saved to session initially 
		session.setAttribute("CorrectLetters", correctLetter);
		// Game win status is saved to be checked constantly in the view
		session.setAttribute("GameWin", win);
		// Game lost status is saved to be checked constantly in the view
		session.setAttribute("GameLost", lost);
		// Game misses is saved so that it can be displayed in the view for convenience s
		session.setAttribute("Misses", puzzle.getMisses());
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("View.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HangManPuzzle puzzle = new HangManPuzzle();
		HttpSession session = request.getSession();
		// The character that is submitted by pressing the character button is retrieved from request object
		String guess = request.getParameter("guess");
		//The original word is retrieved from session object
		String OriginalWord = (String) session.getAttribute("Word");
		//The up-to-date correct guesses is retrieved from the session object
		String totalCorrectWords = (String) session
				.getAttribute("CorrectLetters");
		//The correct guesses is updated always.
		totalCorrectWords = totalCorrectWords + guess;
		//The correct guesses is saved back
		session.setAttribute("CorrectLetters", totalCorrectWords);
		puzzle = (HangManPuzzle) session.getAttribute("Puzzle");
        //Checking if the guess character is present in the original word 
		if (OriginalWord.indexOf(guess.charAt(0)) != -1) {
			
			//All the characters that are not the guessed characters are replaced with "-". This is 
			// done through a regular expression. New puzzle is generated from the original word always
			// totalCorrectWords always contains the set of correct guesses
			OriginalWord = OriginalWord.replaceAll("[^" + totalCorrectWords
					+ " ]", "-");
			//Now the puzzle is updated with the new puzzle 
			session.setAttribute("GameWord", OriginalWord);
			//Winning condition is always checked 
			if (!OriginalWord.contains("-")) {
				session.setAttribute("GameWin", true);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("View.jsp");
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}

		}

		else {
			//This block is executed if there is no correct guess
			// Misses has to be updated. The gameMisses() function of Model is called and the Model is updated
			// according to the number of misses. 
			if (puzzle.getMisses() != 9) {
				puzzle.setMisses(puzzle.getMisses() + 1);
				puzzle.gameMisses();
				session.setAttribute("Misses", puzzle.getMisses());
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("View.jsp");
				if (dispatcher != null) {
					dispatcher.forward(request, response);
				}
				
				//Final miss condition is checked to mark the GameLost condition as true
			} else if (puzzle.getMisses() == 9) {
				puzzle.setMisses(puzzle.getMisses() + 1);
				puzzle.gameMisses();
				session.setAttribute("Misses", puzzle.getMisses());
				session.setAttribute("GameLost", true);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("View.jsp");
				if (dispatcher != null) {
					dispatcher.forward(request, response);
				}

			}

		}
	}

}
