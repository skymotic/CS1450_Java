import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Name:		Luke O'Brien
 * Class:		Data Structure and Algorithms
 * Section:		002
 * Assignment:	Assignment-9
 * Due Date:	22 April 2020
 * 
 * Desc:
 * This program teaches us about how the data structure LinkedList work.
 * with the given file, The program creates it own linked list. Then after
 * the program puts the elephant data into an elephant type object, it is 
 * then added to the linked list using the add() method.
 * 
 * once added, it prints out the list with all the elephants, then finds
 * the sum of all the elephants weight and prints that out too. Finally,
 * the list find the heaviest elephant a deletes it (While printing it out)
 * till there are no more elephants left in the list
 */

//---------------------------------------------------Tester Class
public class OBrienLukeAssignment9 {

	//--------------------- Main Method, Belongs to Tester Class
	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File("elephants.txt"));
		ElephantLinkedList list = new ElephantLinkedList();
		
		while(fileReader.hasNextLine()) {
			list.add(new Elephant(fileReader.next(), fileReader.nextInt()));
		}
		fileReader.close();
		
		//--------------------- Step 1/3
		System.out.println("Step: 1\n**************");
		list.printList();
		
		//--------------------- Step 2/3
		System.out.println("\n\n");
		System.out.println("Step: 2\n**************");
		System.out.printf("%s%d", "The sum of all the Elephants weight is: ", list.getTotalWeight());
		
		//--------------------- Step 3/3
		System.out.println("\n\n");
		System.out.println("Step: 3\n**************");
		while(!list.isEmpty()) {
			Elephant temp = list.findHeaviest();
			System.out.printf("%s%s%s%d%s\n", "Removing: ", temp.getName(), " weighing in at ", temp.getWeight(), " lbs");
			list.remove(temp);
		}
	}
}

//---------------------------------------------------Elephant Class
class Elephant {
	private String name;
	private int weight;
	
	public Elephant(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	//--------------------- Getters/Setters
	public String getName() {
		return name;
	}
	public int getWeight() {
		return weight;
	}
	
	//--------------------- print Method, Prints out elephants name and weight
	public void print() {
		System.out.printf("%-15s%d\n", name, weight);
	}
}

//---------------------------------------------------ElephantLinkedList Class
class ElephantLinkedList {
	//---------------------------------------------------***INNER*** Node Class
	private static class Node {
		private Elephant data;
		private Node next;
		Node(Elephant data) {
			this.data = data;
			next = null;
		}
	}
	
	private Node head;
	private Node tail;
	
	//--------------------- Constructor
	public ElephantLinkedList() {
		head = null;
		tail = null;
	}
	//--------------------- isEmpty Method, Checks the list to see if its empty
	public boolean isEmpty() {
		return head==null;
	}
	
	//--------------------- getTotalWeight Method, Adds up the sum of all elephants weight in list
	public int getTotalWeight() {
		Node current = head;
		int sum = 0;
		while(current != null) {
			sum = sum + current.data.getWeight();
			current = current.next;
		}
		return sum;
	}
	
	//--------------------- add Method, Adds elephants to the end of the list
	public void add(Elephant data) {
		Node newNode = new Node(data);
		
		if(tail==null) {
			head=tail=newNode;
		}
		
		else {
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	//--------------------- findHeaviest Method, Looks for the heaviest elephant and returns it
	public Elephant findHeaviest() {
		Node top = head;
		Node current = head;
		while(current != null) {
			if(current.data.getWeight() > top.data.getWeight()) {
				top = current;
			}
			current = current.next;
		}
		return top.data;
	}
	
	//---------------------remove Method, Looks for and removes specified Elephant
	public void remove(Elephant data) {
		if(tail == null) {
			System.out.println("**Could not remove**" + data.getName());
		}
		else if(head==tail) {
			head = null;
			tail = null;
		}
		else {
			Node current = head;
			Node previous = head;
			while(current != null) {
				if(head.data == data) {
					head = head.next;
					break;
				}
				else if(current.data == data) {
					previous.next = current.next;
					current = current.next;
					break;
				}
				else {
					if(current == head) {
						current = current.next;
					}
					else {
						current = current.next;
						previous = previous.next;
					}
				}
			}
		}
	}
	
	//---------------------printList Method, Prints out the list and its contents.
	public void printList() {
		Node current = head;
		
		System.out.println("**************************************************************");
		System.out.printf("%-15s%s\n", "Elephant", "Weight");
		System.out.println("**************************************************************");
		
		while(current != null) {
			current.data.print();
			current = current.next;
		}
	}
}