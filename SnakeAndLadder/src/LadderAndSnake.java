//------------------------------------------------------------------------
//Assignment 1 Part 1
//Written by: Prabhmehar Singh Kharbanda  Student ID: 40210198
//Date-7/2/2022
//For COMP 249 
//------------------------------------------------------------------------
/* This program contains 4 classes
 * Class Snake to implement a snake
 * Class Ladder to implement a ladder
 * Class Board to implement a Board
 * The board class has multiple objects of both Snake and Ladder classes
 * Class LadderAndSnake to implement the LadderAndSnake game
 * The LadderAndSnake class contains a Board object
 */
//------------------------------------------------------------------------
/** Assignment 1 Part 1
* Written by: Prabhmehar Singh Kharbanda  Student ID: 40210198
* Due Date-7/2/2022
* For COMP 249
*/
/**
 * Class to implement a snake It has 2 attributes for the head and tail of snake
 * 
 * @author prabh
 */
class Snake {
	// attributes
	private int snake_head;
	private int snake_tail;

	/**
	 * parameterized constructor to initialize the Snake object
	 * 
	 * @param h for setting snake head
	 * @param t for setting snake tail
	 */
	public Snake(int h, int t) {
		snake_head = h;
		snake_tail = t;
	}

	// accessor methods
	/**
	 * accessor method for snake_head
	 * @return snake_head
	 */
	public int get_snake_head() {
		return snake_head;
	}

	/**
	 * accessor method for snake_tail
	 * @return snake_tail
	 */
	public int get_snake_tail() {
		return snake_tail;
	}

	// mutator methods
	/**
	 * mutator method for snake_head
	 * @param h for getting the value to be stored in snake_head
	 */
	public void set_snake_head(int h) {
		snake_head = h;
	}

	/**
	 * mutator method for snake_tail
	 * @param t for getting the value to be stored in snake_tail
	 */
	public void set_snake_tail(int t) {
		snake_tail = t;
	}

}

/**
 * Class to implement a ladder It has 2 attributes for the top and bottom of
 * ladder
 */
class Ladder {
	// attributes
	private int ladder_top;
	private int ladder_bottom;

	// parameterized constructor
	/**
	 * parameterized constructor to initialize the Ladder object
	 * 
	 * @param t for ladder_top
	 * @param b for ladder_bottom
	 */
	public Ladder(int t, int b) {
		ladder_top = t;
		ladder_bottom = b;
	}

	// accessor methods
	/**
	 * accessor method for ladder_top
	 * @return ladder_top
	 */
	public int get_ladder_top() {
		return ladder_top;
	}

	/**
	 * accessor method for ladder_bottom
	 * @return ladder_bottom
	 */
	public int get_ladder_bottom() {
		return ladder_bottom;
	}

	// mutator methods
	/**
	 * mutator method for ladder_top
	 * 
	 * @param t for setting ladder_top
	 */
	public void set_ladder_top(int t) {
		ladder_top = t;
	}

	/**
	 * mutator method for ladder_bottom
	 * 
	 * @param b for setting ladder_bottom
	 */
	public void set_ladder_bottom(int b) {
		ladder_bottom = b;
	}
}

/**
 * Class to implement a Board It has 7 attributes These include an array of
 * Snake objects(pointers) to implement multiple snakes an array of Ladder
 * objects(pointers) to implement multiple ladders 2 int attributes for number
 * of snakes and ladders 2 int attributes for number of rows and columns 1 int
 * attribute for finish position
 */
class Board {
	// attributes
	private Snake[] snakes; // array of Snake objects
	private Ladder[] ladders; // array of Ladder objects
	private int numSnakes;
	private int numLadders;
	private int finishP;
	private int numRows;
	private int numColumns;

	// default constructor
	/**
	 * default construcor to initialize the Board object
	 */
	public Board() {
		numSnakes = 8;
		numLadders = 9;
		setSnakes();
		setLadders();
		finishP = 100;
		numRows = 10;
		numColumns = 10;

	}

	/**
	 * method to initialize snakes array to set the snakes on the board
	 */
	public void setSnakes() {
		snakes = new Snake[numSnakes];
		snakes[0] = new Snake(98, 78);
		snakes[1] = new Snake(97, 76);
		snakes[2] = new Snake(95, 24);
		snakes[3] = new Snake(93, 68);
		snakes[4] = new Snake(79, 19);
		snakes[5] = new Snake(64, 60);
		snakes[6] = new Snake(48, 30);
		snakes[7] = new Snake(16, 6);
	}

	/**
	 * method to initialize ladders array to set the ladders on the board
	 */
	public void setLadders() {
		ladders = new Ladder[numLadders];
		ladders[0] = new Ladder(100, 80);
		ladders[1] = new Ladder(91, 71);
		ladders[2] = new Ladder(84, 28);
		ladders[3] = new Ladder(67, 51);
		ladders[4] = new Ladder(44, 36);
		ladders[5] = new Ladder(42, 21);
		ladders[6] = new Ladder(38, 1);
		ladders[7] = new Ladder(31, 9);
		ladders[8] = new Ladder(14, 4);
	}

	// accessor methods
	/**
	 * accessor method for numSnakes
	 * @return numSnakes 
	 */
	public int get_numSnakes() {
		return numSnakes;
	}

	/**
	 * accessor method for numLadders
	 *  @return numLadders 
	 */
	public int get_numLadders() {
		return numLadders;
	}

	/**
	 * accessor method for numRows
	 * @return numRows
	 */
	public int get_numRows() {
		return numRows;
	}

	/**
	 * accessor method for numColumns
	 * @return numColumns
	 */
	public int get_numColumns() {
		return numColumns;
	}

	/**
	 * accessor method for finishP
	 * @return finishP
	 */
	public int get_finishP() {
		return finishP;
	}

	/**
	 * method to find out if a player is bit by a snake and to return his new
	 * position position does not change if the player is not bit by a snake
	 * 
	 * @param p for getting player position(current)
	 * @param n for getting player number
	 * @return p - new position for current player
	 */

	public int snakeBit(int p, int n) {
		for (int j = 0; j < numSnakes; j++) {
			if (p == snakes[j].get_snake_head()) {
				System.out.println("PLAYER " + n + " GOT BIT BY A SNAKE");
				p = snakes[j].get_snake_tail();
				break;
			}
		}
		return p;
	}

	/**
	 * method to find out if a player climbed a ladder and to return his new
	 * position position does not change if the player does not climb a ladder
	 * 
	 * @param p for getting player position(current)
	 * @param n for getting player number
	 * @return p - new position for current player
	 */
	public int ladderClimbed(int p, int n) {
		for (int j = 0; j < numLadders; j++) {
			if (p == ladders[j].get_ladder_bottom()) {
				System.out.println("PLAYER " + n + " CLIMBED A LADDER");
				p = ladders[j].get_ladder_top();
				break;
			}
		}
		return p;
	}

	/**
	 * method to check if there is an element(snake head, ladder top.. ) at the
	 * current position p (and returning a String ) so that it can be displayed on
	 * the board 
	 * @param p for getting player position(current)
	 * @return s - defines the type of element at the current position
	 */
	public String element(int p) {
		String s = "";
		for (int j = 0; j < numSnakes; j++) {
			if (p == snakes[j].get_snake_head()) {
				s = "SH" + (j + 1);
				break;
			} else if (p == snakes[j].get_snake_tail()) {
				s = "ST" + (j + 1);
				break;
			}
		}
		for (int j = 0; j < numLadders; j++) {
			if (p == ladders[j].get_ladder_bottom()) {
				s = "LB" + (j + 1);
				break;
			} else if (p == ladders[j].get_ladder_top()) {
				s = "LT" + (j + 1);
				break;
			}
		}
		return s;
	}

	/**
	 * method to check if there is a player at the current position (and returning a
	 * String) so that it can be displayed on the board
	 * 
	 * @param x for the number on board
	 * @param a for array of positions of all players
	 * @param b for array of orders of all players
	 * @return s - string to show the player number at current position
	 */
	public String playerFinder(int x, int[] a, int[] b) {
		String s = "";
		for (int i = 0; i < a.length; i++) {
			if (x == a[i]) {
				s += "P" + b[i];
			}

		}
		return s;
	}

	/**
	 * method to display the Board object
	 * 
	 * @param pp for array of positions of all player
	 * @param po for array of orders of all players
	 */
	public void display(int[] pp, int[] po) {
		int x = numRows * numColumns;
		int a, num;
		System.out.print("\n");
		for (int i = 0; i < 3 * numRows; i++) {
			a = i / 3;
			if (i % 3 == 0) {
				for (int b = 0; b < 80; b++) {
					System.out.print("_");
				}
				System.out.print("\n");
			}

			for (int j = 0; j < numColumns; j++) {
				num = ((a % 2 == 0) ? ((x - j - numColumns * a)) : ((x - 9 + j - numColumns * a)));
				if (i % 3 == 0) {
					System.out.print(((j == 0) ? ("|") : ("")) + num + "\t|");
				} else if (i % 3 == 1) {
					System.out.print(((j == 0) ? ("|") : ("")) + element(num) + "\t|");

				} else {
					System.out.print(((j == 0) ? ("|") : ("")) + playerFinder(num, pp, po) + "\t|");
				}

			}
			System.out.print("\n");

		}
		for (int i = 0; i < 80; i++) {
			System.out.print("_");
		}
		System.out.print("\n");

	}
}

/**
 * Class to implement the LadderAndSnake game it has 6 attributes 1 int for
 * number of players 1 int array for orders of all players 1 int array for dice
 * rolls of all players 1 int array for positions of all players 1 (pointer to
 * a) Board object 1 boolean attribute to check if the game has ended
 */
public class LadderAndSnake {
	// attributes
	private int num_players;
	private Board game_board; // object of Board Class
	private int[] playerOrder;
	// playerRolls and playerposition will be recorded in order only
	// i.e playerRoll[0] would be the roll of player with order 1
	private int[] playerRolls;
	private int[] player_position;
	private boolean finish;

	/**
	 * parameterized constructor to initialize the LadderAndSnake object
	 * the 3 int arrays are given size equal to
	 * num_players and each element is initialized to 0 (default value)
	 * game_board is made to point at a Board object finish is initialized
	 * to false (default value)
	 * @param x for number of players 
	 * 
	 */
	public LadderAndSnake(int x) {
		num_players = x;
		player_position = new int[num_players];
		game_board = new Board();
		playerOrder = new int[num_players];
		playerRolls = new int[num_players];
		finish = false;
	}

	/**
	 * method to create deep copy of player_position array
	 * 
	 * @return a for deep copy of player_position array
	 */
	public int[] deepCopyPosition() {
		int[] a = new int[num_players];
		for (int i = 0; i < num_players; i++) {
			a[i] = player_position[i];
		}
		return a;
	}

	/**
	 * method to create deep copy of playerOrder array
	 * 
	 * @return a for deep copy of playerOrder array
	 */
	public int[] deepCopyOrder() {
		int[] a = new int[num_players];
		for (int i = 0; i < num_players; i++) {
			a[i] = playerOrder[i];
		}
		return a;
	}

	/**
	 * method to flip the dice that returns a random value between 1 and 6
	 * inclusively
	 * 
	 * @return int value for dice throw
	 */
	public int flipDice() {
		return (int) Math.round(5 * Math.random() + 1);
	}

	/**
	 * method to find the initial throwing order
	 */
	public void playerSort() {
		boolean orderSet[] = new boolean[num_players], breakCheck;
		int x = 0; // variable to set playerOrder
		int maxP = 0;
		int drawCount = 0;
		boolean drawP[] = new boolean[num_players];
		int d = 0; // variable to set drawThrow
		int p = 1; // variable to compare position in draw order
		boolean sameDrawCheck = false;
		int drawOrder[];
		boolean drawOrderSet[];
		int drawThrow[];
		int dMaxP = 0;
		System.out.println("Deciding playing order");
		for (int i = 0; i < num_players; i++) {
			playerRolls[i] = flipDice();
			System.out.println("Player " + (i + 1) + " got a dice throw of " + playerRolls[i]);
		}
		do {
			drawP = new boolean[num_players];
			drawCount = 0;
			breakCheck = false;
			for (int i = 0; i < num_players; i++) {
				if (!orderSet[i]) {
					maxP = i;
					breakCheck = true;
					break;
				}
			}
			if (!breakCheck)
				break;
			for (int j = 0; j < num_players; j++) {
				if (orderSet[j] || j == maxP)
					continue;
				else {
					if (playerRolls[j] > playerRolls[maxP]) {
						maxP = j;
						drawCount = 0; // only counting draws for the max position
						drawP = new boolean[num_players]; // setting all drawP to false
					} else if (playerRolls[j] == playerRolls[maxP]) {
						drawCount++;
						drawP[maxP] = true; // a throw will always draw with itself
						drawP[j] = true;
					}
				}
			}
			if (drawCount == 0) {
				playerOrder[x] = maxP + 1;
				orderSet[maxP] = true;
			} else { // if there is a draw the program doesn't let two players get the same number
				d = 0;
				dMaxP = 0;
				drawThrow = new int[drawCount + 1];
				sameDrawCheck = false;
				drawOrder = new int[drawCount + 1];
				drawOrderSet = new boolean[drawCount + 1];
				int b = 0;// looping variable
				System.out.println("There is a draw for order " + (x + 1) + " between");
				for (int z = 0; z < num_players; z++) {
					if (drawP[z])
						System.out.println("Player " + (z + 1));
				}
				System.out.println("So they have to throw again");
				for (int z = 0; z < num_players; z++) {
					if (drawP[z]) {
						do {
							if (sameDrawCheck)
								System.out.println("SORRY YOU HAVE TO THROW AGAIN!");
							System.out.println("Player " + (z + 1) + " is throwing");
							drawThrow[d] = flipDice();
							for (b = 0; b < d; b++) {
								if (drawThrow[d] == drawThrow[b]) {
									sameDrawCheck = true;
									break;
								}
							}
							if (b == d)
								sameDrawCheck = false;
						} while (sameDrawCheck);
						d++;
						System.out.println("Player " + (z + 1) + " got a dice throw of " + drawThrow[d - 1]);
					}
				}
				for (int a = 0; a <= drawCount; a++) {
					for (int c = 0; c <= drawCount; c++) {
						if (!drawOrderSet[c]) {
							dMaxP = c;
							break;
						}
					}
					for (int i = 0; i <= drawCount; i++) {
						if (drawOrderSet[i] || dMaxP == i)
							continue;
						if (drawThrow[dMaxP] < drawThrow[i])
							dMaxP = i;
					}
					drawOrder[a] = dMaxP + 1;
					drawOrderSet[dMaxP] = true;

				}
				p = 1;
				for (int z = 0; z < num_players; z++) {
					if (drawP[z]) {
						for (int i = 0; i <= drawCount; i++) {
							if (drawOrder[i] == p) {
								playerOrder[x + i] = z + 1;
								orderSet[z] = true;
								p++;
								break;
							}
						}
					}

				}
				x += drawCount;
			}
			x++;
		} while (true);
		System.out.println("THE THROWING ORDER IS");
		for (int i = 0; i < num_players; i++) {
			System.out.println("Player " + playerOrder[i]);
		}
	}

	/**
	 * method to start the game
	 */
	public void play() {
		int i; // looping variable
		System.out.println("IN THE OUTPUT - " + "\n SH Denotes \t Snake Head " + "\n ST Denotes \t SNAKE TAIL "
				+ "\n LT Denotes \t Ladder Top " + "\n LB denotes \t Ladder Bottom " + "\n P Denotes \t Player "
				+ "\nThere corresponding numbers are also displayed");
		System.out.println("Game is played between " + num_players + " players");
		playerSort();
		do {
			for (i = 0; i < num_players; i++) {
				System.out.println("PLAYER " + playerOrder[i] + " is at " + player_position[i]);
				System.out.println("PLAYER " + playerOrder[i] + " is throwing");
				playerRolls[i] = flipDice();
				System.out.println("PLAYER " + playerOrder[i] + " got a dice throw of " + playerRolls[i]);
				player_position[i] += playerRolls[i];
				if (player_position[i] > game_board.get_finishP()) {
					player_position[i] = 2 * game_board.get_finishP() - player_position[i];
				}
				player_position[i] = game_board.snakeBit(player_position[i], playerOrder[i]);
				player_position[i] = game_board.ladderClimbed(player_position[i], playerOrder[i]);
				if (player_position[i] == game_board.get_finishP()) {
					System.out
							.println("PLAYER " + playerOrder[i] + " reached " + game_board.get_finishP() + " and WON");
					finish = true;
					break;
				}
				if (!finish)
					System.out.println("PLAYER " + playerOrder[i] + " reached " + player_position[i]);

			}
			game_board.display(deepCopyPosition(), deepCopyOrder());
		} while (!finish);
	}

}
