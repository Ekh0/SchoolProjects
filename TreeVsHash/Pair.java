//Author: Yifan Li
//a class contains both key and its frequency. Implements Comparable to use the built in sort
public class Pair implements Comparable{
	
	private String key;
	private int count;
	//constructor
	public Pair(String k, int c)
	{
		key = k;
		count = c;
	}
	public int getCount()
	{
		return count;
	}
	public String getKey()
	{
		return key;
	}
	public String toString()
	{
		return key + " : " + count;
	}
	
	//Compare the frequency first, if they have same frequency, compare the key
	@Override
	public int compareTo(Object o) {
		int toReturn = count-((Pair)o).getCount();
		if (toReturn ==0)
		{
			toReturn = key.compareTo(((Pair) o).getKey());
		}
		return toReturn;
	}
	

}
