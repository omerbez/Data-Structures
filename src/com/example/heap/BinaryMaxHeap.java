package com.example.heap;


public class BinaryMaxHeap<T extends Comparable<T>> extends BinaryHeap<T> 
{

	public BinaryMaxHeap() {
		super((o1, o2) -> o1.compareTo(o2));
	}
	
	public BinaryMaxHeap(T[] data) {
		super(data, (o1, o2) -> o1.compareTo(o2));
	}
}
