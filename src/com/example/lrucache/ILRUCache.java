package com.example.lrucache;

public interface ILRUCache <T extends Comparable<T>> {
	public void referPage(T page);
	public void display();
}
