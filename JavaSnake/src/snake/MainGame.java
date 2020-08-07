//By: Sumantra Das
//HARVARD ID: 41487461
//Start: July 31, 2020
//End: August 5, 2020

/*
 
 This is the Classic 2D snake game on Java, and this is the mainframe of the game
 where we setup the JFrame and the title and other user interface features. It was 
 was one of the first games that I've ever played and bring back lots of memories. 
  
 
 */

package snake;

//used to change color of the borders and backgrounds
import java.awt.Color;
import javax.swing.JFrame;

//main game class
public class MainGame 
{

	
	public static void main(String[] args) 
	{
		
		
		//creates new JFrame and links MainGame with GameLogic
        JFrame snake = new JFrame();
        GameLogic gameplay = new GameLogic();
        
        //exits the game when user clicks close(x)
        snake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        snake.setVisible(true);
        //sets the title of the game
        snake.setTitle("Classic Snake");
        //makes the background color DARK GRAY
        snake.setBackground(Color.DARK_GRAY);
        snake.setResizable(false);
        //sets the size of the screen 
        snake.setBounds(10, 10, 905, 700);
        snake.add(gameplay);
        

	}
	

}
