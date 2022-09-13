// CS1450 Assignment 6 Spring 2020
// Worksheet #4: Priority Queues and Comparable Interface


import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Worksheet4_Spring2020 {

	public static void main(String[] args) {

		// ***********************************
		// Worksheet4 Question 3
		// Write the declaration for a priority queue of players (PlayerW4 objects)
		// ***********************************
		// ADD CODE HERE

		
		
		
		// Setup some hard coded players to use in the queue questions!
		// Create two hard coded players - hard coding is the way to go when learning!
		// Of course we would not do this in an assignment!  I'm doing it for testing purposes.
		PlayerW4 player1 = new PlayerW4("Frank");  
		PlayerW4 player2 = new PlayerW4("Paul");    

		// Add some scores to each player
		// Note this time I'm adding the scores with a call to addScore method on the player.
		// Create 3 hard coded score objects and add them to player #1's queue
		player1.addScore(new ScoreW4(10500, "02/04/2020"));
		player1.addScore(new ScoreW4(50000, "11/27/2019"));
		player1.addScore(new ScoreW4(45900, "11/27/2019"));
		
		// Create 2 hard coded score objects and add them to player #2's queue
		player2.addScore(new ScoreW4(33113, "03/31/2013"));
		player2.addScore(new ScoreW4(80720, "08/01/2019"));
		
		
		// ***********************************
		// Worksheet4 Question #4b 
		// Write code to add player1 and player2 to the priority queue
		// ***********************************
		// ADD CODE HERE 
		
		
		
		System.out.println("Player #1 is in priority queue");	
		System.out.println("Player #2 is in priority queue");

	} // main

} // Worksheet4


class ScoreW4  {
	
	private int value;
	private String dateOfScore;
	
	public ScoreW4 (int value, String dateOfScore) {
		this.value = value;
		this.dateOfScore = dateOfScore;
	}

	public int getValue() {
		return value;
	}
	
	public String getDateOfScore() {
		return dateOfScore;
	}
} // ScoreW4



class PlayerW4 {
	
	private String name;			
	private Queue<ScoreW4> scores = new LinkedList<>();  // Queue of scores this player obtained

	
	public PlayerW4 (String name) {
		this.name = name;
	}

	
	// ***********************************
	// Worksheet4 Question #4a
	// Copy your answer from Worksheet3 here
	// ***********************************
	public int getScoresSize() {
		// ADD CODE HERE
		
		
	}
	
	// ***********************************
	// Worksheet4 Question #4a
	// Copy your answer from Worksheet3 here
	// ***********************************
	public void addScore(ScoreW4 score) {
		// ADD CODE HERE


	}
		
	// ***********************************
	// Worksheet4 Question #4a
	// Copy your answer from Worksheet3 here
	// ***********************************
	public ScoreW4 getScore() {
		// ADD CODE HERE
		
		
	}

	// ***********************************
	// Worksheet4 Question #4f 
	// Add the code from question 4f here
	// ***********************************
	// ADD CODE HERE

	
	
	
	
} // Player

