import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Name:		Luke O'Brien
 * Class:		Data Structure and Algorithms
 * Section:		002
 * Assignment:	Assignment-8
 * Due Date:	15 April 2020
 * 
 * Desc:
 * This program takes three files and goes through them, putting
 * the data into four different arrays (one per file). After doing
 * that, the position data in on of the arrays is used in correlation
 * with the letter data it put everything in their proper place with
 * a 2D array. It does this first with the ArrayList, then with the
 * ArrayLists, then with Queues. 
 * After all the data is put into the 2D array stored in the 
 * Crossword class, it is then printed out to the console for the user
 * to see.
 */

//----------------------------------------------------------------Tester Class
public class OBrienLukeAssignment8 {

	
	public static void main(String[] args) throws FileNotFoundException {
		//------------------- Creates Scanners for the different files
		Scanner listLetters = new Scanner(new File("listLetters.txt"));
		Scanner listSlots = new Scanner(new File("listSlots.txt"));
		Scanner qLetters = new Scanner(new File("queueLetters.txt"));
		Scanner qSlots = new Scanner(new File("queueSlots.txt"));
		
		//------------------- Creates 2 Arrays Lists and 2 Queue to store File data
		ArrayList<Character> listLettersArray = new ArrayList<>();
		ArrayList<Slot> listSlotsArray = new ArrayList<>();
		Queue<Character> qLettersQ = new LinkedList<>();
		Queue<Slot> qSlotsQ = new LinkedList<>();
		
		//------------------- Integers to store dimension data
		int numRowList = listSlots.nextInt();
		int numColumnList = listSlots.nextInt();
		int numRowQ = qSlots.nextInt();
		int numColumnQ = qSlots.nextInt();
		
		//------------------- while loops pull data from the files and store them away
		while(listLetters.hasNext()) {
			listLettersArray.add(listLetters.next().charAt(0));
		}
		while(listSlots.hasNextLine()) {
			listSlotsArray.add(new Slot(listSlots.nextInt(),listSlots.nextInt()));
		}
		while(qLetters.hasNext()) {
			qLettersQ.add(qLetters.next().charAt(0));
		}
		while(qSlots.hasNextLine()) {
			qSlotsQ.add(new Slot(qSlots.nextInt(), qSlots.nextInt()));
		}
		
		//-------------------Create the Crossword objects, specifying the dimensions
		Crossword listCross = new Crossword(numRowList, numColumnList);
		Crossword qCross = new Crossword(numRowQ, numColumnQ);
		
		//------------------- Creates an iterator for each ArrayList and Queue
		Iterator<Character> letterIteratorList = listLettersArray.iterator();
		Iterator<Slot> slotIteratorList = listSlotsArray.iterator();
		Iterator<Character> letterIteratorQ = qLettersQ.iterator();
		Iterator<Slot> slotIteratorQ = qSlotsQ.iterator();
		
		//-------------------enters the data into the Crossword object
		listCross.enterLetters(letterIteratorList, slotIteratorList);
		qCross.enterLetters(letterIteratorQ, slotIteratorQ);
		
		//------------------- Prints out all the data from Crosswords
		printHeader(1);
		listCross.printCrossword();
		System.out.println("\n\n");
		printHeader(2);
		qCross.printCrossword();
	}
	
	public static void printHeader(int x) {
		System.out.println("****************************");
		System.out.printf("%s%d\n", "Crossword #", x);
		System.out.println("****************************\n");
	}
}

//---------------------------------------------------------------- Slot Class
class Slot {
	private int row;
	private int column;
	
	//-----------------------------------Constructor
	public Slot(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	//-----------------------------------getRow method, Returns stored Row #
	public int getRow() {
		return row;
	}
	
	//-----------------------------------getColumn method, Returns stored Column #
	public int getColumn() {
		return column;
	}
}

//---------------------------------------------------------------- Crossword CLass
class Crossword{
	private Character[][] array;
	private int numRows;
	private int numColumns;
	private char emptyCharacter = '_';
	
	//-----------------------------------Constructor
	public Crossword(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		array = new Character[numRows][numColumns];
		
		for(int x=0; x<numRows; x++) {
			for(int y=0; y<numColumns; y++) {
				array[x][y] = emptyCharacter;
			}
		}
	}
	//----------------------------------- enterLetters method, Takes in data to enter into 2D array
	public void enterLetters(Iterator<Character> letterIterator, Iterator<Slot> slotIterator) {
		while(letterIterator.hasNext() && slotIterator.hasNext()) {
			Slot temp = slotIterator.next();
			array[temp.getRow()][temp.getColumn()] = letterIterator.next();
		}
	}
	
	//-----------------------------------printCrossword method, Prints out 2D array neatly
	public void printCrossword() {
		for(int x=0; x<numRows; x++) {
			for(int y=0; y<numColumns; y++) {
				System.out.printf("%-2c", array[x][y]);
			}
			System.out.println();
		}
	}
}