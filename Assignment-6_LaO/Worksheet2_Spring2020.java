// CS1450 Assignment 6 Spring 2020
// Worksheet #2: Nested Objects



import java.util.LinkedList;
import java.util.Queue;

public class Worksheet2_Spring2020 {

	public static void main(String[] args) {
		
		// Create some books to be added to the queue
		BookW2 javaBook = new BookW2("Java");
		BookW2 cBook = new BookW2("C Programming");
		BookW2 pythonBook = new BookW2("Python");
		
		//*******************************************
		// Worksheet2 Question #1 
		// Queue in main - no nesting
		// Practice creating a queue here and adding the three books
		// Name your queue - queueNotNested
		//*******************************************
		// ADD CODE HERE

		
		
		
		
		
		// Test your non-nested queue by displaying the values in the queue
		System.out.println("Values in a queue that is not nested in a class");
		int queueLength = queueNotNested.size();
		for (int i = 0; i < queueLength; i++) {
			System.out.println("aQueue[" + i + "] = " + queueNotNested.remove().getName());
		}

		
		//********************************
		// Worksheet2 Question #2
		// See BookQueue Class below
		//********************************

		
		
		//********************************
		// Worksheet2 Question #3a 
		// Use this code to test your BookQueue class
		// Queue is now nested in a class
		// Create an object of type BookQueue and add the three books
		//********************************
		// ADD CODE HERE
		BookQueue aQueue = new BookQueue();

		
		
		
		
		//********************************
		// Worksheet2 Question #3b
		// Test your nested queue by displaying the names of the books in the queue
		//********************************
		// ADD CODE HERE	
		System.out.println();
		System.out.println("Values in a queue nested in a class");

		
		
		
	} // main

} // Worksheet2



//********************************
//Worksheet2 Question #2
//Write the code for each method
//********************************
class BookQueue {
	
	private Queue<BookW2> queue = new LinkedList<>();

	public int size() {
		// ADD CODE HERE
		
		
	}
	
	public void offer(BookW2 book) {
		// ADD CODE HERE
		
		
	}
	
	public BookW2 remove() {
		// ADD CODE HERE
		
		
	}
	
} // BookQueue


class BookW2 {

	private String name;

	public BookW2 (String name) {
		this.name = name;
	}
		
	public String getName() {
		return name;
	}

	// Create and returns a string containing book details 	
	public String print() {
		return String.format("%s", name);
	}
	
} // BookW1


