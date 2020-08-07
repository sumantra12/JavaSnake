//By: Sumantra Das
//HARVARD ID: 41487461
//Start: July 31, 2020
//End: August 5, 2020


 

package snake;

//imported needed functions
import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Random;


public class GameLogic 
extends JPanel implements KeyListener, ActionListener 
{
	
	/*
	 
	 *This Java file contains the main logic of the game and all of the features
	 that I've discussed on the Proposal. Making Snake on Java gave me the chance
	 to learn some object-oriented programming and learn how to use graphics module
	 on Java. I've also applied some of the topics we've learned in CS50 to this 
	 game specifically, like functions, strings, arrays, loops, data types and 
	 conditionals. Along with that I learned new concepts such as the JPanel, Graphics
	 KeyEvents, Random, Timer and other imported functions. 
	 
	 */
	
	private static final long serialVersionUID = 1L;
	
	//imageicon variable for the title of the game 
	private ImageIcon gametitle;
	
	//array for the length of x and y axis of the snake in the game board 
	private int[] lengthofsnakeX = new int[750];
	private int[] lengthofsnakeY = new int[750];
	
	//An array that stores that x and y coordinates for the random spawning of the apples, as pointer
	private int [] pointxaxis = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 
							     425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675,700, 725, 750, 775, 800};
	
	private int [] pointyaxis = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 
			                     375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	//variable int for keeping track of user score
	private int snakescore;
	
	
	//snake heads down animation
	private ImageIcon downmouth;
	//snake head up animation
	private ImageIcon upmouth;
	//snake head left animation
	private ImageIcon leftmouth;
	//snake head right animation
	private ImageIcon rightmouth;
	//snake length animation
	private ImageIcon snakeimage;
	//apple animation
	private ImageIcon points;
	
	//random variable makes sure that the apple spawns at random in the board
	private Random applerandom = new Random();
	
	//the number of possible xpos and ypos
	private int pointxpos = applerandom.nextInt(32);
	
	private int pointypos = applerandom.nextInt(23);
	
	//sets the starting length of the snake to 3
	private int lengthsnake = 3;

	//variables for the movement of the snake
	//all of them are set to false before user starts the game
	private boolean up = false;
	private boolean right = false;
	private boolean left = false;
	private boolean down = false;
	
	
	
	private Timer timer;
	
	//speed of the snake, lower number = higher speed
	private int timedelay = 100;
	
	//how many pixels will the snake move 
	private int snakemoves = 0;
	
	//this function will start when the game has begun and user starts playing 
	public GameLogic() 
	{
		
		
		//adds the key listeners and waits for users input
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		
		//starts the users times
		timer = new Timer(timedelay, this);
		timer.start();
		
		
	}
	
	//this function sets up all of the graphics for the main page
	public void paint(Graphics mainpage)
	{
		
		
		//sets the location of the snake before the user starts playing(default position)
		if(snakemoves == 0)
		{
			
			
			lengthofsnakeX[2] = 40;
			lengthofsnakeX[1] = 75;
			lengthofsnakeX[0] = 100;
			
			lengthofsnakeY[2] = 100;
			lengthofsnakeY[1] = 100;
			lengthofsnakeY[0] = 100;
			
			
		}
		
		//game title border
		mainpage.setColor(Color.BLUE);
		mainpage.drawRect(24, 10, 851, 55);
		
		
		//imports the game title image
		gametitle = new ImageIcon("gameplaytitle.jpg");
		gametitle.paintIcon(this, mainpage, 25, 11);
		
		//sets the score of the game
		mainpage.setColor(Color.GREEN);
		mainpage.setFont(new Font("times", Font.PLAIN, 18));
		mainpage.drawString("Player Score:  "+snakescore, 730, 30);
		
		//sets the length of the snake for the player
		mainpage.setColor(Color.GREEN);
		mainpage.setFont(new Font("times", Font.PLAIN, 18));
		mainpage.drawString("Player Length:  "+lengthsnake, 730, 50);
		
		
		//border for the Game play
		mainpage.setColor(Color.BLUE);
		mainpage.drawRect(24, 74, 851, 577);
		  
		//Background of the game
		mainpage.setColor(Color.GREEN);
		mainpage.fillRect(25, 75, 850, 575);
		
		
		//imports the right mouth animation of the snake for the default position 
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, mainpage, lengthofsnakeX[0], lengthofsnakeY[0]);
		
		int s;
		
		//main game loop
		for(s = 0; s < lengthsnake; s++) 
		{
			
			
			//draws the right mouth animation of the snake when right is true
			if(s == 0 && right)
			{
				
				
				//imports right mouth animation
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, mainpage, lengthofsnakeX[s], lengthofsnakeY[s]);
				
				
			}
			
			//draws the up mouth animation of the snake when up is true 
			if(s == 0 && up)
			{
				
				
				//imports up mouth animation
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, mainpage, lengthofsnakeX[s], lengthofsnakeY[s]);
				
				
			}
			
			//draws the down mouth animation of the snake when down is true 
			if(s == 0 && down)
			{
				
				
				//imports down mouth animation
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, mainpage, lengthofsnakeX[s], lengthofsnakeY[s]);
				
				
			}
			
			//draws the left mouth animation of the snake when left is true 
			if(s == 0 && left)
			{
				
				
				//imports left mouth animation
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, mainpage, lengthofsnakeX[s], lengthofsnakeY[s]);
				
				
			}
			
			//when s not equal to zero that means its the 1 element array, which is not the head and its the body
			//therefore, it draws the body of the snake
			if(s != 0)
			{
				
				
				//imports the snake body image
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, mainpage, lengthofsnakeX[s], lengthofsnakeY[s]);
				
				
			}
			
			
		}
		
		//imports the apple image
		points = new ImageIcon("apple.png");
		
		//checks if the head of the snake(0 element in array) is colliding with the apple position 
		if((pointxaxis[pointxpos] == lengthofsnakeX[0]) && pointyaxis[pointypos] == lengthofsnakeY[0])
		{
			
			
			//when collides then adds one to the length
			lengthsnake =  lengthsnake + 1;
			
			//the apple respawns in new location  
			pointxpos = applerandom.nextInt(32);
			pointypos = applerandom.nextInt(23);
			
			//increments the user score when he/she collects the point
			snakescore = snakescore + 1;
			
			
			
		}
		
		//draws the points on the screen
		points.paintIcon(this, mainpage, pointxaxis[pointxpos], pointyaxis[pointypos]);
		
		
		//crash variable
		int crash;
		
		//checks if the snake head has hit its body, then player loses the game
		for(crash = 1; crash < lengthsnake; crash = crash + 1)
		{
			
			//if the crash == head or 0th element of the array, therefore the user has lost 
			if(lengthofsnakeX[crash] == lengthofsnakeX[0] && lengthofsnakeY[crash] == lengthofsnakeY[0])
			{
				
				
				//game over screen and restart button
				mainpage.setColor(Color.WHITE);
				mainpage.setFont(new Font("Times", Font.BOLD, 50));
				mainpage.drawString("Game Over", 300, 300);
				
				mainpage.setFont(new Font("Times", Font.BOLD, 20));
				mainpage.drawString("SPACE to RESTART", 340, 340);
				
				//makes sure user can't keep on playing after lost, sets all motion variables to false
				up = false;
				down = false;
				left = false;
				right = false;
						
				
			}
			
		}

		
	}

	//After the user has pressed the key this created the actionEvent or what will change on the screen
	public void actionPerformed(ActionEvent e)
	{
		
		
		timer.start();
		
		//when up is set to true
		if(up)
		{
			
			
			//runs for loop itterating over length of the snake and subtracts one from its length 
			for(int r = lengthsnake-1; r >= 0; r = r - 1)
			{
				
				//then another one is added back to r, so the snake can move 
				lengthofsnakeX[r+1] = lengthofsnakeX[r];
				
				
			}
			
			//when the snake goes through the wall and comes back the other side
			for(int r = lengthsnake; r >= 0; r = r - 1) 
			{
				
				
				if(r==0)
				{
					
					
					lengthofsnakeY[r] = lengthofsnakeY[r] - 25;
					
					
				}
				
				else
				{
					
					
					lengthofsnakeY[r] = lengthofsnakeY[r-1];
					
					
				}
				
				//when makes contact with the walls 
				if(lengthofsnakeY[r] < 75)
				{
					
					
					lengthofsnakeY[r] = 625;
					
					
				}
				
			}
			
			repaint();
			
			
		}
		
		//when down is set to true
		if(down)
		{
			
			
			//subtracts one from the length when itterating over
			for(int r = lengthsnake-1; r >= 0; r = r - 1)
			{
				
				
				//adds one back to the length to move the snake down
				lengthofsnakeX[r+1] = lengthofsnakeX[r];
				
				
			}
			
			//when the goes through the wall and comes out the other side
			for(int r = lengthsnake; r >= 0; r = r - 1) 
			{
				
				
				if(r==0)
				{
					
					
					lengthofsnakeY[r] = lengthofsnakeY[r] + 25;
					
					
				}
				
				else
				{
					
					
					lengthofsnakeY[r] = lengthofsnakeY[r-1];
					
					
				}
				
				//when makes contact with the walls 
				if(lengthofsnakeY[r] > 625)
				{
					
					
					lengthofsnakeY[r] = 75;
					
					
				}
				
			}
			
			repaint();
			
			
		}
		
		
		//when left is set to true 
		if(left)
		{
			
			
			//subtracts one from the length when itterating over the length 
			for(int r = lengthsnake-1; r >= 0; r = r - 1)
			{
				
				
				//adds one back so the snake moves forward by one 
				lengthofsnakeY[r+1] = lengthofsnakeY[r];
				
				
			}
			
			for(int r = lengthsnake; r >= 0; r--) 
			{
				
				
				if(r==0)
				{
					
					
					lengthofsnakeX[r] = lengthofsnakeX[r] - 25;
					
					
				}
				
				else
				{
					
					
					lengthofsnakeX[r] = lengthofsnakeX[r-1];
					
					
				}
				
				//when makes contact with the walls 
				if(lengthofsnakeX[r] < 25)
				{
					
					
					lengthofsnakeX[r] = 850;
					
					
				}
				
				
			}
			
			repaint();
			
			
		}
		
		//when right is true
		if(right)
		{
			
			//subtracts one from the length when itterating over the length 
			for(int r = lengthsnake-1; r >= 0; r = r - 1)
			{
				
				
				// adds one back so the snake moves forward by one 
				lengthofsnakeY[r+1] = lengthofsnakeY[r];
				
				
			}
			
			//when the goes through the wall and comes out the other side
			for(int r = lengthsnake; r >= 0; r--) 
			{
				
				if(r==0)
				{
					
					
					lengthofsnakeX[r] = lengthofsnakeX[r] + 25;
					
					
				}
				
				else
				{
					
					
					lengthofsnakeX[r] = lengthofsnakeX[r-1];
					
					
				}
				
				//when makes contact with the walls 
				if(lengthofsnakeX[r] > 850)
				{
					
					
					lengthofsnakeX[r] = 25;
					
					
				}
				
			}
			
			repaint();
			
			
		}
		
	}

	//movement of the snake when the key of pressed
	public void keyPressed(KeyEvent e) 
	{
		
		//variable to check if user pressed space
		int restart = e.getKeyCode();
		 
		int key = e.getKeyCode();
		
		//when user pressed space
		if(restart == KeyEvent.VK_SPACE)
		{
			
			
			//reset game score, position and length of snake
			snakemoves = 0;
			snakescore = 0;
			lengthsnake = 3;
			repaint(); 
			
			
		}
		
		//if the player presses "W", then snake will move up
		if(key == KeyEvent.VK_W)
		{
			
			
			//moves is incremented
			snakemoves = snakemoves + 1;
			//up variable is set to true
			up = true;
			
			//when down is not pressed up is therefore true
			if(!down)
			{
				
				
				up = true;
				
				
			}
			
			//however, if down is pressed then up is false
			else
			{
				
				
				up = false;
				down = true;
				
				
			}
			
			//left and right are still set as false
			left = false;
			right = false;
			
			//if the user crashes with themselves then, up is set to false
			int crashup;
			
			for(crashup = 1; crashup < lengthsnake; crashup = crashup + 1)
			{
				
				
				if(lengthofsnakeX[crashup] == lengthofsnakeX[0] && lengthofsnakeY[crashup] == lengthofsnakeY[0])
				{
					
					
					up = false;
					
					
				}
				
			
			}
			
			
		}
		
		//snake will move down when "S" is pressed 
		if(key == KeyEvent.VK_S)
		{
			
			//snake movement is incremented 
			snakemoves = snakemoves + 1;
		
			//set to true
			down = true;
			
			//when not up then down true, else up true and down false
			if(!up)
			{
				
				
				down = true;
				
				
			}
			
			else
			{
				
				
				up = true;
				down = false;
				
				
			}
			
			//left and right are still set as false
			left = false;
			right = false;
			
			//if the user crashes with themselves then, down is set to false
			int crashdown;
			
			for(crashdown = 1; crashdown < lengthsnake; crashdown = crashdown + 1)
			{
				
				
				if(lengthofsnakeX[crashdown] == lengthofsnakeX[0] && lengthofsnakeY[crashdown] == lengthofsnakeY[0])
				{
					
					
					down = false;
					
					
				}
				
			
			}
			
		}
		
		//when "D" is pressed snake will move right 
		if(key == KeyEvent.VK_D)
		{
			
			
			snakemoves = snakemoves + 1;
			
			//right is set to true
			right = true;
			
			//when not left then right is true, else left is true and right is false
			if(!left)
			{
				
				
				right = true;
				
				
			}
			else
			{
				
				
				left = true;
				right = false;
				
				
				
			}
			
			//up and down are false
			up = false;
			down = false;
			
			
			//if the user crashes with themselves then, right is set to false
			int crashright;
			
			for(crashright = 1; crashright < lengthsnake; crashright = crashright + 1)
			{
				
				
				if(lengthofsnakeX[crashright] == lengthofsnakeX[0] && lengthofsnakeY[crashright] == lengthofsnakeY[0])
				{
					
					
					right = false;
					
					
				}
				
			
			}
			
			
			
		}
		
		
		
		//when "A" is pressed snake moves left  
		if(key == KeyEvent.VK_A)
		{
			
			
			snakemoves = snakemoves + 1;
			
			//set left to true
			left = true;
			
			//when right is false left is true else right is true and left motion will be false
			if(!right)
			{
				
				
				left = true;
				
				
			}
			
			else
			{
				
				
				left = false;
				right = true;
				
				
			}
			
			//up and down are still false
			up = false;
			down = false;
			
			
			//if the user crashes with themselves then, left is set to false
			int crashleft;
			
			for(crashleft = 1; crashleft < lengthsnake; crashleft = crashleft + 1)
			{
				
			
				if(lengthofsnakeX[crashleft] == lengthofsnakeX[0] && lengthofsnakeY[crashleft] == lengthofsnakeY[0])
				{
					
					
					left = false;
					
					
				}
				
			
			}
			
			
			
			
		}
		
		
		
	}
	
	
	public void keyTyped(KeyEvent e) 
	{
		//Ignore these functions, they are needed for the code to function
		
	}

	
	public void keyReleased(KeyEvent e) 
	{
		//Ignore these functions, they are needed for the code to function
		
	}
	
	

}
