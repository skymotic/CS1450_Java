import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.File;

/*
 * Name:		Luke O'Brien
 * Class:		Data Structure and Algorithms
 * Section:		002
 * Assignment:	Assignment-10
 * Due Date:	29 April 2020
 * 
 * Desc:
 * This Program, after taking in a file, uses the data (Parrots) in the 
 * file and stores it in a self-contained binary tree data structure. Then
 * uses a queue to traverse the tree from left to right, top to bottom
 * (Level Order) and prints it out to form a sentence.
 * 
 * After printing the song-words in a sentence, the program uses a method
 * that traverses and prints out all the leaves on the tree without the 
 * branches
 */

//--------------------------------------- Main Tester class
public class OBrienLukeAssignment10 {

	//---------------------- Main method
	public static void main(String[] args) throws FileNotFoundException {
		
		// -- Takes in file and inserts data into Binary Tree
		Scanner fileReader = new Scanner(new File("parrots.txt"));
		BinaryTree tree = new BinaryTree();
		while(fileReader.hasNextLine()) {
			tree.insert(new Parrot(fileReader.nextInt(), fileReader.next(), fileReader.next()));
			fileReader.nextLine();
		}
		fileReader.close();
		
		// -- Traverses the Array from left to right
		System.out.println("Parrot's Song\n------------------------------------");
		tree.levelOrder();
		
		// -- Prints out all tree leaves
		System.out.println("\n\n\nLeaf Node Parrots\n------------------------------------");
		tree.visitLeaves();
	}

}

//--------------------------------------- Parrot type class
class Parrot implements Comparable<Parrot> {
	
	
	private int id;
	private String name;
	private String songWord;
	
	//---------------------- Constructor -Parrot-
	public Parrot(int id, String name, String songWord) {
		this.id = id;
		this.name = name;
		this.songWord = songWord;
	}
	
	//---------------------- Getter
	public String getName() {
		return name;
	}
	
	//---------------------- Getter
	public String sing() {
		return songWord;
	}
	
	//---------------------- compareTo from Comparable Interface
	@Override
	public int compareTo(Parrot other) {
		return other.id-this.id;
	}
}

//--------------------------------------- BinaryTree DataStructure class
class BinaryTree {
	//------------------------------------------- **Inner** Node class
	class TreeNode {
		private Parrot parrot;
		private TreeNode left;
		private TreeNode right;
		
		//---------------------- Constructor -TreeNode-
		public TreeNode(Parrot parrot) {
			this.parrot = parrot;
			this.left = null;
			this.right = null;
		}
	}
	
	private TreeNode root;
	
	//---------------------- Constructor -BinaryTree-
	public BinaryTree() {
		this.root = null;
	}
	
	//---------------------- Insert method, puts new data on the tree
	public boolean insert(Parrot parrot) {
		if(root == null) {
			root = new TreeNode(parrot);
		}
		else {
			TreeNode parent = null;
			TreeNode current = root;
			
			while(current != null) {
				if(current.parrot.compareTo(parrot) < 0) {
					parent = current;
					current = current.left;
				}
				
				else if(current.parrot.compareTo(parrot) > 0) {
					parent = current;
					current = current.right;
				}
				else {
					return false;
				}
			}
			
			if(parent.parrot.compareTo(parrot) < 0) {
				parent.left = new TreeNode(parrot);
			}
			else {
				parent.right = new TreeNode(parrot);
			}
		}
		return true;
	}
	
	//---------------------- Level Order method, Traverses the tree from left to right, top to bottom
	public void levelOrder() {
		if(root != null) {
			Queue<TreeNode> nodeQ = new LinkedList<>();
			nodeQ.offer(root);
			while(!nodeQ.isEmpty()) {
				TreeNode current = nodeQ.remove();
				if(current.left != null) {
					nodeQ.offer(current.left);
				}
				if(current.right != null) {
					nodeQ.offer(current.right);
				}
				System.out.printf("%s%s", current.parrot.sing(), " ");
			}
		}
	}
	
	//---------------------- Visit Leaves pointer-method 
	public void visitLeaves() {
		visitLeaves(root);
	}
	
	//---------------------- Visit Leaves method, Goes to end of tree (the leaves) and prints out the end node of each branch
	private void visitLeaves(TreeNode root) {
		if(root != null) {
			if(root.left == null && root.right == null) {
				System.out.println(root.parrot.getName());
				return;
			}
			if(root.left != null) {
				visitLeaves(root.left);
			}
			if(root.right != null) {
				visitLeaves(root.right);
			}
		}
	}
}