package com.example.lrucache;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Implement LRU cache with HashSet and LinkedList.
 * Time Complexity: O(n)
 * This approach is slower. A better implementation is to use HashMap + DoubleLinkedList 
 * which takes O(1) time only.
 */
public class BasicLRUCache <T extends Comparable<T>> implements ILRUCache<T>
{
	private Set<T> lookupSet;
	private Deque<T> queue;
	private int cacheSize;
	
	public BasicLRUCache(int cacheSize) {
		this.cacheSize = cacheSize;
		lookupSet = new HashSet<T>(cacheSize);
		queue = new LinkedList<T>();
	}
	
	@Override
	public void referPage(T p) {
		if(lookupSet.contains(p)) {
			// update page's location to the best priority (end of the queue)
			queue.remove(p);
			queue.addLast(p);
		} 
		else {
			//if buffer is full - remove the first..
			if(queue.size() == cacheSize) {
				T first = queue.removeFirst();
				lookupSet.remove(first);
			}
			//add the page to the queue + set..
			queue.addLast(p);
			lookupSet.add(p);
		}
	}
	
	@Override
	public void display() {
		queue.forEach(e -> System.out.print(e.toString()+","));		
		System.out.println();
	}
}