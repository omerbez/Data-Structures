package com.example.heap;


public interface IHeap <T extends Comparable<T>> {
	public int getSize();
	public void changePriorityOf(int index, T newValue);
	public void insert(T element);
	public T peekRoot();
	public T extractRoot();
}
