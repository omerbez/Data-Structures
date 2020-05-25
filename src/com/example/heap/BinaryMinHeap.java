package com.example.heap;


public class BinaryMinHeap<T extends Comparable<T>> extends BinaryHeap<T> 
{
	public BinaryMinHeap() {
		// Lower number is higher priority, so if o1 > o2,
		// then o2.compareTo(o1) will result a negative number, as requested..
		super((o1, o2) -> o2.compareTo(o1));
	}
	
	public BinaryMinHeap(T[] data) {
		super(data, (o1, o2) -> o2.compareTo(o1));
	}
}
