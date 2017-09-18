package worldPopulation.Lab1;

public class InsertionSort implements SortStrategy {

	private long sortTime = 0;
	@Override
	public long[] sort(long[] data) {
		long[] ar = data.clone();
		long startTime = System.currentTimeMillis();
		
		for (int i=1; i < ar.length; i++)
		   {
		      long index = ar[i]; int j = i;
		      while (j > 0 && ar[j-1] > index)
		      {
		           ar[j] = ar[j-1];
		           j--;
		      }
		      ar[j] = index;
		   }
		
		long endTime = System.currentTimeMillis();
		this.sortTime = endTime - startTime;
		return ar;
	}

	@Override
	public String getName() {
		return "Insertion Sort";
	}

	
	@Override
	public long getTime() {
		return this.sortTime;
	}

}
