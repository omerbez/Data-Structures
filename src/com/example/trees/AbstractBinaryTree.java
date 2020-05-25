package com.example.trees;


public abstract class AbstractBinaryTree <T extends Comparable<T>> implements ITree<T>
{
	protected BinaryNode<T> root;
	
	
	// inorder tree printer
	private void printSubTree(BinaryNode<T> node) {
		if(node == null)
			return;
		
		printSubTree(node.getLeft());
		
		for(int i=0; i<node.getAmount(); i++)
			System.out.print(node.getValue().toString()+", ");
		
		printSubTree(node.getRight());
	}
	
	// overriding interface-method with an abstract method - for a more specific return type
	public abstract BinaryNode<T> find(T keyVal);
	
	public void printTree() {
		printSubTree(root);
		System.out.println();
	}
	
	static class BinaryNode<T extends Comparable<T>> extends ITree.Node<T> 
	{
		private BinaryNode<T> left, right, parent;
		private int amount;  // how many values of T this node represents..

		public BinaryNode(T v, BinaryNode<T> parent) {
			super(v);
			amount = 1;
			this.parent = parent;
		}

		public BinaryNode<T> getLeft() {
			return left;
		}

		public void setLeft(BinaryNode<T> left) {
			this.left = left;
		}

		public BinaryNode<T> getRight() {
			return right;
		}

		public void setRight(BinaryNode<T> right) {
			this.right = right;
		}

		public int getAmount() {
			return amount;
		}

		public void increaseAmount() {
			amount++;
		}
		
		public void decreaseAmount() {
			amount--;
		}
		
		public BinaryNode<T> getParent() {
			return parent;
		}
		
		public boolean hasLeft() {
			return left != null;
		}
		
		public boolean hasRight() {
			return right != null;
		}
		
		/**
		 * Root node == node without a parent..
		 */
		public boolean isRoot() {
			return parent == null;
		}
		
		/**
		 * check which child (left or right) is the given child argument and replace
		 * it by newNode param.
		 */
		public void replaceChildBy(BinaryNode<T> child, BinaryNode<T> newNode) {
			if(left != null && left.equals(child)) {
				left = newNode;
			}
			else if(right != null && right.equals(child)) {
				right = newNode;
			}
		}
	}
}
