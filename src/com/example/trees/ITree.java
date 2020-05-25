package com.example.trees;


public interface ITree <T extends Comparable<T>> {
	public void insert(T keyVal);
	public void remove(T keyVal);
	public Node<T> find(T keyVal);
	public void printTree();
	
	
	public static class Node<T extends Comparable<T>> {
		private T value;
		
		public Node(T v) {
			value = v;
		}
		
		public T getValue() {
			return value;
		}
		
		public void setValue(T newVal) {
			value = newVal;
		}
	}
}
