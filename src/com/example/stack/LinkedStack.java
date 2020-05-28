package com.example.stack;


/**
 * Implementation of Stack by LinkedList..
 */
public class LinkedStack <T> implements Stack <T>
{
	private int counter = 0;
	private Node<T> head;
	private final int MAX_CAPACITY = Integer.MAX_VALUE;
	
	
	@Override
	public void push(T value) {
		if(counter == MAX_CAPACITY)
			throw new RuntimeException("Stack is full");
		
		if(head == null) {
			head = new Node<>(value);
			counter++;
			return;
		}
		
		Node<T> temp = new Node<>(value);
		temp.next = head;
		head = temp;
	}

	@Override
	public T pop() {
		if(head == null)
			throw new RuntimeException("An attempt to pop element from an empty stack");
		
		T value = head.value;
		Node<T> temp = head;
		head = head.next;
		temp.next = null;
		counter--;
		
		return value;
	}

	@Override
	public int getSize() {
		return counter;
	}

	@Override
	public T peek() {
		if(head == null)
			return null;
		
		return head.value;
	}
	
	@Override
	public int getCapacity() {
		return MAX_CAPACITY;
	}
	
	@Override
	public boolean isEmpty() {
		return head == null;
	}
	
	
	private static class Node <T> {
		private T value;
		private Node<T> next;
		
		public Node(T value) {
			this.value = value;
		}
	}
}
