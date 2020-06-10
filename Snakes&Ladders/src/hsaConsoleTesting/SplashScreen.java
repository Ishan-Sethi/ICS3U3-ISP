package hsaConsoleTesting;

import hsa.Console;
import java.awt.*;
/*
	Ishan Sethi
	Snakes and Ladders
	Ms. Krasteva
	Tuesday, January 14th, 2019
	This Splash Screen class deals with that splash scrren animation of sin wave represented as a snake. 
	Instead of it all being part of one class, I made it into a seperate class so I can also thread the animation,
	and have multiple snakes appearing on the screen at the same time.
	
	Global Variable Dictionary:
	
	     Name:   |  Type:  |  Description:
	-------------|---------|------------------------------------------------------------------------------------------------------------------
	      c      | Console | 	A reference variable of the hsa Console class which displays all the program screens
	 player1Color|  Color  |	Hold's the color of the snake
	     yPos    |   int   |	y position of where you want the snake to appear
*/

public class SplashScreen extends Thread {
	// Global Variables
	Console c;
	Color snakeColor;
	int yPos;

	// Class Constructor, used to assign values to the global variables
	public SplashScreen(Console con, Color sC, int y) {
		c = con;
		snakeColor = sC;
		yPos = y;
	}

	/*
	  	drawSnake  method
	  	This method animates the opening animation off a snake slithering around. It basically is the animation for a moving sine wave,
	  	I use an array to generate all he y value Heights for the sine wave, for a corresponding x value and slowly move that start angle up, 
	  	giving it the illusion of forward movement. 
	 	
	 	Local Variable Dictionary:
	
		     Name:   |  Type:  |  Description:
		-------------|---------|----------------------------------------------------------------------
		   yHeight   | double[]|	Array that holds all the heights for the point in the sine wave
		  startAngle | double  |	The starting angle of the sine wave, that slowly increases
		  tempAngle  | double  |	Temporary angle, used to calculate all the values of yHeight
	*/
	public void drawSnake() {
		// Local Variable Declarations
		double[] yHeight = new double[600];
		double startAngle = 0.0;
		double tempAngle;

		while (true) {
			synchronized (c) {
				// Background
				c.setColor(new Color(173, 203, 227));
				c.fillRect(0, 0, 640, 500);
			}
			// Increasing the starting angle of the sin wave (slowly shifting the wave
			// further)
			startAngle += 0.3;
			tempAngle = startAngle;

			// Calculating the y heights for all the points on the sin wave
			for (int i = 0; i < yHeight.length; i++) {
				yHeight[i] = Math.sin(tempAngle) * 50.0;
				tempAngle += (Math.PI * 2 / 400);
			}

			// Synchronised use to clean up the threads
			synchronized (c) {
				// Drawing out the wave
				for (int x = 0; x < yHeight.length - 50; x += 10) {
					c.setColor(snakeColor);
					c.fillOval((int) x - 25, yPos + (int) yHeight[x] - 25, 50, 50);
				}
			}
			synchronized (c) {
				// Eyes of the snake
				c.setColor(new Color(255, 255, 255));
				c.fillOval(538, (int) (yHeight[550]) + yPos - 15, 14, 14);
				c.fillOval(538, (int) (yHeight[550]) + yPos, 14, 14);
			}
			synchronized (c) {
				c.setColor(new Color(0, 0, 0));
				c.fillOval(540, (int) (yHeight[552]) + yPos - 13, 10, 10);
				c.fillOval(540, (int) (yHeight[552]) + yPos + 2, 10, 10);
			}
			synchronized (c) {
				// Exit message
				c.setColor(new Color(42, 77, 105));
				c.setFont(new Font("Times New Roman", 3, 24));
				c.drawString("Press any key to start the game", 166, 490);
			}

			// Delay
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}
	

	// Starts the thread
	public void run() {
		drawSnake();
	}
}
