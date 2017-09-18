package worldPopulation.Lab1;

public class BubbleSort implements SortStrategy {

	private long sortTime = 0;
	@Override
	public long[] sort(long[] data) {
		long[] ar = data.clone();
		long startTime = System.currentTimeMillis();
		for (int i = (ar.length - 1); i >= 0; i--)
		   {
		      for (int j = 1; j < i; j++)
		      {
		         if (ar[j-1] > ar[j])
		         {
		              long temp = ar[j-1];
		              ar[j-1] = ar[j];
		              ar[j] = temp;
		         } 
		       } 
		    }
		long endTime = System.currentTimeMillis();
		this.sortTime = endTime - startTime;
		return ar;
	}

	@Override
	public String getName() {
		return "Bubble    Sort";
	}

	
	@Override
	public long getTime() {
		return this.sortTime;
	}

}
