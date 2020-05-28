package com.example.stack;

public class BoundedStack <T> implements Stack <T>
{
	private int headIndex = -1;
	private Object[] buffer;
	
	
	public BoundedStack(int capacity) {
		assert capacity > 0;
		buffer = new Object[capacity];
	}

	@Override
	public void push(T value) {
		if(headIndex >= buffer.length-1)
			throw new RuntimeException("Stack is full");
		
		headIndex++;
		buffer[headIndex] = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		if(headIndex <= -1)
			throw new RuntimeException("Stack is empty");
		
		T value = (T)buffer[headIndex];
		headIndex--;
		return value;
	}

	@Override
	public int getSize() {
		return headIndex+1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		if(headIndex <= -1)
			return null;
		
		return (T)buffer[headIndex];
	}

	@Override
	public int getCapacity() {
		return buffer.length;
	}
	
	@Override
	public boolean isEmpty() {
		return headIndex <= -1;
	}
}
