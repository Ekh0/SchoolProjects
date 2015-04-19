//Author: Yifan Li
//A simple Binary Search Tree
import java.util.ArrayList;
public class BSTree 
{
	private BSNode root;
	private int numComparisons;
	private ArrayList<Pair> allElements;
	
	//constructor
	public BSTree()
	{
		root = null;
		numComparisons = 0;
		allElements = new ArrayList<Pair>();
	}
	
	//return the number of key comparisons;
	public int getCount()
	{
		return numComparisons;
	}
	
	//public insert method
	public void insert(String s)
	{
		root = insert(s,root);
	}
	
	//private recursive insert method
	
	private BSNode insert(String s, BSNode n)
	{
		if(n == null)
		{
			n = new BSNode(s);
		}
		else
		{
			int c = s.compareTo(n.key);//key comparison, decide go left child or right child
			numComparisons++;
			if(c<0)
			{
				n.left = insert(s,n.left);
			}
			else if (c>0)
			{
				n.right = insert(s,n.right);
			}
			else//if key matches
			{
				n.count++;//increase the number of frequency.
			}
		}
		return n;
	}
	
	//method returns an ArrayList with the inorder traverse of tree
    public ArrayList<Pair> inorder()
    {
   	 inorder(root);
   	 return allElements;
    }
    private void inorder(BSNode n)
    {
   	 if(n!= null)
   	 {
   		 inorder(n.left);
   		 allElements.add(new Pair(n.key,n.count));
   		 inorder(n.right);
   	 }
    }
}

//Binary Search Node
class BSNode
{
	BSNode left,right;
	String key;
	int count;
	
	public BSNode(String s)
	{
		key = s;
		left = null;
		right = null;
		count = 1;
	}
}