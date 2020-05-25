package com.example.lrucache;

import java.util.LinkedHashMap;

/**
 * LRU cache using composition of Java LinkedHashMap
 * Time Complexity: O(1)
 */
public class JavaLRUCache <T extends Comparable<T>> implements ILRUCache<T>
{
	private LinkedHashMap<T, Object> map;
	
	public JavaLRUCache(int cacheSize) {
		// cacheSize param is "effectively final" - Java 8 feature..
		map = new LinkedHashMap<>(16, 0.75f, true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<T, Object> eldest) {
				//if the size of the linked-map exceeds the cache's size - remove the last item..
				return size() > cacheSize;
			}
		};
	}
	
	@Override
	public void referPage(T page) {
		map.put(page, null);
	}

	@Override
	public void display() {
		map.keySet().iterator().forEachRemaining(e -> System.out.print(e.toString()+","));
		System.out.println();
	}
}
