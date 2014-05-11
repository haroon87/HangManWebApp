<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.HangManPuzzle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- @author Haroon Ashraf
This is view for the HangMan project. It displayes the puzzle peices from the Model object. User can
interact with the controller through this view to enter the guesses. The misses are displayed for convenience.
 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="float: center; margin: 50 auto;">
		<%
			//All the conditions are checked when each time the view is called by servlet. So
			// we have to get all the win and lost conditions from the session object which is always
			//updated by the servlet controller
			HangManPuzzle puzzle = (HangManPuzzle) session
					.getAttribute("Puzzle");
			Integer misses = (Integer) session.getAttribute("Misses");
			Boolean win = (Boolean) session.getAttribute("GameWin");
			Boolean lost = (Boolean) session.getAttribute("GameLost");
		%>
		<h3>
			Misses:
			<%
			out.println(misses);
		%>
		</h3>
	</div>
	<div style="width: 800px; margin: 0 auto;">
		<pre>
<%
	//Only display the puzzle blocks if they are activated. Once activated the puzzle blocks will hold an 
	// empty string value which will be updated as the misses goes on increasing
	if (puzzle.getPuzzleBlocks()[0] != null && win != true) {

		for (int i = 0; i < puzzle.getPuzzleBlocks().length; i++) {
			out.println(puzzle.getPuzzleBlocks()[i]);
		}
	}
	//Always check the win and lost condition which is updated from the servlet 
	if (win == true && lost == false) {
%><h4>You Won</h4>
			
	
	<form method=get action="hangManControllerPath">
	<input type="submit" value="Play Again">
        </form>
	<%
		}

		else if ((win == false && lost == true)) {
	%>
			<h4>You Lost</h4>
	<form method=get action="hangManControllerPath">
	<input type="submit" value="Play Again">
        </form>
        <%
        	} else {
        %>
       <!-- It is lost easier for users to click a button rather than entering a text. I am generating a virtual
       keyboard here. This will be much user friendly and will prevent any illegal entries -->
	</pre>
		<br> <br>
		<%=session.getAttribute("GameWord")%>
		<br> Enter a letter to guess
		<form method=post action="hangManControllerPath">
			<br> <input type="submit" name="guess" value="q"> <input
				type="submit" name="guess" value="w"> <input type="submit"
				name="guess" value="e"> <input type="submit" name="guess"
				value="r"> <input type="submit" name="guess" value="t">
			<input type="submit" name="guess" value="y"> <input
				type="submit" name="guess" value="u"> <input type="submit"
				name="guess" value="i"> <input type="submit" name="guess"
				value="o"> <input type="submit" name="guess" value="p">
			<br>&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name="guess"
				value="a"> <input type="submit" name="guess" value="s">
			<input type="submit" name="guess" value="d"> <input
				type="submit" name="guess" value="f"> <input type="submit"
				name="guess" value="g"> <input type="submit" name="guess"
				value="h"> <input type="submit" name="guess" value="j">
			<input type="submit" name="guess" value="k"> <input
				type="submit" name="guess" value="l"> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" name="guess" value="z"> <input
				type="submit" name="guess" value="x"> <input type="submit"
				name="guess" value="c"> <input type="submit" name="guess"
				value="v"> <input type="submit" name="guess" value="b">
			<input type="submit" name="guess" value="n"> <input
				type="submit" name="guess" value="m">
		</form>
		<%
			}
		%>
	</div>
</body>
</html>