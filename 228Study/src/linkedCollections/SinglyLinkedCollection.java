package linkedCollections;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * Implementation of the Collection interface based on a
 * 
 * null-terminated, singly-linked list with no dummy node.
 * 
 */
public class SinglyLinkedCollection<E> extends AbstractCollection<E> {

	private Node head;
	private int size;

	@Override
	public boolean add(E item)	{
		
		Node temp = new Node(item, null);
		if (head == null) {
			
			// add to empty list
			head = temp;
			
		} else {

			// add after tail
			Node current = head;
			while (current.next != null) {

				current = current.next;

			}

			current.next = temp;

		}

		++size;
		return true;

	}

	@Override
	public Iterator<E> iterator() {

		return new LinkedIterator();

	}

	@Override
	public int size() {

		return size;

	}

	/**
	 * 
	 * Node type for this linked list.
	 * 
	 */
	private class Node {

		public E data;
		public Node next;
		public Node(E pData, Node pNext) {

			data = pData;
			next = pNext;

		}

	}

	/**
	 * 
	 * Iterator for this linked list.
	 * 
	 */
	private class LinkedIterator implements Iterator<E> {

		// Class invariants:
		// 1) cursor points to the predecessor of the next element
		// (null if the next element is head, or if the list is empty)
		// 2) previous points to the predecessor of cursor, if canRemove is true
		// and cursor != head
		// 3) previous is null if cursor == head
		// 4) previous is null if canRemove is false

		private Node cursor = null;
		private Node previous = null;
		private boolean canRemove = false;

		@Override
		public boolean hasNext() {

			return size > 0 && (cursor == null || cursor.next != null);

		}

		@Override
		public E next() {

			if (!hasNext())
				throw new NoSuchElementException();

			// we know size > 0 and either cursor is null or cursor.next is
			// non-null

			if (cursor == null) {

				// next element to return is head
				previous = null;
				cursor = head;

			} else {

				previous = cursor;
				cursor = cursor.next;

			}

			canRemove = true;
			return cursor.data;

		}

		@Override
		public void remove() {

			if (!canRemove)
				throw new IllegalStateException();

			// size > 0 since canRemove must have been set to true in next();
			// and on the first line of next(), hasNext() must have returned
			// true.
			if (previous == null) {

				// removing first element
				head = head.next;
				cursor = null;

			} else {

				// removing element at cursor
				previous.next = cursor.next;
				cursor = previous;

			}

			--size;
			canRemove = false;
			previous = null;

		}
	}
}
