package com.example.trees;

public class Example
{
	
	
	public static void main(String[] args) {
		
		ITree<Integer> tree = new BinarySearchTree<>(10, 8, 12, 6, 3, 7, 1, 5, 11, 19, 17, 20, 14, 18, 16, 15, 14);
		tree.printTree();
		
		tree.remove(5);
		tree.remove(14);
		
		assert tree.find(14) != null;
		
		tree.remove(14);
		
		tree.printTree();
	}

}