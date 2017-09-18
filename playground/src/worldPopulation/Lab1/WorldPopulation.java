package worldPopulation.Lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.StringTokenizer;

public class WorldPopulation {

	SortStrategy sortStrategy;
	long[] population = new long[13484]; // Cheating because we know number of records!!
	long[] sortedPopulation;
	// Lab Exercise:  After creating some strategy classes -- set the default strategy here.
	public WorldPopulation(){
		sortStrategy = new InsertionSort(); // Set the default strategy here.	
	}
	
	public void readInputFile(){
		population = readPopulationFile("src/WorldPopulation.csv");
	}
	
	public void setStrategy(SortStrategy strategy){
		sortStrategy = strategy;
	}
	
	// Lab Exercise:  Read in the WorldPopulation.csv
	// Extract ONLY the numbers and store them into population[]
	public long[] readPopulationFile(String fileName){
		int i = 0;
		
		try{
			BufferedReader input= new BufferedReader(new FileReader(fileName));
			
			 String line = null;
			 
			 while ( (line = input.readLine()) != null ){				 
				 String regex = ",";
				 String[] split = line.split(regex);
				 
				 population[i++] = Long.parseLong(split[2]);
				 
			 }
			 
			 
			 input.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return population;
	}
	
	// Lab Exercise.  Complete this method.
	// Delegate sorting to the strategy object
	public void sortPopulation(){
		this.sortedPopulation = this.sortStrategy.sort(population);
	}
	
	public void computeTotalPopulation(){
		long total = 0;
		
		for (long i : population){
			total+=i;
		}
		System.out.println("total is "+total);
	}
	
	// Experiment with various strategies.
	// Create 3 strategies -- Bubble, insertion, and selection sort.
	public static void main(String[] args) {
		WorldPopulation worldPopulation = new WorldPopulation();
		worldPopulation.readInputFile();
		worldPopulation.computeTotalPopulation();
		
		worldPopulation.setStrategy(new SelectionSort());
		worldPopulation.sortPopulation();	
		System.out.println("Using "+worldPopulation.sortStrategy.getName()+", the sorting took "+worldPopulation.sortStrategy.getTime()+"m");
		
		worldPopulation.setStrategy(new InsertionSort());
		worldPopulation.sortPopulation();	
		System.out.println("Using "+worldPopulation.sortStrategy.getName()+", the sorting took "+worldPopulation.sortStrategy.getTime()+"m");
		
		worldPopulation.setStrategy(new BubbleSort());
		worldPopulation.sortPopulation();	
		System.out.println("Using "+worldPopulation.sortStrategy.getName()+", the sorting took "+worldPopulation.sortStrategy.getTime()+"m");
		
		
		
	}

}
