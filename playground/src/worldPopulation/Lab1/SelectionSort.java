package worldPopulation.Lab1;

public class SelectionSort implements SortStrategy  {
	@Override
	public long [] sort(long[] tempAr){
		long [] ar = tempAr.clone();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < ar.length-1; i++)
		{
			int min = i;
			for (int j = i+1; j < ar.length; j++)
				if (ar[j] < ar[min]) min = j;
			long temp = ar[i];
			ar[i] = ar[min];
			ar[min] = temp;
		} 
		long endTime = System.currentTimeMillis();
		this.sortTime = endTime - startTime;
		return ar;
	}

	@Override
	public String getName() {
		return "Selection Sort";
	}

	private long sortTime = 0;
	@Override
	public long getTime() {
		return sortTime;
	}
}
