import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Name: Luke O'Brien
 * class: CS1450:002
 * Assignment :1
 * Due Date: 1/29/2019
 * 
 * General Desc:
 * 
 */

public class OBrienLukeAssignment1 
{
	public static void main(String[] args)
	{
		//--------------------------------- Task 1 | Creating the First Two arrays, Then printing them
		Random rand = new Random();
		
		int randSize = 11;
		int numSize = 26;
		
		int size1 = rand.nextInt(randSize);
		int size2 = rand.nextInt(randSize);
		
		int arrayOne[] = new int[size1];
		int arrayTwo[] = new int[size2];
		int sortedArray[] = new int[size1+size2];
		
		for(int countOne=0; countOne<arrayOne.length; countOne++)
			arrayOne[countOne] = rand.nextInt(numSize);
		
		for(int countTwo=0; countTwo<arrayTwo.length; countTwo++)
			arrayTwo[countTwo] = rand.nextInt(numSize);
		
		Arrays.sort(arrayOne);
		Arrays.sort(arrayTwo);
		
		System.out.println("First Array with " + size1 + " sorted random values\n----------");
		for(int x : arrayOne)
			System.out.println(x);
		System.out.println("\n\nSecond Array with " + size2 + " sorted random values\n----------");
		for(int y : arrayTwo)
			System.out.println(y);
		
		//--------------------------------- Task 2 | Creating and Writing the File
		try
		{
			File fileCreater = new File("Assignment1.txt");
			
			if(!fileCreater.exists())
				fileCreater.createNewFile();
			
			System.out.println("\n----------\nThe File was made");
			
			PrintWriter fw = new PrintWriter("Assignment1.txt");
			
			//--- Count not figure out how to write the file pre-sorted without a third array
			for(int x=0; x<arrayOne.length; x++)
				sortedArray[x] = arrayOne[x];
			for(int x=0; x<arrayTwo.length; x++)
				sortedArray[x+arrayOne.length] = arrayTwo[x];
			
			Arrays.sort(sortedArray);
			//---
			
			for(int x : sortedArray)
				fw.println(x);
			
			fw.close();
			
			System.out.println("\n----------\nThe File data was input");		
		}
		catch (IOException e)
		{
			System.out.println("BIG Oof");
		}
		
		//--------------------------------- Task 3 |Reading the File and printing it
		File scan = new File("Assignment1.txt");
		try 
		{
			Scanner reader = new Scanner(scan);
			
			int tempA = reader.nextInt();
			int tempB = tempA;
			while(reader.hasNextInt())
			{
				tempA = reader.nextInt();
				if(tempB == tempA)
					System.out.println(tempB);
				else
				{
					System.out.println(tempB + "\n" + tempA);
				}
			}
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
			
		
	}
}
