//Author: Yifan Li
//A simple Hash table;
import java.util.ArrayList;
public class HashTable {
	private HashNode[] table;
	private int size;
	private int numComparisons;
	public HashTable(int size)
	{
		this.size = size;
		table = new HashNode[size];
		numComparisons = 0;
	}
	
	//The new version to add a String, sadly it does not improved the efficiency
	public void add(String s)
	{
		int index =Math.abs(s.hashCode())%size;
		
		HashNode current = table[index];
		HashNode previous = null;
		while(current != null)
		{
			
			numComparisons++;
			if(current.getKey().equals(s))
			{
				current.incCount();
				break;
			}
			else
			{
				previous = current;
				current=current.getNext();
			}
		}
		
		if(previous == null)
		{
			table[index] = new HashNode(s);
		}
		else
		{
			previous.setNext(new HashNode(s));
		}

	}
	
	//The old version to add a string in the hash table
	public void add_old(String s)
	{
		int index =Math.abs(s.hashCode())%size;
		if (table[index]==null)
		{
			table[index] = new HashNode(s);
		}
		else
		{
			HashNode current = table[index];
			numComparisons++;
			while(!current.getKey().equals(s))
			{
				if(current.getNext()==null)
				{
					current.setNext(new HashNode(s));
					return;
				}
				current = current.getNext();
				numComparisons++;
			}
			current.incCount();
			
		}
	}
	//return the number of frequency if the String is in the hashTable
	public int get(String key)
	{
		int index = Math.abs(key.hashCode())%size;
		HashNode current = table[index];
		while(!current.getKey().equals(key))
		{
			if(current.getNext()==null)
			{
				return -1;
			}
			current = current.getNext();
			
		}
		return current.getCount();
	}
	public int getCount()
	{
		return numComparisons;
	}
	
	//return all elements as Pair objects in an ArrayList
	public ArrayList<Pair> getAllElements()
	{
		ArrayList<Pair> toReturn = new ArrayList<Pair>();
		HashNode current;
		for (int i = 0; i < size; i++)
		{
			if(table[i]!=null)
			{
				current = table[i];
				toReturn.add(new Pair(current.getKey(),current.getCount()));
				while(current.getNext()!=null)
				{
					current=current.getNext();
					toReturn.add(new Pair(current.getKey(),current.getCount()));
				}
			}
		}
		return toReturn;
	}
	//toString function
	public String toString()
	{
		String toReturn = "";
		HashNode current;
		for (int i = 0; i < size; i++)
		{
			if(table[i]!=null)
			{
				current = table[i];
				toReturn = toReturn +"  "+ current.getKey()+"  "+current.getCount()+" \n";
				while(current.getNext()!=null)
				{
					current=current.getNext();
					toReturn = toReturn +"  "+ current.getKey()+"  "+current.getCount()+" \n";
				}
			}
		}
		return toReturn;
	}
	

}
//HashNode For HashTable
class HashNode {
	private String key;
	private int count;
	private HashNode next;
	
	public HashNode(String key)
	{
		this.key = key;
		this.count = 1;
		next = null;
	}
	public String getKey()
	{
		return key;
	}
	public int getCount()
	{
		return count;
	}
	public void incCount()
	{
		count++;
	}
	public void setNext(HashNode n)
	{
		next = n;
	}
	public HashNode getNext()
	{
		return next;
	}
	public boolean equals(HashNode h)
	{
		return h.getKey().equals(this.key);
	}

}
