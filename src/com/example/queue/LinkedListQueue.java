package com.example.queue;


public class LinkedListQueue<T> implements Queue<T>
{
	private int counter = 0;
	private Node<T> head, tail;
	

	@Override
	public boolean enqueue(T element) {
		// first element..
		if(tail == null) {
			tail = new Node<>(element);
			head = tail;
			return true;
		}
		
		tail.next = new Node<>(element);
		tail = tail.next;
		
		counter++;
		return true;
	}

	@Override
	public T dequeue() {
		if(head == null)
			throw new RuntimeException("Queue is empty!");
		
		// last element..
		if(tail == head) {
			tail = null;
		}
		
		T element = head.data;
		head = head.next;
		
		counter--;
		return element;
	}

	@Override
	public T peek() {
		if(head == null)
			throw new RuntimeException("Queue is empty!");
		
		return head.data;
	}

	@Override
	public int getSize() {
		return counter;
	}
	
	
	private static class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
	}
}
