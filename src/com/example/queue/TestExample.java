package com.example.queue;

public class TestExample {
	
	
	public static void main(String[] args) {
		
		Queue<Integer> queue = new LinkedListQueue<>();
		
		queue.enqueue(5);
		queue.enqueue(10);
		queue.dequeue();
		queue.enqueue(20);
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(100);
		queue.enqueue(200);
		
		assert queue.dequeue() == 100;
	}
}
