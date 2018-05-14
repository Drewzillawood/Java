package firstCollection;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FirstCollection<E> extends AbstractCollection<E> {
	private static final int DEFAULT_SIZE = 10;
	private E[] data;
	private int size;

	/**
	 * Creates a collection with a backing array of default initial capacity.
	 */
	public FirstCollection() {
		this(DEFAULT_SIZE);
	}

	/**
	 * Creates a collection with a backing array of the given initial capacity.
	 * 
	 * @param initialCapacity
	 *            the initial size of the backing array
	 */
	@SuppressWarnings("unchecked")
	public FirstCollection(int initialCapacity) {
		// Unchecked warning unavoidable for creating new array
		data = (E[]) new Object[initialCapacity];
		size = 0;
	}

	@Override
	public boolean add(E item) {
		checkCapacity();
		data[size++] = item;
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Ensures that the backing array has space to store at least one additional
	 * element.
	 */
	private void checkCapacity() {
		if (size == data.length) {
			// create a copy of the data array with double the capacity
			data = Arrays.copyOf(data, data.length * 2);
		}
	}

	/**
	 * Implementation of the Iterator interface for this collection.
	 */
	private class MyIterator implements Iterator<E> {
		// index of the next element to be returned by next()
		private int cursor = 0;
		private boolean canRemove = false;

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public E next() {
			if (cursor >= size)
				throw new NoSuchElementException();
			canRemove = true;
			return data[cursor++];
		}

		@Override
		public void remove() {
			if (!canRemove) {
				throw new IllegalStateException();
			}

			// delete the element before the cursor
			// Note: it must be the case that cursor >= 1
			for (int i = cursor; i < size; ++i) {
				data[i - 1] = data[i];
			}

			// null out the vacated cell to avoid memory leak
			data[size - 1] = null;

			--size;
			--cursor;
			canRemove = false;
		}
	}
}