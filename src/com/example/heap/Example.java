package com.example.heap;

public class Example
{
	
	
	public static void main(String[] args) {
		
		Integer[] data = new Integer[] {100, 5, 6, 2000, -9, 500};
		BinaryHeap<Integer> heap = new BinaryMaxHeap<>(data);
		
		heap.changePriorityOf(0, -20);  // change 2000 to -20
		
		// insert() can be implemented by returning the index of the inserted element
		// in order to change its priority in the future..
		for(int i=0; i<50; i++)
			heap.insert((int)(Math.random() * 100));
		
		
		for(int i=0; i<5; i++)
			System.out.println(heap.extractRoot());
	}
}
