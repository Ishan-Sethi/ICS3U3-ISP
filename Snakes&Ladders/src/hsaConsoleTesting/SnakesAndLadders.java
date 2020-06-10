package hsaConsoleTesting;

import java.awt.*; // Importing Java awt for color
import java.io.*; // Importing Java IO for the buffered reader, and print writer classes
import javax.swing.JOptionPane; // Imports the error trap message (JOptionPane)
import hsa.Console; // Imports HSA console, where all the graphics are displayed

/*
	Ishan Sethi
	Snakes and Ladders
	Ms. Krasteva
	Tuesday, January 14th, 2019
	This program runs a 2 player version of the classic board game Snakes and Ladders.
	It has 7 main screens:
		Splash Screen: This screen displays a small animation of a snake slithering on the ground
					   using the Math.sin function to make a sin wave look like a snake.
		Main Menu: The selection screen where using the "w" and "s" keys you can cycle through these following options:
			1. Play Game
			2. Instructions
			3. High Scores
			4. Exit
		Ask Data: This method gets player 1 and player 2's choice of name and colour
		Display: This screen is where all the game action happens, and where everything that takes place in the game is controlled from.
		Instructions: This is where first time players can get to know the rules, with a small animation showing the functionality of ladders and snakes
		High Scores: This method reads from a file, and displays all of the top scores
		Goodbye: This screen displays a small message to the user before exiting the program
	
	Global Variable Dictionary:
	
	     Name:   |  Type:  |  Description:
	-------------|---------|------------------------------------------------------------------------------------------------------------------------
	      c      | Console |        A reference variable of the hsa Console class which displays all the program screens
	  menuChoice |  String |        The String variable which holds the value of the user's choice for the main menu (used in mainMenu() and main())
	 player1Name |  String |        The String variable which holds the value of player 1's name
	 player2Name |  String |        The String variable which holds the value of player 2's name    
	 player1Color|  Color  |        Hold's player 1's color choice
	 player2Color|  Color  |        Hold's player 2's color choice
	 player1Score|   int   |        This variable holds the score for player 1
	 player2Score|   int   |        This variable holds the score for player 2
	 xPlayer1Pos |   int   |        X position of player 1
	 yPlayer1Pos |   int   |        y position of player 1
	 xPlayer2Pos |   int   |        X position of player 2
	 yPlayer2Pos |   int   |        y position of player 2
*/

public class SnakesAndLadders {
	// Global Variable Declaration
	Console c;
	String menuChoice;
	String player1Name;
	String player2Name;
	Color player1Color;
	Color player2Color;
	int player1Score;
	int player2Score;
	int xPlayer1Pos;
	int yPlayer1Pos;
	int xPlayer2Pos;
	int yPlayer2Pos;
	int[][] boardPlayer1;
	int[][] boardPlayer2;

	// Class Constructor: Used to initialise the console
	public SnakesAndLadders() {
		// Initialising the Console
		c = new Console(30, 80, "Snakes and Ladders");
		player1Name = "";
		player2Name = "";
	}
	
	/*
		title method
		This method fills in the background, and draws a design of a snake as a "S" and a ladder as a "L"
		as the title snakes and the title.
	*/
	
	private void title() {
		// Background
		c.setColor(new Color(173, 203, 227));
		c.fillRect(0, 0, 640, 500);

		// Title
		c.setFont(new Font("Times New Roman", 1, 42));
		c.setColor(new Color(42, 77, 105));
		c.drawString("nakes & ", 200, 40);
		c.drawString("adders", 400, 70);

		// Ladder
		c.setColor(new Color(165, 121, 73));
		for (int x = 0; x <= 3; x++) {
			c.drawLine(350 - x, 10, 370 - x, 75);
			c.drawLine(375 - x, 10, 395 - x, 75);
		}
		// Rungs
		for (int y = 0; y < 4; y += 1) {
			for (int i = 0; i <= 2; i++) {
				c.drawLine(366 - (y * 6), 65 - (16 * y) - i, 391 - (y * 6), 65 - (16 * y) - i);
			}
		}

		// Snake body "s"
		c.setColor(new Color(0, 255, 0));
		c.fillOval(182, 1, 15, 15);
		c.fillOval(177, 3, 15, 15);
		c.fillOval(171, 5, 15, 15);
		c.fillOval(163, 7, 15, 15);
		c.fillOval(154, 9, 15, 15);
		c.fillOval(146, 11, 15, 15);
		c.fillOval(137, 13, 15, 15);
		c.fillOval(129, 15, 15, 15);
		c.fillOval(123, 17, 15, 15);
		c.fillOval(118, 19, 15, 15);
		c.fillOval(116, 21, 15, 15);
		c.fillOval(115, 23, 15, 15);
		c.fillOval(117, 25, 15, 15);
		c.fillOval(120, 27, 15, 15);
		c.fillOval(126, 29, 15, 15);
		c.fillOval(133, 31, 15, 15);
		c.fillOval(141, 33, 15, 15);
		c.fillOval(150, 35, 15, 15);
		c.fillOval(159, 37, 15, 15);
		c.fillOval(167, 39, 15, 15);
		c.fillOval(174, 41, 15, 15);
		c.fillOval(180, 43, 15, 15);
		c.fillOval(183, 45, 15, 15);
		c.fillOval(185, 47, 15, 15);
		c.fillOval(184, 49, 15, 15);
		c.fillOval(182, 51, 15, 15);
		c.fillOval(177, 53, 15, 15);
		c.fillOval(171, 55, 15, 15);
		c.fillOval(163, 57, 15, 15);
		c.fillOval(154, 59, 15, 15);
		c.fillOval(146, 61, 15, 15);
		c.fillOval(137, 63, 15, 15);
		c.fillOval(129, 65, 15, 15);
		c.fillOval(123, 67, 15, 15);
		c.fillOval(118, 69, 15, 15);

		// Eyes
		c.setColor(new Color(255, 255, 255));
		c.fillOval(187, 2, 4, 4);
		c.fillOval(189, 8, 4, 4);

		// Pupil
		c.setColor(new Color(0, 0, 0));
		c.fillOval(188, 3, 2, 2);
		c.fillOval(190, 9, 2, 2);

		// Tongue
		c.setColor(new Color(255, 0, 0));
		c.drawLine(206, 3, 194, 7);
	}

	/*
		pauseProgram method
		This method pauses the program, untill the user is ready to return to the main menu, used in
		highScore(), display() and instructions()
	*/

	private void pauseProgram() {
		// Pause Program
		c.setColor(new Color(42, 77, 105));
		c.setFont(new Font("Times New Roman", 3, 24));
		c.drawString("Press any key to return to the menu...", 137, 490);
		c.getChar();
	}

	/*
		mainMenu method
		This method is used to control the program flow. The user can select which menu option they would
		like to use using the "w" and "s" keys to move up and down. The entire method is in a while loop, and 
		it makes use of getChar to read whatever key the user presses. If it is a "w"or "s" it moves the option up
		or down, and highlights whichever button the user is on. Once the user presses enter, it assigns the value
		that the user was on to the menuChoice variable, then exits the loop.
		
		Local Variable Dictionary:
	
		     Name:   |  Type:  |  Description:
		-------------|---------|---------------------------------------------------------------------
		    input    |   char  |        Used along with getChar to get whatever key the user presses
		 menuControl |   int   |        Refers to which menu choice and button the user is on
	*/
	
	
	public void mainMenu() {
		// Local Variables
		char input;
		int menuControl = 1;

		// Menu loop
		while (true) {
			// Menu
			title();

			// Buttons
			for (int i = 0; i <= 270; i += 90) {
				if (i / 90 == menuControl - 1) {
					c.setColor(new Color(99, 172, 229));
					c.fillRoundRect(220, 100 + i, 200, 70, 20, 20);
				}
				c.setColor(new Color(231, 239, 246));
				c.fillRoundRect(224, 104 + i, 192, 62, 20, 20);
			}

			// Button Labels
			c.setFont(new Font("Times New Roman", 0, 32));
			c.setColor(new Color(42, 77, 105));
			c.drawString("Play Game", 250, 145);
			c.drawString("Instructions", 244, 235);
			c.drawString("High Scores", 241, 325);
			c.drawString("Exit", 291, 415);

			// Instructions
			c.setColor(new Color(42, 77, 105));
			c.fillRoundRect(75, 204, 50, 40, 5, 5);
			c.fillRoundRect(75, 256, 50, 40, 5, 5);
			c.fillRoundRect(50, 315, 124, 40, 5, 5);

			// Arrows
			// Up
			c.fillRect(145, 210, 10, 34);
			for (int i = 0; i <= 20; i++) {
				c.drawLine(150, 194, 140 + i, 210);
			}
			// Down
			c.fillRect(145, 260, 10, 34);
			for (int i = 0; i <= 20; i++) {
				c.drawLine(150, 310, 140 + i, 294);
			}
			// Labels
			c.setColor(new Color(231, 239, 246));
			c.setFont(new Font("Times New Roman", 0, 46));
			c.drawString("W", 78, 240);
			c.drawString("S", 87, 290);
			c.drawString("Enter", 60, 350);
			
			// Input
			input = c.getChar();

			// Arrow keys control
			// Up
			if ((input == 'w' || input == 'W') && menuControl != 1) {
				menuControl--;
			}
			// Down
			else if ((input == 's' || input == 'S') && menuControl != 4) {
				menuControl++;
			}
			// Enter
			else if (input == 10) {
				menuChoice = menuControl + "";
				break;
			}
		}
		// Clears the screen
		c.clear();
	}

	/*
		askData Method
		This method gets input from the user for their name and color of choice. When you use readLine() you overwrite any graphics
		so instead I use getChar to get the input 1 character at a time in a while loop. Once the user enters something, it first checks
		if the user pressed the enter key, and that the length is not 0, then it break the loop. Else if the user presses backspace, 
		the program sets the player name to a substring of itself minus 1 character. Then if the length is already greater then 10, it 
		throws another exception. Lastly, if all those pass it adds the input char to the main playerName string. Then for color, it works
		the same way as the mainMenu, except you cycle left to right using the "a" and "d" keys. Then it just repeats this again for player 2.
		
		Local Variable Dictionary:
	
		     Name:   |  Type:  |  Description:
		-------------|---------|-----------------------------------------------------------------------------------------------------------------
		    input    |   char  |        Used along with getChar to get whatever key the user presses, always a local variable in the while loops
		 colorControl|   char  |        Used to control the color selection menu
		   c1Choice  |   int   |        Refers to which color choice and button player 1 is on
		   c2Choice  |   int   |        Refers to which color choice and button player 2 is on
		
	*/
	
	public void askData() {
		// Variable declarations
		int c1Choice = 1;
		int colorControl;
		char c2Choice;
		player1Name = "";
		player2Name = "";

		// Input player 1 name
		while (true) {
			// Local Variable
			char input;

			// Title
			title();
			c.setFont(new Font("Times New Roman", 0, 24));
			c.setColor(new Color(42, 77, 105));
			c.drawString("Player 1 Name:", 75, 110);

			// Box
			c.setColor(new Color(75, 134, 180));
			c.fillRoundRect(70, 120, 490, 40, 5, 5);

			// Name
			c.setColor(new Color(231, 239, 246));
			c.setFont(new Font("Times New Roman", 1, 32));
			c.drawString(player1Name, 75, 150);

			// Input
			input = c.getChar();

			try {
				// Breaks the loop if the enter key is pressed, and the length of the name is
				// greater than 0
				if (input == 10) {
					if (player1Name.length() == 0) {
						throw new NullPointerException();
					}
					break;
				}
				// Removes the last character of the name if the backspace key is pressed
				else if (input == 8 && player1Name.length() > 0) {
					player1Name = player1Name.substring(0, player1Name.length() - 1);
				}
				// Throws error if the name is greater then 10 characters
				else if (player1Name.length() > 10) {
					throw new ArrayIndexOutOfBoundsException();
				}
				// If everything passes, then it adds the character to the name
				else {
					player1Name += input;
				}
				c.clear();
			}
			// Error trap for long user names
			catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Your username cannot be longer then 10 characters.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// Error trap for blank user names
			catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Your username cannot be blank.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		// Input player 1 Color
		while (true) {
			// Title
			title();
			c.setFont(new Font("Times New Roman", 0, 24));
			c.setColor(new Color(42, 77, 105));
			c.drawString("Player 1 Name:", 75, 110);
			c.drawString("Player 1 Color:", 75, 200);

			// Box
			c.setColor(new Color(75, 134, 180));
			c.fillRoundRect(70, 120, 490, 40, 5, 5);

			// Name
			c.setColor(new Color(231, 239, 246));
			c.setFont(new Font("Times New Roman", 1, 32));
			c.drawString(player1Name, 75, 150);

			// Color boxes
			// Shows which box is selected
			c.setColor(new Color(0, 0, 0));
			c.fillRoundRect(32 + (c1Choice * 75), 208, 34, 34, 5, 5);

			// Red
			c.setColor(Color.red);
			c.fillRoundRect(109, 210, 30, 30, 5, 5);

			// Orange
			c.setColor(Color.orange);
			c.fillRoundRect(184, 210, 30, 30, 5, 5);

			// Yellow
			c.setColor(Color.yellow);
			c.fillRoundRect(259, 210, 30, 30, 5, 5);

			// Green
			c.setColor(Color.green);
			c.fillRoundRect(334, 210, 30, 30, 5, 5);

			// Blue
			c.setColor(Color.blue);
			c.fillRoundRect(409, 210, 30, 30, 5, 5);

			// Pink
			c.setColor(Color.pink);
			c.fillRoundRect(484, 210, 30, 30, 5, 5);

			// Instructions
			c.setColor(new Color(42, 77, 105));
			c.fillRoundRect(258, 265, 50, 40, 5, 5);
			c.fillRoundRect(332, 265, 50, 40, 5, 5);
			c.fillRoundRect(258, 345, 124, 40, 5, 5);

			// Arrows
			// Up
			c.fillRect(272, 320, 34, 10);
			for (int i = 0; i <= 20; i++) {
				c.drawLine(248, 325, 272, 315 + i);
			}
			// Down
			c.fillRect(332, 320, 34, 10);
			for (int i = 0; i <= 20; i++) {
				c.drawLine(366, 315 + i, 390, 325);
			}
			// Labels
			c.setColor(new Color(231, 239, 246));
			c.setFont(new Font("Times New Roman", 0, 46));
			c.drawString("A", 267, 300);
			c.drawString("D", 340, 300);
			c.drawString("Enter", 258+10, 345+35);
			
			// Input
			colorControl = c.getChar();

			// Exits the loop, and assigns the users selected color to their player when the
			// enter key is pressed
			if (colorControl == 10) {
				if (c1Choice == 1) {
					player1Color = Color.red;
				} else if (c1Choice == 2) {
					player1Color = Color.orange;
				} else if (c1Choice == 3) {
					player1Color = Color.yellow;
				} else if (c1Choice == 4) {
					player1Color = Color.green;
				} else if (c1Choice == 5) {
					player1Color = Color.blue;
				} else if (c1Choice == 6) {
					player1Color = Color.pink;
				}
				break;
			}
			// Moves the selector to the left
			if ((colorControl == 'a' || colorControl == 'A') && c1Choice != 1) {
				c1Choice--;
			}
			// Moves the selector to the right
			else if ((colorControl == 'd' || colorControl == 'D') && c1Choice != 6) {
				c1Choice++;
			}
		}

		// Input player 2 name
		while (true) {
			// Local Variable
			char input;

			// Title
			title();
			c.setFont(new Font("Times New Roman", 0, 24));
			c.setColor(new Color(42, 77, 105));
			c.drawString("Player 2 Name:", 75, 110);

			// Box
			c.setColor(new Color(75, 134, 180));
			c.fillRoundRect(70, 120, 490, 40, 5, 5);

			// Name
			c.setColor(new Color(231, 239, 246));
			c.setFont(new Font("Times New Roman", 1, 32));
			c.drawString(player2Name, 75, 150);

			// Input
			input = c.getChar();

			try {
				// Breaks the loop if the enter key is pressed, and the length of the name is
				// greater than 0, and it is not the same as player 1's name
				if (input == 10) {
					if (player2Name.length() == 0) {
						throw new NullPointerException();
					}
					if (player2Name.equals(player1Name)) {
						throw new IllegalArgumentException();
					}
					break;
				}
				// Removes the last character of the name if the backspace key is pressed
				else if (input == 8 && player2Name.length() > 0) {
					player2Name = player2Name.substring(0, player2Name.length() - 1);
				}
				// Throws error if the name is greater then 10 characters
				else if (player2Name.length() > 10) {
					throw new ArrayIndexOutOfBoundsException();
				}
				// If everything passes, then it adds the character to the name
				else {
					player2Name += input;
				}
				c.clear();
			}
			// Error trap for long user names
			catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Your username cannot be longer then 10 characters.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// Error trap for blank user names
			catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Your username cannot be blank.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// Error trap for same name
			catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "Your username cannot be the same as Player 1.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		// Color input player 2
		// Input player 1 Color
		if (c1Choice == 1) {
			c2Choice = 2;
		} else {
			c2Choice = 1;
		}
		while (true) {
			// Title
			title();
			c.setFont(new Font("Times New Roman", 0, 24));
			c.setColor(new Color(42, 77, 105));
			c.drawString("Player 2 Name:", 75, 110);
			c.drawString("Player 2 Color:", 75, 200);

			// Box
			c.setColor(new Color(75, 134, 180));
			c.fillRoundRect(70, 120, 490, 40, 5, 5);

			// Name
			c.setColor(new Color(231, 239, 246));
			c.setFont(new Font("Times New Roman", 1, 32));
			c.drawString(player2Name, 75, 150);

			// Color boxes
			// Shows which box is selected
			c.setColor(new Color(0, 0, 0));
			c.fillRoundRect(32 + (c2Choice * 75), 208, 34, 34, 5, 5);

			// Red
			c.setColor(Color.red);
			c.fillRoundRect(109, 210, 30, 30, 5, 5);

			// Orange
			c.setColor(Color.orange);
			c.fillRoundRect(184, 210, 30, 30, 5, 5);

			// Yellow
			c.setColor(Color.yellow);
			c.fillRoundRect(259, 210, 30, 30, 5, 5);

			// Green
			c.setColor(Color.green);
			c.fillRoundRect(334, 210, 30, 30, 5, 5);

			// Blue
			c.setColor(Color.blue);
			c.fillRoundRect(409, 210, 30, 30, 5, 5);

			// Pink
			c.setColor(Color.pink);
			c.fillRoundRect(484, 210, 30, 30, 5, 5);

			// Covering player1's choice
			c.setColor(new Color(188, 188, 188, 200));
			c.fillRoundRect(34 + (c1Choice * 75), 210, 30, 30, 5, 5);

			// Instructions
			c.setColor(new Color(42, 77, 105));
			c.fillRoundRect(258, 265, 50, 40, 5, 5);
			c.fillRoundRect(332, 265, 50, 40, 5, 5);
			c.fillRoundRect(258, 345, 124, 40, 5, 5);

			// Arrows
			// Up
			c.fillRect(272, 320, 34, 10);
			for (int i = 0; i <= 20; i++) {
				c.drawLine(248, 325, 272, 315 + i);
			}
			// Down
			c.fillRect(332, 320, 34, 10);
			for (int i = 0; i <= 20; i++) {
				c.drawLine(366, 315 + i, 390, 325);
			}
			// Labels
			c.setColor(new Color(231, 239, 246));
			c.setFont(new Font("Times New Roman", 0, 46));
			c.drawString("A", 267, 300);
			c.drawString("D", 340, 300);
			c.drawString("Enter", 268, 380);
			
			// Input
			colorControl = c.getChar();

			// Exits the loop, and assigns the users selected color to their player when the
			// enter key is pressed
			if (colorControl == 10) {
				if (c2Choice == 1) {
					player2Color = Color.red;
				} else if (c2Choice == 2) {
					player2Color = Color.orange;
				} else if (c2Choice == 3) {
					player2Color = Color.yellow;
				} else if (c2Choice == 4) {
					player2Color = Color.green;
				} else if (c2Choice == 5) {
					player2Color = Color.blue;
				} else if (c2Choice == 6) {
					player2Color = Color.pink;
				}
				break;
			}
			// Moves the selector to the left
			if ((colorControl == 'a' || colorControl == 'A') && c2Choice != 1) {
				// Skipping over the color that player 1 selected
				if (c2Choice - 1 == c1Choice) {
					if (c2Choice != 2)
						c2Choice -= 2;
				} else {
					c2Choice--;
				}
			}
			// Moves the selector to the right
			else if ((colorControl == 'd' || colorControl == 'd') && c2Choice != 6) {
				// Skipping over the color that player 1 selected
				if (c2Choice + 1 == c1Choice) {
					if (c2Choice != 5)
						c2Choice += 2;
				} else {
					c2Choice++;
				}
			}
		}
	}

	/*
		display method
		This method displays the game, and controls all of the game flow. For the first move, it outputs it manually, because you are moving off
		the board onto the board. It first displays a message with which player's turn it is. It then uses the diceRoll and diceDisplay for "roll"
		a dice, then moves it by that many moves, with a manual check for the first ladder. It repeats this action for both players, loops through 
		both arrays to find where the players are on the board before getting to the main game loop. In a while loop, it first draws the background, 
		and the previous dice roll and then displaying a message for player 1. Once the player is ready to move on, it rolls the dice, and then if the 
		players where previously on the same square it resets their y position. Next in a try catch statement, it call the movePlayer function, catching
		for ArrayIndexOutOfBounds, which is thrown once a player wins a game in movePlayer. Once the player's animation is complete, it displays a win 
		message, and stores who won the game before breaking the loop and writing scores to the file.
		
		If no one won the game it moves on to reassigning the values of the player's column and row, by looping through the array. Then if they are on the same square,
		it moves them apart, and set sameSquare to true. It then repeats the message but for player 2, and then if sameSquare was true, right before the animation it 
		resets the y value, and sets sameSquare to false. Then the process repeats until someone has won the game.
		
		Local Variable Dictionary:
	
		     Name:   |      Type:     |  Description:
		-------------|----------------|------------------------------------------------------------------------------
		  spaceMove  |       int      |         Holds the value of the dice roll
		 player1Col  |       int      |         The column of player 1
		 player1Row  |       int      |         The row of player 1
		 player2Col  |       int      |         The column of player 2
		 player2Row  |       int      |         The row of player 2
		   winner    |       int      |         Stores which player one the game
		     in      | BufferedReader |         Used to take in input from the file
		   output    |   PrintWriter  |         Used to write updated scores to the file
		     temp    |    String[]    |         1-d Array that takes in input by the line, then tokenizing it by " "
		  highScore  |   String[][]   |         2-d Array that scores the player's score and name
		    size     |       int      |         How big the high scores file is
		
	*/
	
	public void display() {
		// Variable Declarations
		boardPlayer1 = new int[10][10];
		boardPlayer2 = new int[10][10];
		boolean sameSquare = false;
		int spaceMove = 0;
		int player1Col = 0;
		int player1Row = 9;
		int player2Col = 0;
		int player2Row = 9;
		int winner = 0;
		player1Score = 0;
		player2Score = 0;
		
		// Setting player 1 position
		xPlayer1Pos = 91;
		yPlayer1Pos = 415;

		// Setting player 2 position
		xPlayer2Pos = 91;
		yPlayer2Pos = 431;

		// Drawing the background and players
		background();

		// Message player 1
		c.setColor(new Color(0, 0, 0, 220));
		c.fillRect(0, 175, c.getWidth(), 150);
		c.setFont(new Font("Courier", 1, 32));
		c.setColor(new Color(255, 255, 255));
		c.drawString(player1Name + "'s turn", 300 - (player1Name.length() * 18), 250);
		c.setFont(new Font("Courier", 2, 16));
		c.drawString("Press any key to move", 216, 300);

		// Waiting until the player is ready to move
		c.getChar();

		// Drawing the background and players
		background();

		// Dice Function
		for (double i = 100; i < 500; i *= 1.1) {
			spaceMove = diceRoll();
			diceDisplay(spaceMove);
			try {
				Thread.sleep((int) i);
			} catch (Exception e) {
			}
		}
		player1Score++;

		// Animating the first move for player 1
		yPlayer1Pos += 8;
		yPlayer2Pos -= 8;
		for (int i = 0; i < (player1Col + spaceMove) * 40; i++) {
			xPlayer1Pos++;
			background();
			diceDisplay(spaceMove);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
		// Updating player board
		boardPlayer1[player1Col][9] = 0;
		player1Col += spaceMove - 1;
		boardPlayer1[player1Col][9] = 1;

		if (player1Col == 2) {
			for (int i = xPlayer1Pos; i >= 131; i--) {
				xPlayer1Pos--;
				yPlayer1Pos = (int) Math.round((xPlayer1Pos * 9.0) / 16.0) + 310;
				// Drawing background
				background();

				// Redrawing the dice
				diceDisplay(spaceMove);

				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			boardPlayer1[0][8] = 1;
			boardPlayer1[2][9] = 0;
			player1Col =  0;
			player1Row = 8;
			player1Score--;
		}

		// Player 2
		// Message player 2
		c.setColor(new Color(0, 0, 0, 220));
		c.fillRect(0, 175, c.getWidth(), 150);
		c.setFont(new Font("Courier", 0, 32));
		c.setColor(new Color(255, 255, 255));
		c.drawString(player2Name + "'s turn", 300 - (player2Name.length() * 18), 250);
		c.setFont(new Font("Courier", 2, 16));
		c.drawString("Press any key to move", 216, 300);

		// Waiting until the player is ready to move
		c.getChar();

		// Drawing the background
		background();

		// Dice Function
		for (double i = 100; i < 500; i *= 1.1) {
			spaceMove = diceRoll();
			diceDisplay(spaceMove);
			try {
				Thread.sleep((int) i);
			} catch (Exception e) {
			}
		}
		player2Score++;

		// Animating player 2
		for (int i = 0; i < (player2Col + spaceMove) * 40; i++) {
			xPlayer2Pos++;
			background();
			diceDisplay(spaceMove);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
		boardPlayer2[player2Col][9] = 0;
		player2Col += spaceMove - 1;
		boardPlayer2[player2Col][9] = 2;

		// Ladder for 3
		if (player2Col == 2) {
			for (int i = xPlayer2Pos; i >= 131; i--) {
				xPlayer2Pos--;
				yPlayer2Pos = (int) Math.round((xPlayer2Pos * 9.0) / 16.0) + 310;
				// Drawing background
				background();

				// Redrawing the dice
				diceDisplay(spaceMove);

				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			boardPlayer2[0][8] = 2;
			boardPlayer2[2][9] = 0;
			player2Col =  0;
			player1Row = 8;
			player2Score--;
		}

		// Moving the players separate if they are on the same square
		if (xPlayer1Pos == xPlayer2Pos && yPlayer1Pos == yPlayer2Pos) {
			yPlayer1Pos -= 8;
			yPlayer2Pos += 8;
			// Re-drawing background
			background();

			// Drawing the dice
			diceDisplay(spaceMove);
			sameSquare = true;
		}

		// Main Game Loop
		while (true) {
			// Drawing background and players
			background();

			// Drawing the dice
			diceDisplay(spaceMove);

			// Message player 1
			c.setColor(new Color(0, 0, 0, 220));
			c.fillRect(0, 175, c.getWidth(), 150);
			c.setFont(new Font("Courier", 0, 32));
			c.setColor(new Color(255, 255, 255));
			c.drawString(player1Name + "'s turn", 300 - (player1Name.length() * 18), 250);
			c.setFont(new Font("Courier", 2, 16));
			c.drawString("Press any key to move", 216, 300);

			// Waiting until the player is ready to move
			c.getChar();

			// Re-drawing the background
			background();

			// Dice Function Player 1
			for (double i = 100; i < 500; i *= 1.1) {
				spaceMove = diceRoll();
				diceDisplay(spaceMove);
				try {
					Thread.sleep((long) i);
				} catch (Exception e) {
				}
			}

			// Moving the players back to their normal y level if they were on the same
			// square.
			if (sameSquare) {
				yPlayer1Pos += 8;
				yPlayer2Pos -= 8;
				sameSquare = false;
			}

			// Animation
			try {
				movePlayer(spaceMove, 1, player1Col, player1Row);
			}

			// Win Screen
			catch (ArrayIndexOutOfBoundsException e) {
				// Storing who wins the game
				winner = 1;

				// Message
				c.setColor(new Color(0, 0, 0, 220));
				c.fillRect(0, 175, c.getWidth(), 150);
				c.setColor(new Color(255, 255, 255));
				c.setFont(new Font("Courier", 0, 32));
				c.drawString(player1Name + " is the winner!", 178 - (player1Name.length() * 9), 250);

				// Waiting until the user is ready to go back to the menu
				pauseProgram();
				break;
			}

			// Finding where the players are on the board
			for (int y = 0; y < 10; y++) {
				for (int x = 0; x < 10; x++) {
					// Player 1
					if (boardPlayer1[x][y] == 1) {
						player1Col = x;
						player1Row = y;
					}
					// Player 2
					if (boardPlayer2[x][y] == 2) {
						player2Col = x;
						player2Row = y;
					}
				}
			}

			// Setting player 1 position
			xPlayer1Pos = (player1Col * 40) + 131;
			yPlayer1Pos = (player1Row * 40) + 63;

			// Moving the players separate if they are on the same square
			if (xPlayer1Pos == xPlayer2Pos && yPlayer1Pos == yPlayer2Pos) {
				yPlayer1Pos -= 8;
				yPlayer2Pos += 8;
				// Re-drawing background
				background();

				// Drawing the dice
				diceDisplay(spaceMove);
				sameSquare = true;
			}

			// Message player 2
			c.setColor(new Color(0, 0, 0, 220));
			c.fillRect(0, 175, c.getWidth(), 150);
			c.setFont(new Font("Courier", 0, 32));
			c.setColor(new Color(255, 255, 255));
			c.drawString(player2Name + "'s turn", 300 - (player2Name.length() * 18), 250);
			c.setFont(new Font("Courier", 2, 16));
			c.drawString("Press any key to move", 216, 300);

			// Waiting until the player is ready to move
			c.getChar();

			// Drawing the background and players
			background();

			// Dice Function Player 2
			for (double i = 100; i < 500; i *= 1.1) {
				spaceMove = diceRoll();
				diceDisplay(spaceMove);
				try {
					Thread.sleep((long) i);
				} catch (Exception e) {
				}
			}

			// Moving the players back to their normal y level if they were on the same
			// square.
			if (sameSquare) {
				yPlayer1Pos += 8;
				yPlayer2Pos -= 8;
				sameSquare = false;
			}

			// Animation
			try {
				movePlayer(spaceMove, 2, player2Col, player2Row);
			}
			// Win Screen
			catch (ArrayIndexOutOfBoundsException e) {
				// Storing who wins the game
				winner = 2;

				// Message
				c.setColor(new Color(0, 0, 0, 220));
				c.fillRect(0, 175, c.getWidth(), 150);
				c.setColor(new Color(255, 255, 255));
				c.setFont(new Font("Courier", 0, 32));
				c.drawString(player2Name + " is the winner!", 178 - (player2Name.length() * 9), 250);

				// Waiting until the user is ready to go back to the menu
				pauseProgram();
				char temp = c.getChar();
				while (temp != 'a')
					temp = c.getChar();
				break;
			}
			
			// Finding where the players are on the board
			for (int y = 0; y < 10; y++) {
				for (int x = 0; x < 10; x++) {
					// Player 1
					if (boardPlayer1[x][y] == 1) {
						player1Col = x;
						player1Row = y;
					}
					// Player 2
					if (boardPlayer2[x][y] == 2) {
						player2Col = x;
						player2Row = y;
					}
				}
			}

			// Setting player 2 position
			xPlayer2Pos = (player2Col * 40) + 131;
			yPlayer2Pos = (player2Row * 40) + 63;

			// Moving the players separate if they are on the same square
			if (xPlayer1Pos == xPlayer2Pos && yPlayer1Pos == yPlayer2Pos) {
				yPlayer1Pos -= 8;
				yPlayer2Pos += 8;
				// Drawing background
				background();

				// Player 1
				c.setColor(Color.red);
				c.fillOval(xPlayer1Pos, yPlayer1Pos, 15, 15);

				// Player 2
				c.setColor(Color.blue);
				c.fillOval(xPlayer2Pos, yPlayer2Pos, 15, 15);

				// Drawing the dice
				diceDisplay(spaceMove);
				sameSquare = true;
			}
		}

		// File reading/writing for high scores, once someone wins
		BufferedReader in;
		PrintWriter output;

		try {
			in = new BufferedReader(new FileReader("HighScores"));
			int size = Integer.parseInt(in.readLine());
			// If the file is empty, it writes the first game played as the top high score,
			// and returns from the method
			if (size == -1) {
				String[][] highScore = new String[2][1];
				if (winner == 1) {
					// Stores player 1's score if they won
					highScore[0][0] = player1Name;
					highScore[1][0] = player1Score + "";
				} else if (winner == 2) {
					// Stores playe 2's score if they won
					highScore[0][0] = player2Name;
					highScore[1][0] = player2Score + "";
				}
				// Closing scanner
				in.close();

				// Writing to the file
				output = new PrintWriter(new FileWriter("HighScores"));
				output.println(1);
				output.println(highScore[0][0] + " " + highScore[1][0]);
				output.close();
				return;
			}

			// Initialising the variable
			String[][] highScore = new String[2][size + 1];
			for (int i = 0; i < size; i++) {
				// Input
				String[] temp = in.readLine().split(" ");
				// Checking if the user score is lower then the current score
				if (Integer.parseInt(temp[1]) > player1Score && winner == 1) {
					if (winner == 1) {
						highScore[0][i] = player1Name;
						highScore[1][i] = player1Score + "";
					} else {
						highScore[0][i] = player2Name;
						highScore[1][i] = player2Score + "";
					}
					// Writing the rest of the names
					highScore[0][i + 1] = temp[0];
					highScore[1][i + 1] = temp[1];
					for (int x = i + 2; x < highScore[0].length; x++) {
						temp = in.readLine().split(" ");
						highScore[0][x] = temp[0];
						highScore[1][x] = temp[1];
					}
					// Exiting the loop
					in.close();
					break;
				}
				// Writing the names if the score is higher
				else {
					highScore[0][i] = temp[0];
					highScore[1][i] = temp[1];
				}
			}

			// If it loops through the entire file, and the user scored the worst, it writes
			// it at the bottom of the array
			if (highScore[0][highScore[0].length - 1] == null) {
				// Player 1
				if (winner == 1) {
					highScore[0][highScore[0].length - 1] = player1Name;
					highScore[1][highScore[0].length - 1] = player1Score + "";
				}
				// Player 2
				else if (winner == 2) {
					highScore[0][highScore[0].length - 1] = player2Name;
					highScore[1][highScore[0].length - 1] = player2Score + "";
				}
			}

			// Writing to the file
			output = new PrintWriter(new FileWriter("HighScores"));
			output.println(highScore[0].length); // Printing out the length of the array
			for (int i = 0; i < highScore[0].length; i++) {
				// Writing all the names to the file
				output.println(highScore[0][i] + " " + highScore[1][i]);
			}
			// Closing the scanner
			output.close();
		} catch (IOException e) {
		}
	}

	/*
	  movePlayer method
	  This method handles all the calculations for moving. It takes in 4 parameters, the number of moves,
	  player number, the column and the row. The first thing it does is declare constants for the moves, column
	  and row. It then updates the score depending on the player, before moving on to the actual movement. If first
	  checks if the player is in a position to win the game, and reach the final square on the board. If so, it animates the
	  player moving there, before throwing an exception which will be caught in display. If not player has won the game, it moves on
	  to the next part of movement. The program checks if the current row is even or odd, and as such it animates the players moving 
	  left to right on odd rows, and right to left on even rows. If the player is not moving up the row, it moves the player in the 
	  desired direction. If the player is moving up, it first moves the player to the end of the row, moves it up one row, then 
	  calculates how many squares are left to move, before moving it. At the end, it uses the constants and the updated row, and column 
	  values to update the array. Once the array is updated, it moves on to checking if the player landed on a snake or ladder.
	  If so, using the slope of the equation represented by the ladder/snakes it animates moving up a ladder or down a snake and adjusting 
	  the score depending on whether the user hits a ladder or a snake.
	  
	  Local Variable Dictionary:
	
		     Name:   |  Type:  |  Description:
		-------------|---------|----------------------------------------------------------------------------
		    moves    |   int   |        Parameter passed the roll on the dice
		    player   |   int   |        Passed parameter on which player is being moved
		     col     |   int   |        Column the player is on, which gets updated based on the user's roll
		     row     |   int   |        Row the player is on, which gets updated based on the user's roll
		    MOVES    |final int|        Parameter passed the roll on the dice, which never gets updated
		     COL     |final int|        Column the player is on, which never gets updated
		     ROW     |final int|        Row the player is on, which never gets updated
	  
	*/
	
	private void movePlayer(int moves, int player, int col, int row) {
		// Local variable declarations
		final int MOVES = moves;
		final int COL = col;
		final int ROW = row;

		// Updating the score
		if (player == 1) {
			player1Score++;
		} else if (player == 2) {
			player2Score++;
		}

		// Checks if the player has won the game
		if (row == 0 && col - moves <= 0) {
			// Animations
			for (int i = (col * 40 + 131); i > 131; i--) {
				// Moving player 1
				if (player == 1) {
					xPlayer1Pos--;
				}
				// Moving player 2
				else {
					xPlayer2Pos--;
				}

				// Drawing background
				background();

				// Redrawing the dice
				diceDisplay(MOVES);

				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			throw new ArrayIndexOutOfBoundsException();
		}

		// This is the section for moving from left to right, on the odd rows
		if (row % 2 != 0) {

			// Moving the player when staying on the same row
			if (col + moves < 10) {
				// Updating the column
				col += moves;
				for (int i = 0; i <= (moves * 40); i++) {
					// Animations
					if (player == 1) {
						// Moving player 1
						xPlayer1Pos++;
					} else {
						// Moving player 2
						xPlayer2Pos++;
					}
					// Drawing background
					background();

					// Redrawing the dice
					diceDisplay(MOVES);

					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
			}

			// Moving when you move up a row going left to right
			else {
				for (int i = (COL * 40) + 131; i <= 491; i++) {
					if (player == 1) {
						// Moving the player
						xPlayer1Pos++;
					} else {
						xPlayer2Pos++;
					}
					// Drawing background
					background();
					// Redrawing the dice
					diceDisplay(MOVES);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
				// Calculations for how many spaces are left to move
				moves -= (10 - col);
				col = 9 - moves;
				row--;

				for (int i = 0; i < 40; i++) {
					// Animating the players moving up
					if (player == 1) {
						// Player 1
						yPlayer1Pos--;
					} else {
						yPlayer2Pos--;
					}
					// Drawing background
					background();
					// Redrawing the dice
					diceDisplay(MOVES);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}

				for (int i = 491; i >= (col * 40) + 131; i--) {
					// Moving player 1
					if (player == 1) {
						xPlayer1Pos = i;
					}
					// Moving player 2
					else {
						xPlayer2Pos = i;
					}
					// Drawing background
					background();

					// Redrawing the dice
					diceDisplay(MOVES);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
			}
		}
		// Movement from right to left, on even rows
		else {
			// Moving the player when staying on the same row
			if (col - moves >= 0) {
				// Updating the column
				col -= moves;
				for (int i = (COL * 40) + 131; i >= (col * 40) + 131; i--) {
					// Animations
					if (player == 1) {
						// Moving player 1
						xPlayer1Pos--;
					} else {
						// Moving player 2
						xPlayer2Pos--;
					}
					// Drawing background
					background();
					// Redrawing the dice
					diceDisplay(MOVES);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}

			} else {

				for (int i = COL * 40; i > 0; i--) {
					// Animating the player
					if (player == 1) {
						// Player 1
						xPlayer1Pos--;
					} else {
						xPlayer2Pos--;
					}
					// Drawing background
					background();
					// Redrawing the dice
					diceDisplay(MOVES);

					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
				// Calculating the amount of moves left
				moves -= (col + 1);
				col = moves;
				row--;

				for (int i = 0; i <= 40; i++) {
					// Animations
					if (player == 1) {
						// Player 1
						yPlayer1Pos--;
					} else {
						// Player 2
						yPlayer2Pos--;
					}
					// Drawing background
					background();
					// Redrawing the dice
					diceDisplay(MOVES);
					// Delay
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
				// Moving the player to the left for the remaining moves
				for (int i = 131; i <= col * 40 + 131; i++) {
					// Animations
					if (player == 1) {
						// Player 1
						xPlayer1Pos++;
					} else {
						// Player 2
						xPlayer2Pos++;
					}
					// Drawing background
					background();
					// Redrawing the dice
					diceDisplay(MOVES);
					// Delay
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
			}
		}
		// Updating the array
		if (player == 1) {
			// Player 1
			boardPlayer1[COL][ROW] = 0;
			boardPlayer1[col][row] = 1;
		} else {
			// Player 2
			boardPlayer2[COL][ROW] = 0;
			boardPlayer2[col][row] = 2;
		}
		// Ladders
		// 3 -> 20
		if (col == 2 && row == 9) {
			for (int i = 80; i >= 0; i--) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos--;
					yPlayer1Pos = (int) Math.round((xPlayer1Pos * 9.0) / 16.0) + 310;
					boardPlayer1[0][8] = 1;
					boardPlayer1[2][9] = 0;
				} else {
					// Player 2
					xPlayer2Pos--;
					yPlayer2Pos = (int) Math.round((xPlayer2Pos * 9.0) / 16.0) + 310;
					boardPlayer2[0][8] = 2;
					boardPlayer2[2][9] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);

				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score--;
			} else if (player == 2) {
				player2Score--;
			}
		}

		// 17 -> 74
		if (col == 3 && row == 8) {
			for (int i = 0; i <= 120; i++) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos++;
					yPlayer1Pos -= 2;
					boardPlayer1[6][2] = 1;
					boardPlayer1[3][8] = 0;
				} else {
					// Player 2
					xPlayer2Pos++;
					yPlayer2Pos -= 2;
					boardPlayer2[6][2] = 2;
					boardPlayer2[3][8] = 0;
				}

				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(20);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score--;
			} else if (player == 2) {
				player2Score--;
			}
		}

		// 60 -> 84
		if (col == 0 && row == 4) {
			for (int i = 0; i <= 120; i++) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos++;
					yPlayer1Pos--;
					boardPlayer1[3][1] = 1;
					boardPlayer1[0][4] = 0;
				} else {
					// Player 2
					xPlayer2Pos++;
					yPlayer2Pos--;
					boardPlayer2[3][1] = 2;
					boardPlayer2[0][4] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score--;
			} else if (player == 2) {
				player2Score--;
			}
		}

		// 32 -> 52
		if (col == 8 && row == 6) {
			for (int i = 80; i >= 0; i--) {
				// Animating the player moving
				if (player == 1) {
					yPlayer1Pos--;
					boardPlayer1[8][4] = 1;
					boardPlayer1[8][6] = 0;
				} else {
					yPlayer2Pos--;
					boardPlayer2[8][4] = 2;
					boardPlayer2[8][6] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score--;
			} else if (player == 2) {
				player2Score--;
			}
		}

		// 90 -> 93
		if (col == 9 && row == 1) {
			for (int i = 80; i > 0; i--) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos--;
					yPlayer1Pos = (int) Math.round((xPlayer1Pos * 9.0) / 16.0) - 165;
					boardPlayer1[7][0] = 1;
					boardPlayer1[9][1] = 0;
				} else {
					// Player 2
					xPlayer2Pos--;
					yPlayer2Pos = (int) Math.round((xPlayer2Pos * 9.0) / 16.0) - 165;
					boardPlayer2[7][0] = 2;
					boardPlayer2[9][1] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score--;
			} else if (player == 2) {
				player2Score--;
			}
		}

		// 44 -> 63
		if (col == 3 && row == 5) {
			for (int i = 40; i > 0; i--) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos--;
					yPlayer1Pos -= 2;
					boardPlayer1[2][3] = 1;
					boardPlayer1[3][5] = 0;
				} else {
					// Player 2
					xPlayer2Pos--;
					yPlayer2Pos -= 2;
					boardPlayer2[2][3] = 2;
					boardPlayer2[3][5] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score--;
			} else if (player == 2) {
				player2Score--;
			}
		}

		// 13 -> 34
		if (col == 7 && row == 8) {
			for (int i = 40; i > 0; i--) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos--;
					yPlayer1Pos -= 2;
					boardPlayer1[6][6] = 1;
					boardPlayer1[7][8] = 0;
				} else {
					// Player 2
					xPlayer2Pos--;
					yPlayer2Pos -= 2;
					boardPlayer2[6][6] = 2;
					boardPlayer2[7][8] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score--;
			} else if (player == 2) {
				player2Score--;
			}
		}

		// Snakes
		// 30 -> 8
		if (col == 9 && row == 7) {
			for (int i = 80; i > 0; i--) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos--;
					yPlayer1Pos++;
					boardPlayer1[7][9] = 1;
					boardPlayer1[9][7] = 0;
				} else {
					// Player 2
					xPlayer2Pos--;
					yPlayer2Pos++;
					boardPlayer2[7][9] = 2;
					boardPlayer2[9][7] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score++;
			} else if (player == 2) {
				player2Score++;
			}
		}

		// 42 -> 21
		if (col == 1 && row == 5) {
			for (int i = 40; i > 0; i--) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos -= 1;
					yPlayer1Pos += 2;
					boardPlayer1[0][7] = 1;
					boardPlayer1[1][5] = 0;
				} else {
					// Player 2
					xPlayer2Pos -= 1;
					yPlayer2Pos += 2;
					boardPlayer2[0][7] = 2;
					boardPlayer2[1][5] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score++;
			} else if (player == 2) {
				player2Score++;
			}
		}

		// 86 -> 65
		if (col == 5 && row == 1) {
			for (int i = 40; i > 0; i--) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos -= 1;
					yPlayer1Pos += 2;
					boardPlayer1[4][3] = 1;
					boardPlayer1[5][1] = 0;
				} else {
					// Player 2
					xPlayer2Pos -= 1;
					yPlayer2Pos += 2;
					boardPlayer2[4][3] = 2;
					boardPlayer2[5][1] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score++;
			} else if (player == 2) {
				player2Score++;
			}
		}

		// 94 -> 73
		if (col == 6 && row == 0) {
			for (int i = 0; i < 40; i++) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos += 1;
					yPlayer1Pos += 2;
					boardPlayer1[7][2] = 1;
					boardPlayer1[6][0] = 0;
				} else {
					// Player 2
					xPlayer2Pos++;
					yPlayer2Pos += 2;
					boardPlayer2[7][2] = 2;
					boardPlayer2[6][0] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score++;
			} else if (player == 2) {
				player2Score++;
			}
		}
		// 99 -> 23
		if (col == 1 && row == 0) {
			for (int i = 0; i < 40; i++) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos++;
					yPlayer1Pos += 7;
					boardPlayer1[2][7] = 1;
					boardPlayer1[1][0] = 0;
				} else {
					// Player 2
					xPlayer2Pos++;
					yPlayer2Pos += 7;
					boardPlayer2[2][7] = 2;
					boardPlayer2[1][0] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(20);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score++;
			} else if (player == 2) {
				player2Score++;
			}
		}

		// 68 -> 51
		if (col == 7 && row == 3) {
			for (int i = 0; i < 80; i += 2) {
				// Animating the player moving
				if (player == 1) {
					// Player 1
					xPlayer1Pos += 2;
					yPlayer1Pos++;
					boardPlayer1[9][4] = 1;
					boardPlayer1[7][3] = 0;
				} else {
					// Player 2
					xPlayer2Pos += 2;
					yPlayer2Pos++;
					boardPlayer2[9][4] = 2;
					boardPlayer2[7][3] = 0;
				}
				// Drawing background
				background();
				// Redrawing the dice
				diceDisplay(MOVES);
				// Delay
				try {
					Thread.sleep(20);
				} catch (Exception e) {
				}
			}
			// Updating the score
			if (player == 1) {
				player1Score++;
			} else if (player == 2) {
				player2Score++;
			}
		}
	}
	
	/*
		diceRoll int
		Generates a random number between 1 -> 6
	*/
	
	private int diceRoll() {
		return (int) (Math.random() * 6) + 1;
	}
	
	/*
		diceDisplay method
		Given a number between 1 and 6 it will draw the corresponding dice face to the number
	*/
	
	private void diceDisplay(int diceFace) {
		// Dice
		c.setColor(Color.white);
		c.fillRoundRect(545, 350, 75, 75, 5, 5);
		c.setColor(Color.black);
		c.drawRoundRect(545, 350, 75, 75, 5, 5);

		// Drawing the dots on the dice face
		if (diceFace == 1 || diceFace == 3 || diceFace == 5) {
			c.fillOval(576, 381, 12, 12);
		}

		if (diceFace == 2 || diceFace == 3) {
			c.fillOval(595, 363, 12, 12);
			c.fillOval(557, 401, 12, 12);
		}

		if (diceFace == 4 || diceFace == 5 || diceFace == 6) {
			c.fillOval(557, 363, 12, 12);
			c.fillOval(595, 363, 12, 12);
			c.fillOval(557, 401, 12, 12);
			c.fillOval(595, 401, 12, 12);
		}

		if (diceFace == 6) {
			c.fillOval(557, 382, 12, 12);
			c.fillOval(595, 382, 12, 12);
		}
	}
	
	/*
		background method
		This method draws the board, the numbers, snakes and ladders, the scoreboard, and the players
	*/
	
	private void background() {
		// Background
		c.setColor(new Color(99, 172, 229));
		c.fillRect(0, 0, 640, 500);

		// title
		c.setColor(new Color(42, 77, 105));
		c.setFont(new Font("Times New Roman", 3, 32));
		c.drawString("Snakes and Ladders", 185, 30);
		
		// Drawing the board
		for (int y = 50; y < 450; y += 40) {
			for (int x = 120; x < 520; x += 40) {
				c.setColor(Color.black);
				c.drawRect(x - 1, y - 1, 41, 41);
				c.setColor(new Color(173, 203, 227));
				c.fillRect(x, y, 40, 40);
			}
		}

		// Drawing the numbers
		c.setFont(new Font("Times New Roman", 0, 16));
		c.setColor(Color.black);
		for (int x = 0; x < 10; x++) {
			c.drawString((x + 1) + "", (x * 40) + 121, 423);
			c.drawString((x + 21) + "", (x * 40) + 121, 343);
			c.drawString((x + 41) + "", (x * 40) + 121, 263);
			c.drawString((x + 61) + "", (x * 40) + 121, 183);
			c.drawString((x + 81) + "", (x * 40) + 121, 103);
		}
		for (int x = 10; x > 0; x--) {
			c.drawString((x + 10) + "", (10 - x) * 40 + 121, 383);
			c.drawString((x + 30) + "", (10 - x) * 40 + 121, 303);
			c.drawString((x + 50) + "", (10 - x) * 40 + 121, 223);
			c.drawString((x + 70) + "", (10 - x) * 40 + 121, 143);
			c.drawString((x + 90) + "", (10 - x) * 40 + 121, 63);
		}
		
		// Score Board
		// Table Body
		c.setColor(new Color(173, 203, 227));
		c.fillRect(10, 190, 100, 80);

		// Table Heading
		c.setColor(new Color(42, 77, 105));
		c.fillRect(10, 190, 100, 30);

		// Scores
		// Player 1
		c.setFont(new Font("Courier", 1, 28));
		if (player1Score <= 9)
			c.drawString(player1Score + "", 27, 250);
		else if (player1Score <= 99)
			c.drawString(player1Score + "", 20, 250);
		else
			c.drawString(player1Score + "", 10, 250);
		// Player 2
		if (player1Score <= 9)
			c.drawString(player2Score + "", 77, 250);
		else if (player1Score <= 99)
			c.drawString(player2Score + "", 70, 250);
		else
			c.drawString(player2Score + "", 60, 250);

		// Table outline
		c.setColor(new Color(0, 0, 0));
		c.drawRect(10, 190, 50, 80);
		c.drawRect(60, 190, 50, 80);
		c.drawRect(10, 190, 100, 30);

		// Player 1 label
		c.setColor(player1Color);
		c.fillOval(27, 197, 15, 15);

		// Player 2 label
		c.setColor(player2Color);
		c.fillOval(77, 197, 15, 15);

		// Snakes:

		// 99 -> 23
		// Body
		for (int i = 0; i <= 42; i++) {
			c.setColor(new Color(0, 255, 0));
			c.fillOval(165 + i, 75 + (i * 6), 25, 25);
		}
		// Eyes
		c.setColor(new Color(255, 255, 255));
		c.fillOval(168, 85, 8, 8);
		c.fillOval(178, 82, 8, 8);
		// Pupil
		c.setColor(new Color(0, 0, 0));
		c.fillOval(170, 87, 4, 4);
		c.fillOval(181, 84, 4, 4);
		// Tongue
		c.setColor(new Color(255, 0, 0));
		c.drawLine(172, 68, 173, 76);

		// 30 -> 8
		// Body
		for (int i = 0; i <= 62; i++) {
			c.setColor(new Color(0, 0, 255));
			c.fillOval(485 - i, 350 + i, 20, 20);
		}
		// Eyes
		c.setColor(new Color(255, 255, 255));
		c.fillOval(490, 352, 6, 6);
		c.fillOval(496, 359, 6, 6);
		// Pupil
		c.setColor(new Color(0, 0, 0));
		c.fillOval(491, 353, 4, 4);
		c.fillOval(497, 360, 4, 4);
		// Tongue
		c.setColor(new Color(255, 0, 0));
		c.drawLine(502, 356, 508, 348);

		// 86 -> 65
		// Body
		for (int i = 0; i <= 35; i++) {
			c.setColor(new Color(255, 0, 0));
			c.fillOval(330 - i, 115 + i * 2, 20, 20);
		}
		// Eyes
		c.setColor(new Color(255, 255, 255));
		c.fillOval(333, 119, 6, 6);
		c.fillOval(341, 123, 6, 6);
		// Pupil
		c.setColor(new Color(0, 0, 0));
		c.fillOval(334, 120, 4, 4);
		c.fillOval(342, 124, 4, 4);
		// Tongue
		c.setColor(new Color(255, 0, 0));
		c.drawLine(346, 107, 343, 120);

		// 94 -> 73
		// Body
		for (int i = 0; i <= 25; i++) {
			c.setColor(new Color(255, 0, 255));
			c.fillOval(380 + i, 75 + i * 2, 20, 20);
		}
		// Eyes
		c.setColor(new Color(255, 255, 255));
		c.fillOval(382, 81, 6, 6);
		c.fillOval(389, 78, 6, 6);
		// Pupil
		c.setColor(new Color(0, 0, 0));
		c.fillOval(383, 82, 4, 4);
		c.fillOval(390, 79, 4, 4);
		// Tongue
		c.setColor(new Color(255, 0, 0));
		c.drawLine(379, 70, 385, 78);

		// 42 -> 21
		for (int i = 0; i <= 35; i++) {
			c.setColor(new Color(255, 227, 13));
			c.fillOval(170 - i, 275 + i * 2, 20, 20);
		}
		// Eyes
		c.setColor(new Color(255, 255, 255));
		c.fillOval(173, 279, 6, 6);
		c.fillOval(181, 283, 6, 6);
		// Pupil
		c.setColor(new Color(0, 0, 0));
		c.fillOval(174, 280, 4, 4);
		c.fillOval(182, 284, 4, 4);
		// Tongue
		c.setColor(new Color(255, 0, 0));
		c.drawLine(186, 267, 183, 280);

		// 68 -> 51
		for (int i = 0; i <= 35; i++) {
			c.setColor(new Color(0, 255, 255));
			c.fillOval(415 + i * 2, 185 + i, 20, 20);
		}
		// Eyes
		c.setColor(new Color(255, 255, 255));
		c.fillOval(418, 194, 6, 6);
		c.fillOval(422, 187, 6, 6);
		// Pupil
		c.setColor(new Color(0, 0, 0));
		c.fillOval(419, 195, 4, 4);
		c.fillOval(423, 188, 4, 4);
		// Tongue
		c.setColor(new Color(255, 0, 0));
		c.drawLine(407, 185, 416, 190);

		// Ladders
		// 3 -> 20
		for (int x = 0; x <= 3; x++) {
			c.setColor(new Color(165, 121, 73));
			c.drawLine(205, 440 - x, 125, 395 - x);
			c.drawLine(230, 435 - x, 150, 390 - x);
			// Rungs
			c.drawLine(145, 405 - x, 162, 397 - x);
			c.drawLine(165, 420 - x, 190, 410 - x);
			c.drawLine(195, 433 - x, 210, 425 - x);

		}

		// 7 -> 74
		for (int x = 0; x <= 6; x++) {
			c.drawLine(245, 400 - x, 365, 160 - x);
			c.drawLine(275, 400 - x, 395, 160 - x);
		}
		// Rungs
		for (int y = 0; y <= 212; y += 19) {
			for (int i = 0; i <= 2; i++) {
				c.drawLine(250 + y / 2, 385 - y - i, 280 + y / 2, 385 - y - i);
			}
		}

		// 60 -> 84
		for (int x = 0; x <= 4; x++) {
			c.drawLine(125, 240 - x, 245, 115 - x);
			c.drawLine(155, 240 - x, 275, 115 - x);
		}
		// Rungs
		for (int y = 0; y < 120; y += 20) {
			for (int i = 0; i <= 2; i++) {
				c.drawLine(130 + y, 230 - y - i, 160 + y, 230 - y - i);
			}
		}

		// 32 -> 52
		for (int x = 0; x <= 2; x++) {
			c.drawLine(445 - x, 320, 445 - x, 235);
			c.drawLine(475 - x, 320, 475 - x, 235);
		}

		// Rungs
		for (int y = 0; y < 80; y += 20) {
			for (int i = 0; i <= 2; i++) {
				c.drawLine(445, 310 - y - i, 475, 310 - y - i);
			}
		}

		// 90 -> 93
		for (int x = 0; x <= 3; x++) {
			// 3 -> 20
			c.drawLine(485, 120 - x, 405, 75 - x);
			c.drawLine(510, 115 - x, 430, 70 - x);
			// Rungs
			c.drawLine(425, 85 - x, 442, 77 - x);
			c.drawLine(445, 100 - x, 470, 90 - x);
			c.drawLine(475, 113 - x, 490, 105 - x);
		}

		// 44 -> 63
		for (int x = 0; x <= 3; x++) {
			c.drawLine(205 - x, 200, 245 - x, 280);
			c.drawLine(235 - x, 200, 275 - x, 280);
		}
		// Rungs
		for (int y = 0; y < 4; y += 1) {
			for (int i = 0; i <= 2; i++) {
				c.drawLine(235 - (y * 7), 265 - (16 * y) - i, 265 - (y * 7), 265 - (16 * y) - i);
			}
		}

		// 13 -> 34
		for (int x = 0; x <= 3; x++) {
			c.drawLine(365 - x, 320, 405 - x, 400);
			c.drawLine(395 - x, 320, 435 - x, 400);
		}
		// Rungs
		for (int y = 0; y < 4; y += 1) {
			for (int i = 0; i <= 2; i++) {
				c.drawLine(395 - (y * 7), 385 - (16 * y) - i, 425 - (y * 7), 385 - (16 * y) - i);
			}
		}

		// Drawing the players
		// Player 1
		c.setColor(new Color(0,0,0));
		c.fillOval(xPlayer1Pos - 1, yPlayer1Pos - 1, 17, 17);
		c.setColor(player1Color);
		c.fillOval(xPlayer1Pos, yPlayer1Pos, 15, 15);

		// Player 2
		c.setColor(new Color(0,0,0));
		c.fillOval(xPlayer2Pos - 1, yPlayer2Pos - 1, 17, 17);
		c.setColor(player2Color);
		c.fillOval(xPlayer2Pos, yPlayer2Pos, 15, 15);
	}
	
	/*
		highScore method
		This method reads from the HighScore file and outputs the top 10 highest scores of the game. First thing it does is get the size of the file.
		Before anyone plays the game, I have the size set as -1, to tell the program that the file is empty, and there are no scores to display. In 
		that case it throws an exception, which later gets caught and the user is told to return to the main menu. If the file has scores in it, It will read
		up to the first ten, storing those values in the highScores array, before outputting them in a neatly formatted way.
		
		Local Variable Dictionary:
	
		     Name:   |      Type:     |  Description:
		-------------|----------------|---------------------------------------------------------------------------------------
		    input    |   String[][]   |         2-d Array that scores the player's score and name
		     temp    |    String[]    |         1-d Array that takes in input by the line, then tokenizing it by " "
		    output   |     String     |         String that joins the rank, name and score of the player
		      in     | BufferedReader |         Used to take in input from the file
	      overWrite  |   PrintWriter  |             Used to overwrite the high scores file in the case the user wants to clear it
		tempIn   |      char      |             Used to check if the user wants to clear the file
 
	*/
	
	public void highScore() {
		// Local Variables
		char tempIn;
		String[][] highScores = null;
		BufferedReader in;

		// Title and table
		title();
		c.setColor(new Color(42, 77, 105));
		c.setFont(new Font("Times New Roman", 3, 48));
		c.drawString("Hall Of Fame", 200, 120);

		// Reading from the "HighScores" file
		try {
			// Input from the BufferedReader
			in = new BufferedReader(new FileReader("HighScores"));
			int size = Integer.parseInt(in.readLine());
			//c.print(size);
			// Error trap for an empty file
			if (size == -1) {
				in.close();
				throw new NullPointerException();
			}

			// High scores are only mean to show the top 10 scores, so it will only store a
			// max of 10 entries
			if (size < 10) {
				highScores = new String[2][size];
			}
			else {
				highScores = new String[2][10];
			}
			// Inputting the score
			for (int i = 0; i < highScores[0].length; i++) {
				String[] temp = in.readLine().split(" ");
				highScores[0][i] = temp[0]; // Player name
				highScores[1][i] = temp[1]; // Player score
			}
			in.close();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Sorry, but there are no scores to display! Please play a game.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IOException e) {
		}

		// Outputting the names
		c.setFont(new Font("Courier", 2, 22));
		// Looping through the high score array
		for (int i = 0; i < highScores[0].length; i++) {
			String output = "";
			if (i < 9) {
				// Generating an output that combines the player name, rank and score
				output = (i + 1) + ". " + highScores[0][i];
				for (int x = output.length(); x <= 28; x++) {
					output += " ";
				}
			}
			// Aligning the 10 with rest of the numbers
			else {
				output = 10 + ". " + highScores[0][i];
				for (int x = output.length(); x <= 29; x++) {
					output += " ";
				}
			}
			// Adding the scores to the output
			output += highScores[1][i];

			// Outputting the table
			if (i == 9)
				c.drawString(output, 90, 150 + (i * 30));
			else
				c.drawString(output, 103, 150 + (i * 30));
		}
		c.setColor(new Color(42, 77, 105));
		c.setFont(new Font("Times New Roman", 3, 24));
		c.drawString("Press 'c' to clear the high scores", 158, 465);
		c.drawString("Press any key to return to the menu...", 137, 490);
		// Getting choice if the user wants to overwrite the save file
		tempIn = c.getChar();
		
		// If the user chooses to clear the save file
		if (tempIn == 'c' || tempIn =='C') {
			try {
				// Writing over the file with a -1, indicating it is a blank file
				PrintWriter overWrite = new PrintWriter(new FileWriter("HighScores"));
				overWrite.println("-1");
				overWrite.close();
			} catch (IOException e) {
			}
		}
	}
	
	/*
		instructions method
		This method writes the instructions for the game, along with a couple of example animations on how ladders and snakes work, before returning the the main menu 
	*/
	
	public void instructions() {
		// Title
		title();
		c.setColor(new Color(42, 77, 105));
		c.setFont(new Font("Times New Roman", 3, 48));
		c.drawString("Instructions", 200, 120);

		// Instructions
		c.setFont(new Font("Times New Roman", 0, 22));
		c.drawString("Snakes and ladders is one of the most timeless, simple yet fun", 45, 150);
		c.drawString("family board games. It started off as an ancient Indian Board Game", 18, 170);
		c.drawString("but took off in America during the mid 1940s. The rules of the", 45, 190);
		c.drawString("game are straight forward. Each player takes turn rolling a dice.", 35, 210);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}

		// Animation display a ladder
		for (int i = 160; i > 115; i--) {
			// Redrawing background
			title();
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 3, 48));
			c.drawString("Instructions", 200, 120);

			// Instructions
			c.setFont(new Font("Times New Roman", 0, 22));
			c.drawString("Snakes and ladders is one of the most timeless, simple yet fun", 45, 150);
			c.drawString("family board games. It started off as an ancient Indian Board Game", 18, 170);
			c.drawString("but took off in America during the mid 1940s. The rules of the", 45, 190);
			c.drawString("game are straight forward. Each player takes turn rolling a dice.", 35, 210);

			// Ladder
			c.setColor(new Color(165, 121, 73));
			for (int x = 0; x <= 3; x++) {
				c.drawLine(115 - x, 245, 155 - x, 325);
				c.drawLine(145 - x, 245, 185 - x, 325);
			}
			// Rungs
			for (int y = 0; y < 4; y += 1) {
				for (int x = 0; x <= 2; x++) {
					c.drawLine(145 - (y * 7), 310 - (16 * y) - x, 175 - (y * 7), 310 - (16 * y) - x);
				}
			}

			// Message
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 0, 18));
			c.drawString("You can go up ladders...", 85, 345);

			// Player example
			c.setColor(new Color(0, 0, 255));
			c.fillOval(i, i * 2, 15, 15);
			try {
				Thread.sleep(20);
			} catch (Exception e) {
			}
		}

		for (int i = 115; i <= 525; i++) {
			// Redrawing background
			title();
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 3, 48));
			c.drawString("Instructions", 200, 120);

			// Instructions
			c.setFont(new Font("Times New Roman", 0, 22));
			c.drawString("Snakes and ladders is one of the most timeless, simple yet fun", 45, 150);
			c.drawString("family board games. It started off as an ancient Indian Board Game", 18, 170);
			c.drawString("but took off in America during the mid 1940s. The rules of the", 45, 190);
			c.drawString("game are straight forward. Each player takes turn rolling a dice.", 35, 210);

			// Ladder
			c.setColor(new Color(165, 121, 73));
			for (int x = 0; x <= 3; x++) {
				c.drawLine(115 - x, 245, 155 - x, 325);
				c.drawLine(145 - x, 245, 185 - x, 325);
			}
			// Rungs
			for (int y = 0; y < 4; y += 1) {
				for (int x = 0; x <= 2; x++) {
					c.drawLine(145 - (y * 7), 310 - (16 * y) - x, 175 - (y * 7), 310 - (16 * y) - x);
				}
			}
			// Message 1
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 0, 18));
			c.drawString("You can go up ladders...", 85, 345);

			// Message 2
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 0, 18));
			c.drawString("Or...", 315, 230);

			// Player example
			c.setColor(new Color(0, 0, 255));
			c.fillOval(i, 230, 15, 15);

			// Delay
			try {
				Thread.sleep(5);
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < 40; i++) {
			// Redrawing background
			title();
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 3, 48));
			c.drawString("Instructions", 200, 120);

			// Instructions
			c.setFont(new Font("Times New Roman", 0, 22));
			c.drawString("Snakes and ladders is one of the most timeless, simple yet fun", 45, 150);
			c.drawString("family board games. It started off as an ancient Indian Board Game", 18, 170);
			c.drawString("but took off in America during the mid 1940s. The rules of the", 45, 190);
			c.drawString("game are straight forward. Each player takes turn rolling a dice.", 35, 210);

			// Ladder
			c.setColor(new Color(165, 121, 73));
			for (int x = 0; x <= 3; x++) {
				c.drawLine(115 - x, 245, 155 - x, 325);
				c.drawLine(145 - x, 245, 185 - x, 325);
			}
			// Rungs
			for (int y = 0; y < 4; y += 1) {
				for (int x = 0; x <= 2; x++) {
					c.drawLine(145 - (y * 7), 310 - (16 * y) - x, 175 - (y * 7), 310 - (16 * y) - x);
				}
			}

			// Message 1
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 0, 18));
			c.drawString("You can go up ladders...", 85, 345);

			// Message 2
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 0, 18));
			c.drawString("Or...", 315, 230);

			// Message 3
			c.setColor(new Color(42, 77, 105));
			c.setFont(new Font("Times New Roman", 0, 18));
			c.drawString("Down a Snake!", 450, 360);

			// Example Snake
			// Body
			for (int x = 0; x <= 35; x++) {
				c.setColor(new Color(255, 0, 0));
				c.fillOval(525 - x, 245 + x * 2, 20, 20);
			}
			// Eyes
			c.setColor(new Color(255, 255, 255));
			c.fillOval(528, 249, 6, 6);
			c.fillOval(536, 253, 6, 6);
			// Pupil
			c.setColor(new Color(0, 0, 0));
			c.fillOval(529, 250, 4, 4);
			c.fillOval(537, 254, 4, 4);
			// Tongue
			c.setColor(new Color(255, 0, 0));
			c.drawLine(541, 237, 538, 250);

			// Player example
			c.setColor(new Color(0, 0, 255));
			c.fillOval(510 - i, 245 + (i * 2), 15, 15);
			// Delay
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}

		// More Instructions
		c.setColor(new Color(42, 77, 105));
		c.setFont(new Font("Times New Roman", 0, 22));
		c.drawString("The first to the top of the board, wins the game!", 100, 400);
		c.drawString("For each ladder you go up you gain a point, ", 120, 420);
		c.drawString("and each snake you go down you lose a point.", 110, 440);

		pauseProgram();
	}
	
	/*
		splashScreen method
		This method creates three iterations of the splash screen class, starts up the 3 threads and then
		waits until the user presses any key to proceed with the game using getChar. Once the user hits a button
		it kills those threads, and continues on with the main game
		
		Local Variable Dictionary:
	
		     Name:   |    Type:    |  Description:
		-------------|-------------|------------------------------------------------
		      s1     | SplashScreen|    First iteration of the SplashScreen class
		      s2     | SplashScreen|    Second iteration of the SplashScreen class
		      s3     | SplashScreen|    Third iteration of the SplashScreen class

	 */
	
	public void splashScreen() {
		// Declares and initializes the 3 threads
		SplashScreen s1 = new SplashScreen(c, new Color(255,0,0), 110);
		SplashScreen s2 = new SplashScreen(c, new Color(0,255,0), 235);
		SplashScreen s3 = new SplashScreen(c, new Color(0,0,255), 340);
		// Starts up the 3 threads
		s1.start();
		s2.start();
		s3.start();
		// Once the user is ready to start the program it kills the threads, and goes to the main menu
		c.getChar();
		s1.stop();
		s2.stop();
		s3.stop();
		c.clear();
	}
	
	/*
		goodBye method
		This method just displays an exit greeting and then pauses the program, waiting for user input before exiting the program and closing the console.
	*/
	
	public void goodBye() {
		// Title
		title();
		
		// Message
		c.setColor(new Color(42, 77, 105));
		c.setFont(new Font("Times New Roman", 3, 24));
		c.drawString("Thank you for using the program", 150, 100);
		c.drawString("Made by Ishan Sethi.", 217, 150);
		
		// Waiting until the user is ready to escape
		c.drawString("Press any key to exit the program", 145, 200);
		c.getChar();  

		// Closing the console
		c.close();
	}
	
	/*
		Main method
		This method acts like the main menu and calls all the other methods. The first thing it does is make a new object of
		the SnakesAndLadders class. It then calls the splashScreen method which displays a small animation. We then have an infinite
		while loop. The user selects an option from the mainMenu, and then we reference the menuChoice String. If they selected option
		1 it goes to askData then to display and back to mainMenu. If they selected option 2 it goes to the instructions.
		If they selected option 3, it breaks the while loop and goes to goodBye ending the program.
	*/

	public static void main(String[] args) {
		// Initialising the SnakesAndLadderrs class
		SnakesAndLadders s = new SnakesAndLadders();
		// Splash Screen
		 s.splashScreen();
		// Menu Loop
		while (true) {
			s.mainMenu();
			if (s.menuChoice.equals("1")) {
				// Main Game
				s.askData();
				s.display();
			} else if (s.menuChoice.equals("2")) {
				// Instructions
				s.instructions();
			} else if (s.menuChoice.equals("3")) {
				// High scores
				s.highScore();
			} else {
				// Exit
				break;
			}
		}
		// Good bye
		s.goodBye();
	}
}

