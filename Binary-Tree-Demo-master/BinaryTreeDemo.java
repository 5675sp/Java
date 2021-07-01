/**
 * This program using a Binary Sort Tree to store strings in alphabetical order.
 * It also demonstrates tree traversal using recursion, stacks, and queues.
 *@author Sergio Perez 
 *@version 1.0
 */
public class BinaryTreeDemo {
	
	public static void main(String[] args) {
		
		// Make a BST containing 31 randomly selected words.
		WordList words = new WordList();
		root = null; // Start again with an empty tree.
		for (int i = 0; i < 31; i++) {
			int index = (int)(Math.random()*words.size());
			bstInsert(words.get(index));
		}
		treeDemo(root,31);
		
		// Make a BST with 7 known words.  This tree is balanced.
		root = null;  // Start again with an empty tree.
		bstInsert("Fred");
		bstInsert("Moe");
		bstInsert("Larry");
		bstInsert("Betty");
		bstInsert("Curley");
		bstInsert("Barney");
		bstInsert("Wilma");
		treeDemo(root,7);
		
		// Make a BST with 3 known words.  This tree is NOT balanced.
		root = null;
		bstInsert("and");
		bstInsert("but");
		bstInsert("or");
		treeDemo(root,3);
		
	} // end main()
	
	
	/**
	 *A subroutine that counts the number
	 *of nodes in the left and right subtree of the tree.
	 *@param tree takes a TreenNode.
	 *
	 */
	static int countNodes(TreeNode tree) {
		
		if(tree == null){ // if the tree is empty than theirs nothing to count
			
			return 0; //tell the user that their nothing on the tree
			
		}else {
			
			return countNodes(tree.left) + countNodes(tree.right) + 1; // the sum of all the nodes
			
		}
	}
	
	/**
	 *A subroutine that counts the leaves on the tree.
	 *@param tree takes a parameter of type treenode.
	 *
	 */
	static int countLeaves(TreeNode tree){
		
		if(tree == null){ // if the tree is empty than return  a 0
			return 0;
		}
		if(tree.left == null && tree.right == null){	// if their somehting to count in subtrees
			return 1; /// return a value 1 
		}
		return countLeaves(tree.left) + countLeaves(tree.right); // add the leaves up 
	}
	
	
	/**
	 * A subroutine that counts all the vowels in a specific word
	 *@param tree takes a data type of treenode.
	 *@param vowel takes a character as a type.
	 *
	 */
	static int countVowels(TreeNode tree, char vowel){
		
		int R,L,Count = 0; // will store the value and position on a letter

		if(tree == null){ // if the tree is empty than retrun 0 
			return 0;
		}
		
		for(int i = 0; i < tree.item.length(); i++){  // go through the whole word 

			if( tree.item.charAt(i) == vowel){ // identifies the vowels 
				Count++; // the usm goes up for a specific vowel 
			}
		}

		L = countVowels(tree.left, vowel);  //counts the left vowels of all the words on the left
		R = countVowels(tree.right, vowel); ////counts the left vowels of all the words on the right
		
		return   L + R + Count; // return the amount of vowels for a certain word 
		
	}
	
	/**
	 *A subroutine that creates the output using a stack in pre-order order:
	 *@param tree that takes a treenode as a parameter
	 *
	 */
	
	public static void stac(TreeNode tree){

		Stack<TreeNode> TraversalStack = new Stack<TreeNode>(); // creates the strack on treenode

		TraversalStack.push(tree);

		while(!TraversalStack.isEmpty()){

			tree = TraversalStack.pop();

				if(!(tree == null)){
					System.out.println(tree.item ); // prints the item first 
					TraversalStack.push(tree.right);// looks for a right node
					TraversalStack.push(tree.left); // looks for a left node
				}
		}
	}
	
	
	/**
	 * A subroutine that creates the output using a queue in breadth-first order:
	 *@param tree takes a TreeNode as a param.
	 *
	 */
	
	public static void queve(TreeNode tree) {
		
		Queue<TreeNode> q = new Queue<TreeNode>();
		q.enqueue(tree);
	
		while(!q.isEmpty()){ // if only is empty 

			tree = q.dequeue();

			if(!(tree == null)){
				q.enqueue(tree.left); // looks for a left node first 
				System.out.println(tree.item); // if no left than the item is process
				q.enqueue(tree.right); // if no right than the item is process
			}

		}
	}
	
	
	/**
	 *A subroutine that creates an output using recursion in post-order order:
	 *@param tree takes a TreeNode as a param.
	 *
	 */
	public static void poTraversal(TreeNode tree){

		if(!(tree == null)){ // if the tree is not empty than to the above 
			poTraversal(tree.left); // looks for a left first 
			poTraversal(tree.right); // than looks for a rgiht 
			System.out.println(tree.item); // the item if finally process 
		}

	}
	
	//----------------------------------------------------------------
	
	/**
	 * Performs some manipulations on a binary sort tree.
	 * @param root The root of the tree.
	 * @param itemCount The number of nodes in the tree, which must be one less than
	 *    a power of two, for example, 3, 7, 15, 31, 63, 127, ...
	 */
	private static void treeDemo(TreeNode root, int itemCount) {
		System.out.println("\n\n----------------------------------------------------------------");
		System.out.println("    Working with a tree with " + itemCount + " nodes.");
		System.out.println("----------------------------------------------------------------");
		
		System.out.println("\nA perfectly balanced tree would have " + (itemCount+1)/2 + " leaves, with");
		System.out.println(itemCount/2 + " nodes in the left subtree and " + itemCount/2 + " in the right.");
		System.out.println("In this tree:");
		
		System.out.println("     The number of leaves in the tree is: " + countLeaves(root));
		System.out.println("     The number of nodes in the left subtree is:" + countNodes(root.left) );
		System.out.println("     The number of nodes in the right subtree is: " + countNodes(root.right));
		
		System.out.println();
		char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
		for (int i = 0; i < vowels.length; i++) {
			char ch = vowels[i];
			System.out.println("The number of " + ch + "'s in the tree is:  " + countVowels(root,vowels[i]));
		}
		
		System.out.println("\n\n" + itemCount + " words output using recursion in alphabetical order:");
		bstPrint(root);
		
		System.out.println("\n\n" + itemCount + " words output using recursion in post-order order:");
		poTraversal(root);

		System.out.println("\n\n" + itemCount + " words output using a queue in breadth-first order:");
		queve(root);

		System.out.println("\n\n" + itemCount + " words output using a stack in pre-order order:");
		stac(root);
	}
	
	//-----------------------------------------------------------------

	/**
	 * A node class used for build a binary tree of Strings, 
	 * which will be used as a binary sort tree.
	 */
	private static class TreeNode {
		String item;
		TreeNode left,right;
		TreeNode(String item) {
			   // Construct a node containing specified item, with
			   // the default value (null) for left and right.
			this.item = item;
		}
	}
	
	private static TreeNode root = null; // Start with empty tree.
	
	/**
	 * Add item to the tree.  The tree is a binary sort tree,
	 * and the item is added in its correct sorted order.
	 * Comparisons are not case sensitive.  Duplicates 
	 * in the tree are allowed.  Null items are not allowed.
	 */
	public static void bstInsert( String item ) {
		if (item == null) {
			throw new IllegalArgumentException("Can't put null in tree.");
		}
		if (root == null) {
			root = new TreeNode(item);
		}
		else {
			TreeNode runner = root;
			while (true) {
				if (item.compareToIgnoreCase(runner.item) < 0) {
					if (runner.left == null) {
						runner.left = new TreeNode(item);
						break;
					}
					else
						runner = runner.left;
				}
				else {
					if (runner.right == null) {
						runner.right = new TreeNode(item);
						break;
					}
					else
						runner = runner.right;
				}
			}
		}
	} // end treeInsert
	
	
	/**
	 * Print items from the tree by doing an inorder traversal.  Since
	 * the tree is a binary sort tree, items will be in alphabetical order.
	 * (Private recursive helper function for bstPrint().)
	 */
	private static void bstPrint( TreeNode tree ) {
		if (tree != null) {
			bstPrint(tree.left);
			System.out.println("   " + tree.item);
			bstPrint(tree.right);
		}
	}

}
