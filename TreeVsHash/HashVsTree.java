//Author: Yifan Li
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//the main class
public class HashVsTree {
	protected static ArrayList<String> words;
	public static void readfiles(String fileName) throws FileNotFoundException
	{
		words= new ArrayList<String>();
		FileReader reader = new FileReader(fileName);
		Scanner textIn = new Scanner(reader);
		while(textIn.hasNext())
		{
			words.add(textIn.next().toLowerCase());
		}
	}
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{
		readfiles("Computer Science and the Liberal Arts.txt");
		HashTable h = new HashTable(100);
		AVLTree AvlT = new AVLTree();
		BSTree BsT = new BSTree();
		for(int i = 0; i < words.size(); i++)
		{
			String w = words.get(i);
			h.add_old(w);
			AvlT.insert(w);
			BsT.insert(w);
		}
		
		System.out.println(h.getCount());
		System.out.println(AvlT.getCount());
		System.out.println(BsT.getCount());
		ArrayList<Pair> hashElements = h.getAllElements();
		ArrayList<Pair> AvlElements = AvlT.inorder();
		ArrayList<Pair> BsElements = BsT.inorder();
		Collections.sort(hashElements);
		Collections.sort(AvlElements);
		Collections.sort(BsElements);
		Collections.reverse(hashElements);
		Collections.reverse(AvlElements);
		Collections.reverse(BsElements);
		PrintWriter writer1 = new PrintWriter("hash.txt", "UTF-8");
		PrintWriter writer2 = new PrintWriter("AvlTree.txt", "UTF-8");
		PrintWriter writer3 = new PrintWriter("BsTree.txt", "UTF-8");
		for(int i =0; i < hashElements.size();i++)
		{
			writer1.println(hashElements.get(i));
			writer2.println(AvlElements.get(i));
			writer3.println(BsElements.get(i));
		}
		writer1.close();
		writer2.close();
		writer3.close();
		//System.out.println(hashElements);
		//System.out.println(AvlElements);
		//System.out.println(BsElements);
	}

}

