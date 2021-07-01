/**
 * A program call Simple list.
 * @author Sergio Perez
 * @version 1.0
 * 
 */
import java.util.Scanner;

/**
 * Lets the user enter some words, which are added to a simple linked list. In
 * the second phase, the user enters words and the program looks for the word in
 * the list. If the word is found, then the position of the word in the list is
 * output.
 */
public class SimpleList {

	static Node head = null;

	public static class Node {
		String item;
		Node next;
	}

	/** Add word to the list of words. */
	/**
	 * A method that adds a node
	 * @param add takes a string an add's the string to a new node
	 */
	private static void add(String word) {

		Node newString = new Node();

		newString.item = word;

		newString.next = head;
		head = newString;

	}

	/**
	 * Look for word in the list. If it NOT found, then the return value is -1.
	 * If it is in the list, then the return value is the position of the word
	 * in the list, where words are numbered starting from 1.
	 */
	/**
	 * A method that finds a word in a listnode.
	 * @param find takes a string as a parameter.
	 * 
	 */

	private static int find(String word) {

		int counter = 0;

		Node runner = head;
		while (runner != null) {

			counter++;
			if (word.equals(runner.item)) {

				return counter;

			}

			runner = runner.next;
		}

		return -1;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Type some words, one to a line.  Press return to end.");
		while (true) {
			System.out.print("? ");
			String word = in.nextLine().trim();
			if (word.length() == 0)
				break;
			add(word);
		}
		System.out.println();
		System.out.println("Your words have been added to the list.");
		System.out.println("Enter words to be tested, one to a line.  Press return to end,");
		while (true) {
			System.out.print("? ");
			String word = in.nextLine().trim();
			if (word.equals(""))
				break;
			int position = find(word);
			if (position == -1)
				System.out.println("   " + word + " was not found in the list.");
			else
				System.out.println("   " + word + " was found in the list at postion " + position + ".");
		}
		in.close();
	}

}
