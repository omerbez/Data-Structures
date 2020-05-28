package com.example.stack;

public interface Stack <T> {
	public void push(T value);
	public T pop();
	public int getSize();
	public int getCapacity();
	public T peek();
	public boolean isEmpty();
}
