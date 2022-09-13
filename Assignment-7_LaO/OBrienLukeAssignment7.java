import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/*
 * Name:		Luke O'Brien
 * Class:		Data Structure and Algorithms
 * Section:		002
 * Assignment:	Assignmnet-7
 * Due Date:	8 April 2020
 * 
 * Desc:
 * This program takes a file with a list of players, Each player
 * has a seat number and a rank. They are organized into an array
 * by seat number. Once that is done, a EscapeGame object and
 * EscapeGameController object are created. This then allows you
 * to pull the players from their seats and put them into the escape
 * rooms. Each time a player is moved, the program prints it out.
 * Once all the players have moved, it starts the simulate and prints
 * out the results as they happen along with the current leader.
 * 
 * once all the simulations have finished, the score are set on each
 * player object and then thrown into the Priority Queue that I built.
 * It is then organized by score, and then printed out by the 
 * displayResults() method, and that finishes the program.
 */


//--------------------------------------------- Test Class***
public class OBrienLukeAssignment7 {

	public static void main(String[] args) throws FileNotFoundException {
		Player[] seats = new Player[25];
		Scanner playerReader = new Scanner(new File("players.txt"));
		
		while(playerReader.hasNextLine()) {
			Player temp = new Player(playerReader.next(), playerReader.nextInt(), playerReader.nextInt());
			seats[temp.getSeat()] = temp;
		}
		playerReader.close();
		
		EscapeGame escGm = new EscapeGame();
		EscapeGameController escGmCont = new  EscapeGameController();
		
		escGmCont.movePlayerIntoEscapeGame(seats, escGm);
		System.out.println("\n\n");
		escGmCont.simulateGame(escGm);
		System.out.println("\n\n");
		escGmCont.displayResults(escGm);
	}

}

//--------------------------------------------- Player Class***
class Player implements Comparable<Player> {
	private String name;
	private int rank;
	private int seat;
	private int score;
	
	//-------------------------Constructor - Player Class
	public Player(String name, int rank, int seat) {
		score = 0;
		this.name = name;
		this.rank = rank;
		this.seat = seat;
	}
	
	//------------------------- Getters and Setters
	public String getName() {
		return name;
	}
	public int getRank() {
		return rank;
	}
	public int getSeat() {
		return seat;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	//-------------------------implemented CompareTo method - Player Class
	public int compareTo(Player otherPlayer) {
		int temp = 0;
		if(score > otherPlayer.score) {
			temp = 1;
		}
		else if(score == otherPlayer.score) {
			temp = 0;
		}
		else if(score < otherPlayer.score) {
			temp = -1;
		}
		
		return temp;
	}
}

//--------------------------------------------- EscapeRoom Class***
class EscapeRoom {
	//------------------------- Hash method - EscapeRoom Class
	private int hash(String key) {
		int hash = 0;
		
		for (int i = 0; i < key.length(); i++) {
			hash += key.charAt(i);
			hash += (hash << 10);
			hash ^= (hash >> 6);
		}
		
		hash += (hash << 3);
		hash ^= (hash >> 11);
		hash += (hash << 15);

		return Math.abs(hash);		
	}
	
	//-------------------------tryToEscape method - EscapeRoom Class
	public int tryToEscape(String playerName, int playerRank) {
		return hash(playerName + Integer.toString(playerRank)) % (101);
	}
}

//--------------------------------------------- EscapeGame Class***
class EscapeGame {
	private Queue<Player> waitingQ;
	private PriorityQueue resultsQ;
	private EscapeRoom escRm;
	
	//------------------------- Constructor - EscapeGame class
	public EscapeGame() {
		waitingQ = new LinkedList<>();
		resultsQ = new PriorityQueue();
		escRm = new EscapeRoom();
	}
	///------------------------- Getters and Setters
	public boolean isWaitingQueueEmpty() {
		return waitingQ.isEmpty();
	}
	public void addPlayerToWaitingQueue(Player player) {
		waitingQ.offer(player);
	}
	public Player removePlayerFromWaitingQueue() {
		return waitingQ.remove();
	}
	
	public boolean isResultsQueueEmpty() {
		return resultsQ.isEmpty();
	}
	public void addPlayertoResultsQueue(Player player) {
		resultsQ.offer(player);
	}
	public Player removePlayerFromResultsQueue() {
		return resultsQ.remove();
	}
	public Player peekResultsQueue() {
		return resultsQ.peek();
	}
	
	//------------------------- TryToEscape method - EscapeGame class
	public int tryToEscape(String playerName, int playerRank) {
		return escRm.tryToEscape(playerName, playerRank);
	}
}

//--------------------------------------------- EscapeGameController Class***
class EscapeGameController {
	
	//-------------------------MovePlayerIntoEscapeGame method -EscGmCont Class
	public void movePlayerIntoEscapeGame(Player[] seats, EscapeGame escGm) {
		System.out.println("Controller: Moving players from outside seats into Escape Room");
		System.out.println("---------------------------------------------------");
		for(int x=0; x<seats.length; x++) {
			if(seats[x] != null) {
				escGm.addPlayerToWaitingQueue(seats[x]);
				System.out.println("Moved to escape game: " + seats[x].getName() + " from outside seat " + x);
			}
		}
	}
	
	//------------------------- SimulateGame method - EscGmCont Class
	public void simulateGame(EscapeGame escGm) {
		System.out.println("Controller: Simulation Starting -- Moving players waiting in line into Escape Room:");
		System.out.println("---------------------------------------------------");
		System.out.printf("%-8s%-8s%s\n", "Player", "Score", "Current Leader");
		System.out.println("---------------------------------------------------");
		Player topPlayer = null; 
		/*
		 * I realized that i built the peek() method specifically for this after I finished the program,
		 * but I figured it wouldn't be bad to leave in a different way of doing the same thing.
		 */
		
		while(!escGm.isWaitingQueueEmpty()) {
			Player temp = escGm.removePlayerFromWaitingQueue();
			temp.setScore(escGm.tryToEscape(temp.getName(), temp.getRank()));
			escGm.addPlayertoResultsQueue(temp);
			
			if((topPlayer == null) || (topPlayer.getScore() < temp.getScore())) {
				topPlayer = temp;
			}
			
			System.out.printf("%-8s%-6d%s\n", temp.getName(), temp.getScore(), topPlayer.getName());
		}
	}
	
	//------------------------- DisplayResults method - EscGmCont Class
	public void displayResults(EscapeGame escGm) {
		System.out.println("Controller: Escape Room Results");
		System.out.println("---------------------------------------------------");
		System.out.printf("%-8s%s\n", "Player", "Score");
		System.out.println("---------------------------------------------------");
		while(!escGm.isResultsQueueEmpty()) {
			Player temp = escGm.removePlayerFromResultsQueue();
			System.out.printf("%-8s%d\n", temp.getName(), temp.getScore());
		}
	}
}

//--------------------------------------------- Built PriorityQueue Class***
class PriorityQueue {
	private Player[] list;
	private int numPlayers;
	
	//------------------------- Constructor - PriorityQueue Class
	public PriorityQueue() { 
		list = new Player[30];
		numPlayers = 0;
	}
	//------------------------- isEmpty method - PriorityQueue Class
	public boolean isEmpty() {
		boolean result = true;
		for(int x=0; x<list.length; x++) {
			if(list[x] != null) {
				result = false;
			}
		}
		return result;
	}
	
	//------------------------- Peek method - PriorityQueue Class
	public Player peek() {
		if(numPlayers < 0) {
			return list[numPlayers-1];
		}
		return null;
	}
	
	public boolean offer(Player player) {
		if(numPlayers >= list.length-1) {
			return false;
		}
		list[numPlayers] = player;
		numPlayers++;
		selectionSort(list, numPlayers);
		return true;
	}
	
	//------------------------- Eemove method - PriorityQueue Class
	public Player remove() {
		if(numPlayers < 1) {
			return null;
		}
		Player temp = list[numPlayers-1];
		list[numPlayers-1] = null;
		numPlayers--;
		return temp;
	}
	
	//-------------------------SelectionSort method - PriorityQueue Class
	private void selectionSort(Player[] list, int numPlayers) {
		for(int x=0; x<numPlayers-1; x++) {
			Player smallest = list[x];
			int smallestIndex = x;
			for(int y=x+1; y<numPlayers; y++) {
				if(smallest.compareTo(list[y]) >= 1) {
					smallest=list[y];
					smallestIndex = y;
				}
			}
			
			if(smallestIndex != x) {
				list[smallestIndex] = list[x];
				list[x] = smallest;
			}
		}
	}
}