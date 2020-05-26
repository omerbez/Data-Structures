package com.example.heap;


/**
 * Abstract BinaryHeap class which using outer Comparator object, to easely create
 * MinHeap and MaxHeap
 */
public abstract class BinaryHeap <T extends Comparable<T>> implements IHeap<T>
{
	private final int INITIAL_CAPACITY = 11;
	private Object[] heap;
	private int elementsCounter;
	private PriorityComparator<T> comparator;
	
	
	/**
	 * Create BinaryHeap using the comparator argument to decide
	 * the priority of the elements.
	 * Higher priority = Top of the Heap.
	 */
	public BinaryHeap(PriorityComparator<T> comparator) {
		assert comparator != null;
		this.comparator = comparator;
		elementsCounter = 0;
		heap = new Object[INITIAL_CAPACITY];
	}
	
	/**
	 * Create heap from unsorted array.
	 * Time Complexity: O(n)
	 */
	public BinaryHeap(T[] data, PriorityComparator<T> comparator) {
		assert data != null && comparator != null;
		
		this.comparator = comparator;
		elementsCounter = data.length;
		heap = new Object[elementsCounter];
		System.arraycopy(data, 0, heap, 0, elementsCounter);
		
		// run heapify-down on all the non-leaf nodes
		int i = parentIndexOf(elementsCounter-1);
		for(; i>=0; i--) {
			heapifyDown(i);
		}
	}

	private void heapifyUp(int i) {
		if(isIndexOutOfRange(i))
			return;
		
		int parentIndex = parentIndexOf(i);
		
		// if child is greater than its parent - swap and heapify-up again..
		if(getElementAt(parentIndex) != null && comparator.compare(getElementAt(i), getElementAt(parentIndex)) == 1) {
			swap(parentIndex, i);
			heapifyUp(parentIndex);
		}
	}
	
	private void heapifyDown(int i) {
		if(isIndexOutOfRange(i))
			return;
		
		T left = getElementAt(leftChildIndexOf(i));
		T right = getElementAt(rightChildIndexOf(i));
		
		int largest = i; 
		
		// find the max element among parent and both children
		if(left != null && comparator.compare(left, getElementAt(largest)) == 1) 
			largest = leftChildIndexOf(i);
		
		if(right != null && comparator.compare(right, getElementAt(largest)) == 1)
			largest = rightChildIndexOf(i);
		
		
		if(i != largest) {
			// swap with child having greater value
			swap(i, largest);
			
			// heapify-down from child..
			heapifyDown(largest);
		}
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public T peekRoot() {
		return (T)heap[0];
	}
	
	@Override
	public T extractRoot() {
		if(elementsCounter == 0)
			return null;
		
		T root = peekRoot();
		heap[0] = heap[elementsCounter-1];
		heap[elementsCounter-1] = null;
		elementsCounter--;
		
		heapifyDown(0);
		return root;
	}
	
	@Override
	public int getSize() {
		return elementsCounter;
	}
	
	@Override
	public void insert(T element) {
		if(elementsCounter >= heap.length)
			grow();
		
		heap[elementsCounter] = element;
		heapifyUp(elementsCounter);
		elementsCounter++;
	}
	
	@Override
	public void changePriorityOf(int index, T newValue) {
		if(isIndexOutOfRange(index) || heap[index] == null || newValue == null)
			return;
		
		// if newValue is smaller, heapifu-down should be performed..
		boolean doHeapifyDown = comparator.compare(newValue, getElementAt(index)) == -1;
		heap[index] = newValue;
		
		if(doHeapifyDown) 
			heapifyDown(index);
		else
			heapifyUp(index);
	}
	
	private int leftChildIndexOf(int i) {
		return i*2 + 1;	
	}
	
	private int rightChildIndexOf(int i) {
		return i*2 + 2;	
	}
	
	private int parentIndexOf(int i) {
		return (i-1) / 2;	
	}
		
	/**
	 * return the element of index or null if out of range.
	 */
	@SuppressWarnings("unchecked")
	private T getElementAt(int index) {
		return isIndexOutOfRange(index) ? null : (T)heap[index];
	}
	
	@SuppressWarnings("unchecked")
	private void swap(int i, int j) {
		if(isIndexOutOfRange(i) || isIndexOutOfRange(j))
			return;
		
		T temp = (T)heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	protected boolean isIndexOutOfRange(int index) {
		return index < 0 || index >= heap.length;
	}
	
	/**
	 * Duoble the size of the heap..
	 */
	private void grow() {
		Object[] newHeap = new Object[heap.length * 2];
		System.arraycopy(heap, 0, newHeap, 0, elementsCounter);
		heap = newHeap;
		System.gc();
	}
	
	
	/**
	 * Strategy Pattern - fanctional interface to compare the priority
	 * among the objects.
	 */
	@FunctionalInterface
	public static interface PriorityComparator<T> {
		/**
		 * Compare the priority of 2 Comparable objects.
		 * @param o1
		 * @param o2
		 * @return 1, 0, or -1 if o1 has better priority, same priority, or less priority than o2
		 */
		public int compare(T o1, T o2);
	}
}
