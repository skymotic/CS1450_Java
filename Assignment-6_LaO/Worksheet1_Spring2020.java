// CS1450 Assignment 6 Spring 2020
// Worksheet #1: Stacks



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Worksheet1_Spring2020 {

	public static void main(String[] args) throws IOException {
		
		// Name of file to read from
		final String BOOKS_FILE_NAME = "Worksheet1Books.txt";	

		// Setup a file reference variable to refer to text file
		File booksFileName = new File(BOOKS_FILE_NAME);

		// First value in file is the number of books in the file
		Scanner booksFile = new Scanner (booksFileName);
		int numBooksInFile = booksFile.nextInt();

		//****************************************
		// Worksheet1 Question #2: 
		// Write code to create a stack of books
		//*****************************************
		// ADD YOUR CODE HERE
		
		
		
		// Create book objects from the details provided in the file
		for(int i = 0; i < numBooksInFile; i++) {
			
			// Read information from file and create a book
			String name = (booksFile.nextLine()).trim();

			BookW1 book = new BookW1(name);		

			// Print the books's details
			System.out.println(book.print());
	
			//****************************************
			// Worksheet1 Question #3: 
			// Write code to add the book to the stack
			//****************************************
			// ADD YOUR CODE HERE


		
		} // for each book
		
		
		//****************************************
		// Worksheet1 Question #4
		// Write code to print the name of each book on the stack.  
		// Use a while loop to perform this task.
		//****************************************
		// ADD YOUR CODE HERE
		System.out.println();
		System.out.println();
		
		
		
		
		
		
		//****************************************
		// Worksheet1 Question #5
		// Write code to examine the top element on the stack  
		//****************************************
		// ADD YOUR CODE HERE
		System.out.println();
		System.out.println();
		
		
			
		booksFile.close();
		
	} // main

} // Worksheet1


// Class representing the generic stack - named it GenericStackSp20 to avoid name clashing issues
class GenericStackSp20<E>  {

	private ArrayList<E> list;		// Storage for stack is provided by an ArrayList
	
	public GenericStackSp20() {
		list = new ArrayList<>();
	}
	
	public boolean isEmpty (){
		return list.isEmpty();
	}
	
	public int getSize(){
		return list.size();
	}

	// Returns the top element on the top without removing it
	// Since using array list to store elements, this means getting the last element in the array
	public E peek(){
		return list.get(getSize() - 1);
	}
	
	// Removed and returns the top element on the stack 
	// Since using array list to store elements, this means removing the last element in the array
	public E pop(){		
		E value = list.get(getSize()-1);
		list.remove(getSize() - 1);
		return value;
	}

	// Place a new element on the top of the stack
	// Since using array list to store elements, this means adding the element to the array
	public void push(E value){
		list.add(value); 
	}
	
} // GenericStackSp20



class BookW1 {

	private String name;

	public BookW1 (String name) {
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

