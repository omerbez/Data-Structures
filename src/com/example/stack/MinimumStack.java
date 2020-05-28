package com.example.stack;

/**
 * Stack implementation using LinkedList but has also
 * getMin() method which run in O(1) time
 */
public class MinimumStack <T extends Comparable<T>> implements IMinStack <T>
{
	private Stack<T> stack;  // composite regular LinkedList Stack..
	private Node<T> minsHead;
	
	
	public MinimumStack() {
		stack = new LinkedStack<>();
	}
	
	@Override
	public T pop() {
		T value = stack.pop();
		
		// if poping a minimum - pop it also from the mins stack
		if(minsHead.data.equals(value)) {
			Node<T> temp = minsHead;
			minsHead = minsHead.next;
			temp.next = null;
		}
		
		return value;
	}
	
	@Override
	public void push(T value) {
		stack.push(value);
		
		// if found a new minimum - add it to the mins stack
		if(minsHead == null || value.compareTo(minsHead.data) <= 0) {
			Node<T> min = new Node<>(value);
			min.next = minsHead;
			minsHead = min;
		}
	}
	
	@Override
	public int getSize() {
		return stack.getSize();
	}
	
	@Override
	public T getMin() {
		if(minsHead == null)
			return null;
		
		return minsHead.data;
	}

	@Override
	public int getCapacity() {
		return stack.getCapacity();
	}

	@Override
	public T peek() {
		return stack.peek();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	
	private static class Node <T> {
		T data;
		Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
	}
}
