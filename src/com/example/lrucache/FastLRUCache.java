package com.example.lrucache;
import java.util.HashMap;
import java.util.Map;
import com.example.lrucache.FastLRUCache.DoublyLinkedList.Entry;

/**
 * LRU cache implementation using HashMap and DoublyLinkedList.
 * Time Complexity: O(1)
 */
public class FastLRUCache <T extends Comparable<T>> implements ILRUCache<T>
{
	private Map<T, Entry<T>> map;
	private int cacheSize;
	private DoublyLinkedList<T> list;
	
	
	public FastLRUCache(int cacheSize) {
		this.cacheSize = cacheSize;
		map = new HashMap<>();
		list = new DoublyLinkedList<>();
	}
	
	@Override
	public void referPage(T p) {
		if(map.containsKey(p)) {
			// update page's location to the best priority (end of the queue)
			Entry<T> entry = map.get(p);
			list.swapToTail(entry);
		} 
		else {
			//if buffer is full - remove the eldest entry
			if(map.size() == cacheSize) {
				map.remove(list.removeHead().value);
			}
			//add the page to the Map & list's tail
			map.put(p, list.addLast(p));
		}
	}
	
	@Override
	public void display() {
		list.printList();
	}
	
	
	static class DoublyLinkedList <T extends Comparable<T>> {
		private Entry<T> head, tail;
		
		public Entry<T> addFirst(T value) {
			Entry<T> entry = new Entry<>(value);
			entry.next = head;
			
			if(head != null)
				head.previous = entry;
			
			head = entry;
			
			if(tail == null)
				tail = head.next == null ? head : head.next;
			
			return head;
		}
		
		public Entry<T> addLast(T value) {
			if(tail == null)
				return addFirst(value);
			
			tail.next = new Entry<>(value);
			tail.next.previous = tail;
			tail = tail.next;
			return tail;
		}
		
		public void swapToTail(Entry<T> entry) {
			if(entry == null || tail == entry)
				return;
			
			
			if(entry != head)
				entry.previous.next = entry.next;
			else
				head = head.next;
			
			entry.next = null;
			tail.next = entry;
			tail = tail.next;
		}
		
		public Entry<T> removeHead() {
			if(head == null)
				return null;
			
			Entry<T> temp = head;
			head = head.next;
			
			// remove connection if exists..
			if(head != null)
				head.previous = null;
			else
				tail = null;  // there was only one element in the list.. update tail too
			
			return temp; // return the removed head entry..
		}
		
		public void printList() {
			Entry<T> temp = head;
			while(temp != null) {
				System.out.print(temp.value.toString()+",");
				temp = temp.next;
			}	
			System.out.println();
		}
		
		static class Entry <V extends Comparable<V>> {
			private V value;
			private Entry<V> previous, next;
			
			public Entry(V value) {
				this.value = value;
			}
		}
	}
}