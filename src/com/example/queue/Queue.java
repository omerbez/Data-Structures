package com.example.queue;


public interface Queue<T> {
	public boolean enqueue(T element);
	public T dequeue();
	public T peek();
	public int getSize();
}
