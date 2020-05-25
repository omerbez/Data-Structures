package com.example.lrucache;

public class Example 
{
		
	public static void main(String[] args) {
		ILRUCache<Integer> ca = new FastLRUCache<>(3); 
        ca.referPage(1); ca.display();
        ca.referPage(1); ca.display();
        ca.referPage(2); ca.display();
        ca.referPage(3); ca.display();
        ca.referPage(4); ca.display(); // 1 will be removed because it's the eldest one..
        ca.referPage(2); ca.display();
        ca.referPage(5); ca.display(); // 3 will be removed..
	}
}
