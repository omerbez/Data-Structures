package com.example.queue;


/**
 * classic implementation of Queue with bounded array.
 */
public class ArrayQueue <T> implements Queue<T>
{
	private int capacity;
	private int head, tail;
	private Object[] buffer;
	private int counter = 0;
	
	
	public ArrayQueue(int capacity) {
		assert capacity <= 0;
		this.capacity = capacity;
		buffer = new Object[capacity];
		head = tail = 0;
	}

	@Override
	public boolean enqueue(T element) {
		if(counter == capacity) {
			System.err.println("Queue is full!");
			return false;
		}
		
		buffer[tail] = element;
		tail = (tail + 1) % capacity;
		counter++;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T dequeue() {
		// block or throw..
		if(counter == 0)
			throw new RuntimeException("Queue is empty!");
		
		T element = (T)buffer[head];
		head = (head + 1) % capacity;
		counter--;
		return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		if(counter == 0)
			throw new RuntimeException("Queue is empty!");
		
		return (T)buffer[head];
	}
	
	/**
	 * The number of element in the Queue..
	 */
	@Override
	public int getSize() {
		return counter;
	}

	public int getCapacity() {
		return capacity;
	}
}