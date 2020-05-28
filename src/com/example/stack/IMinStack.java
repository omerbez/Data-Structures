package com.example.stack;

public interface IMinStack <T extends Comparable<T>> extends Stack <T> {
	public T getMin();
}
