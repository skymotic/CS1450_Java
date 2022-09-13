import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
 * Name:			Luke O'Brien
 * Class Name:		Data Structure and Algorithms
 * Section:			002
 * Assignment #:	2
 * Due Date:		Feb 5, 2020
 * 
 * General Description:
 * 
 * The code bellow takes the file named "trains.txt" and parses the data by line.
 * Once parsed, the code then stores the data into a polymorphic array depending on train type.
 * After all the data from the file is stored in the array, it is printed out to the user.
 */


public class OBrienLukeAssignment2 
{
	//------------------------------------------------------------------------------ Main
	public static void main(String[] args)
	{
		int arraySize = 0; //tells the program how many objects there are going to be
		
		try
		{
			FileReader trainFiles = new FileReader("trains.txt");
			Scanner parser = new Scanner(trainFiles);
			
			if(parser.hasNextInt())
				arraySize = parser.nextInt();
			parser.nextLine();
			
			Trains[] train = new Trains[arraySize]; //creates the Object array
			
			//Small visual orginizer
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.printf("%-20s%-15s%-30s%s\n", "Type", "Speed", "Name", "Benefits");
			System.out.println("-------------------------------------------------------------------------------------------");
			
			for(int x=0; x<train.length; x++) //runs though the file by line. For statement used to create new object in array at same time
			{
				String type = parser.next();
				double speed = Double.parseDouble(parser.next());
				String name = parser.nextLine();
				
				//-------------------------- If Blocks, used to know what object to put the data into
				if(type.toLowerCase().equals("highspeed"))
					train[x] = new HighSpeedTrain(type,speed,name);
				if(type.toLowerCase().equals("monorail"))
					train[x] = new MonorailTrain(type,speed,name);
				if(type.toLowerCase().equals("lightrail"))
					train[x] = new LightrailTrain(type,speed,name);
				if(type.toLowerCase().equals("cog"))
					train[x] = new CogTrain(type,speed,name);
				//--------------------------
			 }
			parser.close();
			
			//-------------------------- The printer
			for(int x=0; x<train.length; x++)
			{
				System.out.printf("%-20s%-15.2f%-30s", train[x].getType(), train[x].getMaxSpeed(), train[x].getName());
				System.out.println(train[x].benifits());
			}
			//--------------------------
			
		}
		catch(FileNotFoundException e) //If the file the program asked for is not to be found, it tells you
		{
			System.out.println("-----\nFile Not Found!!!\n-----");
		}
	}
}
//------------------------------------------------Trains (Parent Class)
abstract class Trains
{
	private String type;
	private double maxSpeed;
	private String name;
	
	public Trains() {
		//Default Constructor
	}
	
	public Trains(String type, double maxSpeed, String name)
	{
		this.type = type;
		this.maxSpeed = maxSpeed;
		this.name = name;
	}
	
	//----------Getters
	
	public String getType()
	{
		return type;
	}
	public double getMaxSpeed()
	{
		return maxSpeed;
	}
	public String getName()
	{
		return name;
	}
	
	//---------- "Policies"
	abstract String benifits();
}

//------------------------------------------------ High-Speed Train

class HighSpeedTrain extends Trains
{
	HighSpeedTrain() {
		//Default Constructor
	}
	
	HighSpeedTrain(String type,double speed, String name)
	{
		super(type,speed,name);
	}
	
	@Override
	String benifits()
	{
		return "Travels at speeds between 125 and 267 mph";
	}
}
//------------------------------------------------ Monorail Train
class MonorailTrain extends Trains
{
	MonorailTrain(){
		//Default Constructor
	}
	
	MonorailTrain(String type, double speed, String name)
	{
		super(type,speed,name);
	}
	
	@Override
	String benifits()
	{
		return "Minimal footprint and quieter";
	}
}
//------------------------------------------------ Lightrail Train
class LightrailTrain extends Trains
{
	LightrailTrain(){
		//Default Constructor
	}
	
	LightrailTrain(String type, double speed, String name)
	{
		super(type,speed,name);
	}
	
	@Override
	String benifits()
	{
		return "Tighter turning radius";
	}
}
//------------------------------------------------ Cog Train
class CogTrain extends Trains
{
	CogTrain(){
		//Default Constructor
	}
	
	CogTrain(String type, double speed, String name)
	{
		super(type,speed,name);
	}
	
	@Override
	String benifits()
	{
		return "Can climb grades up to 48%";
	}
}