// CS1450 Assignment 6 Sprin 2020
// Worksheet #3: Complicated Nested Objects


import java.util.LinkedList;
import java.util.Queue;

public class Worksheet3_Spring2020 {

	public static void main(String[] args) {
		
		//**********************************
		// Worksheet3 Pinball Machine Setup 
		//**********************************
		// I'm going to build a pinball machine and fill it with a bunch of players by HARD CODING!
		// I'm doing this to test the code.
		
		// Create a pinball machine object that contains a list of players
		// The list of players is an array and will have 5 slots (0-4)
		int maxNumberPlayers = 5;
		PinballMachineW3 pinballMachine = new PinballMachineW3(maxNumberPlayers);

		System.out.println();
		System.out.println("Hard coding 4 players to test code");
		System.out.println("Frank contains 3 scores");
		System.out.println("Paul contains 2 scores");
		System.out.println("Tim contains 1 score");
		System.out.println("Vic contains 3 scores");
		System.out.println();
		
		// Hard code 3 players - hard coding is the way to go when learning!
		// Of course we would not do this in an assignment!  
		// I'm doing it for testing purposes.
		PlayerW3 frank = new PlayerW3("Frank");  
		PlayerW3 paul = new PlayerW3("Paul");    
		PlayerW3 vic = new PlayerW3("Vic");    
		
		// Add players to pinball machine - hard coding the slot and column numbers for quick setup.
		// Of course we would not do this in a regular assignment - this is just for testing purposes
		pinballMachine.addPlayer(frank, 0);
		pinballMachine.addPlayer(paul, 3);
		pinballMachine.addPlayer(vic, 4);
				
		// Add some scores to each player	
		// Create some scores and add them to Frank in slot = 0
		Score frankScore1 = new Score(10500, "02/04/2020");
		Score frankScore2 = new Score(50000, "11/27/2019");
		Score frankScore3 = new Score(45900, "11/27/2019");
		Score frankScore4 = new Score(45000, "12/04/2019");
		pinballMachine.addScoreToPlayer(frankScore1, 0);
		pinballMachine.addScoreToPlayer(frankScore2, 0);
		pinballMachine.addScoreToPlayer(frankScore3, 0);
		pinballMachine.addScoreToPlayer(frankScore4, 0);

		// Create some scores and add them to Paul in slot = 3
		Score paulScore1 = new Score(33113, "03/31/2013");
		Score paulScore2 = new Score(80720, "08/01/2019");
		pinballMachine.addScoreToPlayer(paulScore1, 3);
		pinballMachine.addScoreToPlayer(paulScore2, 3);

		// Create some scores and add them to Vic in slot = 4
		Score vicScore1 = new Score(24900, "11/04/2019");
		Score vicScore2 = new Score(44580, "12/26/2019");
		Score vicScore3 = new Score(80902, "12/27/2019");
		pinballMachine.addScoreToPlayer(vicScore1, 4);
		pinballMachine.addScoreToPlayer(vicScore2, 4);
		pinballMachine.addScoreToPlayer(vicScore3, 4);

		// Now the pinball machine is setup with players that contain a score queues!
		// Display some information to test the code 
		for (int slot = 0; slot < maxNumberPlayers; slot++) {
			
			// Get a player out of location (slot)
			PlayerW3 aPlayer = pinballMachine.getPlayer(slot);
			
			// If there was a player in (slot) display scores in the queue
			if (aPlayer != null) {
				System.out.print("Player " + aPlayer.getName());

				// Display number of scores
				int numScores = aPlayer.getScoresSize();
				System.out.println(" contains " + numScores + " scores on this pinball machine");
				
				// To display scores, use method getScore to return a Score object 
				// which we call getValue on
				for (int i = 0; i < numScores; i++) {
					System.out.println("Score: " + aPlayer.getScore().getValue());
				}
				System.out.println();
			}
		} // for slot
		
	} // main

} // Worksheet3


class Score  {
	
	private int value;
	private String dateOfScore;
	
	public Score (int value, String dateOfScore) {
		this.value = value;
		this.dateOfScore = dateOfScore;
	}

	public int getValue() {
		return value;
	}
	
	public String getDateOfScore() {
		return dateOfScore;
	}
} // Score



//********************************
// Worksheet3 Question #1
// Write the code for each method
//********************************
class PlayerW3  {
	
	private String name;			
	private Queue<Score> scores = new LinkedList<>();  // Queue of scores this player obtained

	public PlayerW3 (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public int getScoresSize() {
		// ADD CODE HERE
		
		
	}
	
	public void addScore (Score score) {
		// ADD CODE HERE
		
		
	}
	
	public Score getScore () {
		// ADD CODE HERE
		
		
	}

} // Player


class PinballMachineW3 {
	
	private PlayerW3[] players;  	// Array of players that have scores stored on this pinball machine
	
	public PinballMachineW3 (int numPlayers) {
		players = new PlayerW3[numPlayers];  // Setup array to hold a certain number of players
	}
   
	// Add a player to the player array in the specific location
	public void addPlayer(PlayerW3 player, int slot) {
		players[slot] = player;
	}
	
	// Returns the player in the specified location in the array.  
	// When a slot does NOT contain a player return null.
	public PlayerW3 getPlayer(int slot) {
		return players[slot];
	}
	
	//********************************
	// Worksheet3 Question #3
	// Add a score to the player in location (slot) in the player array
	//********************************
	public void addScoreToPlayer(Score score, int slot) {
		// ADD CODE HERE

		
		
	}
	
} // PinballMachineW3

