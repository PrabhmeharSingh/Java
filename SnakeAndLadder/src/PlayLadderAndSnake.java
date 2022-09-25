//------------------------------------------------------------------------
//Assignment 1 Part 2
//Written by: Prabhmehar Singh Kharbanda  Student ID: 40210198
//Date-7/2/2022
//For COMP 249 
//------------------------------------------------------------------------
/* This program contains a driver class named PlayLadderAndSnake
 * which is used to run/use the LadderAndSnake class
 * it asks the user to enter the number of players and only runs 
 * the LadderAndSnake class if the number of players is 
 *  b/w 2 and 4 inclusively
 */
/** Assignment 1 Part 2
* Written by: Prabhmehar Singh Kharbanda  Student ID: 40210198
* Due Date-7/2/2022
* For COMP 249
*/
import java.util.Scanner;

/**
 * Driver class to run/use the LadderAndSnake class
 * 
 * @author prabh
 *
 */
public class PlayLadderAndSnake {

	/**
	 * main method for the program execution start point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// variables
		int n; // variable for inputting number of players
		int x = 0; // variable to count the number of attempts
		boolean exit = false; // boolean variable to check if correct value was assigned to n
		Scanner cin = new Scanner(System.in);
		// displaying welcome message
		System.out.println(
				"Welcome to the Ladder and Snake game" + "\nThis program is made by Prabhmehar Singh Kharbanda");
		// actual implementation
		System.out.println("Enter the # of players for your game " + "\nNumber must be between 2 and 4 inclusively");
		do {
			n = cin.nextInt();
			if (n < 2 || n > 4) {
				System.out.println("Bad attempt " + (++x) + " Invalid number of players \nNumber"
						+ " must be between 2 and 4 inclusively");
			} else
				exit = true;

		} while (x < 4 && exit == false);
		if (!exit)
			System.out.println("You have exhausted all your attempts");
		else {
			LadderAndSnake a = new LadderAndSnake(n);
			a.play();
		}
		// displaying closing message
		System.out.println("Thank you for playing the game" + "\n The game has ended");
		cin.close();

	}

}
