/*this code is from http://www.sanfoundry.com/java-program-implement-avl-tree/
 *Modified to fit the Strings and count the number of comparisons 
 * 
 * 
 */




/*
 *  Java Program to Implement AVL Tree
 */
 
 import java.util.ArrayList;
import java.util.Scanner;
 
 /* Class AVLNode */
 class AVLNode
 {    
     AVLNode left, right;
     String key;
     int count;
     int height;
     
 
     /* Constructor */
     public AVLNode()
     {
         left = null;
         right = null;
         count = 0;
         key = "";
         height = 0;
     }
     /* Constructor */
     public AVLNode(String n)
     {
         left = null;
         right = null;
         key = n;
         count = 1;
         height = 0;
     }     
 }
 
 /* Class AVLTree */
public class AVLTree
 {
     private AVLNode root;
     private int numComparisons;//Added to count the number of comparisons
     private ArrayList<Pair> allElements;
     /* Constructor */
     public AVLTree()
     {
         root = null;
         numComparisons = 0;
         allElements = new ArrayList<Pair>();
     }
     /* Function to check if tree is empty */
     public boolean isEmpty()
     {
         return root == null;
     }
     /* function to get the number of comparisons */
     public int getCount()
     {
    	 return numComparisons;
     }
     /* Make the tree logically empty */
     public void makeEmpty()
     {
         root = null;
         numComparisons = 0;
     }
     /* Function to insert data */
     public void insert(String data)
     {
         root = insert(data, root);
     }
     /* Function to get height of node */
     private int height(AVLNode t )
     {
         return t == null ? -1 : t.height;
     }
     /* Function to max of left/right node */
     private int max(int lhs, int rhs)
     {
         return lhs > rhs ? lhs : rhs;
     }
     /* Function to insert data recursively */
     private AVLNode insert(String x, AVLNode t)
     {
    	 if (t == null)
    	 {
    		 
             t = new AVLNode(x);
             //System.out.print(t.key+" : "+ t.count);
    	 }
             
    	 else
    	 {
    		 int c = (x.compareTo(t.key));
    		 numComparisons++;
	          if (c< 0)
	         {
	        	 
	             t.left = insert( x, t.left );
	             if( height( t.left ) - height( t.right ) == 2 )
	             {
	            	 numComparisons++;
	                 if( x.compareTo(t.left.key)<0)
	                     t = rotateWithLeftChild( t );
	                 else
	                     t = doubleWithLeftChild( t );
	             }
	         }
	         else if((c> 0 ))
	         {
	             t.right = insert( x, t.right );
	             if( height( t.right ) - height( t.left ) == 2 )
	             {
	            	 numComparisons++;
	                 if(x.compareTo(t.right.key)>0)
	                     t = rotateWithRightChild( t );
	                 else
	                     t = doubleWithRightChild( t );
	             }
	         }
	         else
	         {
	        	 t.count++;
	         }
	           ;  // Duplicate; do nothing

    	 }
         t.height = max( height( t.left ), height( t.right ) ) + 1;
         return t;
     }
     /* Rotate binary tree node with left child */     
     private AVLNode rotateWithLeftChild(AVLNode k2)
     {
         AVLNode k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
         k1.height = max( height( k1.left ), k2.height ) + 1;
         return k1;
     }
 
     /* Rotate binary tree node with right child */
     private AVLNode rotateWithRightChild(AVLNode k1)
     {
         AVLNode k2 = k1.right;
         k1.right = k2.left;
         k2.left = k1;
         k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
         k2.height = max( height( k2.right ), k1.height ) + 1;
         return k2;
     }
     /**
      * Double rotate binary tree node: first left child
      * with its right child; then node k3 with new left child */
     private AVLNode doubleWithLeftChild(AVLNode k3)
     {
         k3.left = rotateWithRightChild( k3.left );
         return rotateWithLeftChild( k3 );
     }
     /**
      * Double rotate binary tree node: first right child
      * with its left child; then node k1 with new right child */      
     private AVLNode doubleWithRightChild(AVLNode k1)
     {
         k1.right = rotateWithLeftChild( k1.right );
         return rotateWithRightChild( k1 );
     }    
     /* Functions to count number of nodes */
     public int countNodes()
     {
         return countNodes(root);
     }
     private int countNodes(AVLNode r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.left);
             l += countNodes(r.right);
             return l;
         }
     }
     /* Functions to search for an element */
     public boolean search(String val)
     {
         return search(root, val);
     }
     private boolean search(AVLNode r, String val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             String rval = r.key;
             if (val.compareTo(rval) < 0)
                 r = r.left;
             else if (val.compareTo(rval) > 0)
                 r = r.right;
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
     public ArrayList<Pair> inorder()
     {
    	 inorder(root);
    	 return allElements;
     }
     private void inorder(AVLNode n)
     {
    	 if(n!= null)
    	 {
    		 inorder(n.left);
    		 //System.out.print(n.key+" : "+ n.count);
    		 allElements.add(new Pair(n.key,n.count));
    		 inorder(n.right);
    	 }
     }
 
 }

 