
/**
 * A generic Queeu class, which can represent stacks of any king of object.
 * For example, Queue<String> represents a queue of strings, and Queue<Node>
 * represents a queue of Nodes.
 */
public class Queue<ItemType> {

	/**
	 * Test whether the queue is empty.
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * Add newItem to the back of the queue.
	 */
	public void enqueue(ItemType newItem) {
		Node newNode = new Node(newItem,null);
		if (head == null)
			head = tail = newNode;
		else {
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	/**
	 * Remove and return the front item in the queue.
	 * @throws IllegalStateException if the queue is empty.
	 */
	public ItemType dequeue() {
		if (head == null)
			throw new IllegalStateException("Can't dequeue from empty queue");
		ItemType it = head.item;
		head = head.next;
		if (head == null)
			tail = null;
		return it;		
	}
	
	// --------------------- private implementation part --------------------
	
	private class Node { // a node in the linked list that implements the queue.
		ItemType item;
		Node next;
		Node(ItemType item, Node next) {
			this.item = item;
			this.next = next;
		}
	}
	
	private Node head; // pointer to front node of queue, null for an empty queue
	private Node tail; // pointer to back node of queue, null for an empty queue
	
}
