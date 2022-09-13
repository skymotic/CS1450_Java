//----------------------------------- Imported Packages
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

//__________________________________________________________________________
/*
 * Name:			Luke O'Brien
 * Class Name:		Data Structure and CS1450
 * Class Section:	Section 002
 * Assignment #:	4
 * Due Date:		Feb 19, 2020
 * 
 * Description:
 * This program takes in and parses two files. One file contains game board
 * data, while the other contains playing data. The programs reads the game
 * board data and creates a 2D array according to the files specifications.
 * It then goes through and fills that array with Targets based on the file.
 * 
 * The program calls a playGame function that reads through the play.txt file.
 * This file contains point data of where the ball will be. The play method
 * checks to see if the ball hit any targets, and if it does it'll add a hit
 * to the target in the gameBoard array. While doing this, it will print out
 * every thing that it hits plus the total score.
 * 
 * After the game simulation has finished, the program will go on to print
 * out a final game report with all of the targets and their stats. Plus
 * how many times they have been hit
 */
//__________________________________________________________________________

//--------------------------------------------- Main Class
public class OBrienLukeAssignment4 
{
	//------------------------------ Main Method
	public static void main(String[] args) throws IOException 
	{
		//----- Creates all class objects
		Scanner machineTargets = new Scanner(new File("pinballMachineTargets.txt"));
		
		int tempRow = 0;
		int tempColumn = 0;
		tempRow = machineTargets.nextInt();
		tempColumn = machineTargets.nextInt();
		PinballMachine gameBoard = new PinballMachine(tempRow,tempColumn);
		
		//----- Adds all the Targets to the playing field
		while(machineTargets.hasNextLine())
		{
			gameBoard.addTargetToPlayingField(machineTargets.nextInt(), machineTargets.nextInt(), new Target(machineTargets.next(),machineTargets.nextInt(),machineTargets.nextInt()));
		}
		
		//----- Prints out the Game Board
		gameBoard.displayPlayingField();
		
		//----- Closes scanners
		machineTargets.close();
		
		//----- Runs the Game and prints the report
		playGame(gameBoard);
		printReport(gameBoard);
	}
	
	//-------------------- PlayGame Method
	public static void playGame(PinballMachine pinn) throws IOException
	{
		//----- Score keeper
		int scoreKeeper = 0;
		
		//----- Opens the file "Play.txt," Then creates pinball object
		Scanner player = new Scanner(new File("Play.txt"));
		
		//----- Prints out Score headline
		System.out.println("-------------------------------------------------");
		System.out.printf("%30s\n", "Game Simulation");
		System.out.println("-------------------------------------------------");
		System.out.printf("%-15s%-8s%-12s%-10s\n", "Target Hit", "ID", "Points", "Score");
		System.out.println("-------------------------------------------------");
		
		//----- Goes through the File and gives points if object is hit
		while(player.hasNextLine())
		{
			int tempRow = player.nextInt();
			int tempColumn = player.nextInt();
			
			if(pinn.getTarget(tempRow, tempColumn) != null)
			{
				String type = pinn.getTarget(tempRow, tempColumn).getType();
				int id = pinn.getTarget(tempRow, tempColumn).getID();
				int points = pinn.getTarget(tempRow, tempColumn).getPoints();
				
				pinn.getTarget(tempRow, tempColumn).incrementHit();
				scoreKeeper = scoreKeeper + points;
				
				System.out.printf("%-15s%-8d%-12d%-10d\n", type, id, points, scoreKeeper);
			}
		}
		
		//----- Adds a couple lines for PrintReport
		System.out.println("\n\n");
		
		//----- Closes the Scanner
		player.close();
	}
	
	//-------------------- PrintReport Method
	public static void printReport (PinballMachine pinn)
	{
		//----- Creates the array list that stores the target reports
		ArrayList<TargetReport> list = new ArrayList<>();
		
		//----- Prints out a heading for print report
		System.out.println("*******************************************************");
		System.out.printf("%42s\n", "PINBALL MACHINE TARGET HIT REPORT");
		System.out.printf("%40s\n", "(From most hits to least hits)");
		System.out.println("*******************************************************");
		System.out.printf("%-7s%-9s%-15s%-9s%-8s%s\n", "Row", "Column", "Type", "Number", "Points", "hits");
		System.out.println("-------------------------------------------------------");
		
		//----- Scans through the playing field and 
		//       puts targets into target report
		for(int x=0; x<pinn.getNumRows(); x++)
		{
			for(int y=0; y<pinn.getNumColumns(); y++)
			{	
				if(pinn.getTarget(x, y) != null)
				{
					String type = pinn.getTarget(x, y).getType();
					int id = pinn.getTarget(x, y).getID();
					int points = pinn.getTarget(x, y).getPoints();
					int hits = pinn.getTarget(x, y).getHits();
					
					list.add(new TargetReport(x, y, type, id, points, hits));
				}
			}
		}
		
		Collections.sort(list);
		
		for(TargetReport x : list)
			System.out.println(x.print());
	}

}

//---------------------------------------- PinballMachine Class
class PinballMachine
{
	private int numRows;
	private int numColumns;
	private Target[][] playingField;
	
	PinballMachine(){
		//Default Constructor
	}
	
	PinballMachine(int numRows, int numColumns)
	{
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.playingField = new Target[numRows][numColumns];
	}
	
	//-------------------- addTargetToPlayingField Method
	void addTargetToPlayingField(int row, int column, Target x)
	{
		playingField[row][column] = x;
	}
	
	//----------------------------------- displayPlayingField Method Star
	void displayPlayingField()
	{
		System.out.println("******************************************************************************");
		System.out.printf("%41s\n", "Game board");
		System.out.println("******************************************************************************\n");
		System.out.printf("%7s", " ");
		for(int col=0; col<getNumColumns(); col++) 
		{
			System.out.printf("%10s%2d","Column:", col);
		}
		System.out.println();
		
		for(int x=0; x<getNumRows(); x++)
		{
			System.out.printf("%s%d","Row: ", x);
			
			for(int y=0; y<getNumColumns(); y++)
			{
				if(getTarget(x,y) != null)
				{
					System.out.printf("%12s" ,getTarget(x,y).getType());
				}
				else
				{
					System.out.printf("%12s", "--------");
				}
			}
			System.out.println();
		}
		System.out.printf("\n\n\n");
	}
	//----------------------------------- displayPlayingField Method END
	
	//-------------------- getNumRows Method
	int getNumRows()
	{
		return numRows;
	}
	
	//-------------------- getNumColloums Method
	int getNumColumns()
	{
		return numColumns;
	}
	
	//-------------------- getTarget Method
	Target getTarget(int row, int column)
	{
		return playingField[row][column];
	}
}

//---------------------------------------- Target Class
class Target
{
	private String type;
	private int id;
	private int points;
	private int hits;
	
	Target() {
		//Default Constructor
	}
	
	Target(String type, int id, int points)
	{
		this.type = type;
		this.id = id;
		this.points = points;
	}
	
	//-------------------- getID Method
	int getID()
	{
		return id;
	}
	
	//-------------------- getType Method
	String getType()
	{
		return type;
	}
	
	//-------------------- getPoints Method
	int getPoints()
	{
		return points;
	}
	
	//-------------------- getHits Method
	int getHits()
	{
		return hits;
	}
	
	//-------------------- incrementHit Method
	void incrementHit()
	{
		hits++;
	}
}

//---------------------------------------- Target report Class
class TargetReport implements Comparable<TargetReport>
{
	private int rowNum;
	private int columnNum;
	private String type;
	private int id;
	private int points;
	private int hits;
	
	TargetReport() {
		//Default Constructor
	}
	
	TargetReport(int rowNum, int columnNum, String type, int id, int points, int hits)
	{
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		this.type = type;
		this.id = id;
		this.points = points;
		this.hits = hits;
	}
	
	//-------------------- Print Method
	public String print()
	{
		return String.format("%d\t%d\t%-15s%d\t%d\t%d", rowNum, columnNum, type, id, points, hits);
	}
	
	//-------------------- CompairTo Method
	@Override
	public int compareTo(TargetReport other)
	{
		return Integer.compare(other.hits, this.hits);
	}
}