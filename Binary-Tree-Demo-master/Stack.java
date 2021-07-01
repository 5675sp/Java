
/**
 * A generic Stack class, which can represent stacks of any king of object.
 * For example, Stack<String> represents a stack of strings, and Stack<Node>
 * represents a stack of Nodes.
 */
public class Stack<ItemType> {

	/**
	 * Test whether the stack is empty.
	 */
	public boolean isEmpty() {
		return top == null;
	}
	
	/**
	 * Add newItem to the top of the stack.
	 */
	public void push(ItemType newItem) {
		top = new Node(newItem,top);
	}
	
	/**
	 * Remove and return the top item from the stack.
	 * @throws IllegalStateException if the stack is empty.
	 */
	public ItemType pop() {
		if (top == null)
			throw new IllegalStateException("Can't pop from empty stack");
		ItemType it = top.item;
		top = top.next;
		return it;		
	}
	
	// --------------------- private implementation part --------------------
	
	private class Node { // a node in a linked list that implements the stack.
		ItemType item;
		Node next;
		Node(ItemType item, Node next) {
			this.item = item;
			this.next = next;
		}
	}
	
	private Node top; // pointer to top of stack, null for an empty stack.
	
}
