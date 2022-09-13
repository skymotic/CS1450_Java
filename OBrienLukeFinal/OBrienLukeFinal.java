import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class OBrienLukeFinal {

	public static void main(String[] args) {
		Queue<Integer> aQueue = new LinkedList<>();
		Stack<Integer> aStack = new Stack<>();
		Node head = null;

		// Example queue and stack to test your code on
		aQueue.offer(2);
		aQueue.offer(-47);
		aQueue.offer(-7);
		aQueue.offer(6);
		aQueue.offer(5);

		aStack.push(-43);
		aStack.push(1);
		aStack.push(4);
		aStack.push(-2);
		aStack.push(3);
		aStack.push(-63);

		// ******* ADD SOLUTION HERE *******
		// Write all code in main - no need to create methods
		// Create your own linked list using the provided head and Node class

		PriorityQueue<Integer> aQ = new PriorityQueue<>();
		while(!aQueue.isEmpty()) {
			int temp = aQueue.remove();
			if(temp >= 0) {
				aQ.offer(temp);
			}
		}
		while(!aStack.isEmpty()) {
			int temp = aStack.pop();
			if(temp >= 0) {
				aQ.offer(temp);
			}
		}
		
		while(!aQ.isEmpty()) {
			aStack.push(aQ.remove());
		}
		
		Node tail = null;
		while(!aStack.isEmpty()) {
			if(tail == null) {
				head = tail = new Node(aStack.pop());
			}
			else {
				tail.next = new Node(aStack.pop());
				tail = tail.next;
			}
		}
		
		
		
		// Print the list
		System.out.println("Reverse Sorted Linked List:");
		Node current = head;
		while (current != null) {
			int data = current.data;
			current = current.next;
			System.out.print(data + "-->");
		}
		System.out.println("Null");

	} // main

	// Inner Node Classes
	private static class Node {
		private int data;
		private Node next;

		public Node(int data) {
			this.data = data;
			next = null;
		}
	} // Node
}
