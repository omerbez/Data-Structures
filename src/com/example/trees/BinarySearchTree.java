package com.example.trees;

public class BinarySearchTree <T extends Comparable<T>> extends AbstractBinaryTree<T>
{
	
	public BinarySearchTree() {
		
	}
	
	@SafeVarargs
	public BinarySearchTree(T... values) {
		for(T val : values)
			insert(val);
	}

	@Override
	public void insert(T keyVal) {
		if(root == null) {
			root = new BinaryNode<>(keyVal, null);
			return;
		}
		
		BinaryNode<T> temp = root;
		while(true) {
			if(keyVal.compareTo(temp.getValue()) == 0) {
				temp.increaseAmount();
				break;
			}
			else if(keyVal.compareTo(temp.getValue()) < 0) {
				// Go to left..
				if(temp.getLeft() == null) {
					temp.setLeft(new BinaryNode<>(keyVal, temp));
					break;
				}
				temp = temp.getLeft();
			}
			else {
				// Go to right..
				if(temp.getRight() == null) {
					temp.setRight(new BinaryNode<>(keyVal, temp));
					break;
				}
				temp = temp.getRight();
			}
		}		
	}

	@Override
	public void remove(T keyVal) {
		if(keyVal == null)
			return;
		
		BinaryNode<T> nodeToRemove = find(keyVal);
		if(nodeToRemove == null)
			return;
		
		removeNode(nodeToRemove);
	}

	@Override
	public BinaryNode<T> find(T keyVal) {
		if(root == null || keyVal == null)
			return null;
		
		BinaryNode<T> temp = root;
		while(temp != null) {
			if(keyVal.compareTo(temp.getValue()) == 0)
				return temp;
			
			if(keyVal.compareTo(temp.getValue()) < 0) {
				// go left..
				temp = temp.getLeft();
			} else {
				temp = temp.getRight();
			}
		}
		
		return null;
	}
	
	
	private void removeNode(BinaryNode<T> nodeToRemove) {
		if(nodeToRemove == null)
			return;
		
		if(nodeToRemove.getAmount() > 1) {
			nodeToRemove.decreaseAmount();
			return;
		}
		
		// if there is 1 or 0 children to the node
		if(!nodeToRemove.hasLeft() || !nodeToRemove.hasRight()) {
			BinaryNode<T> temp = nodeToRemove.hasLeft() ? nodeToRemove.getLeft() : nodeToRemove.getRight();
			nodeToRemove.getParent().replaceChildBy(nodeToRemove, temp);
		} else {
			// Node has both left and right children..
			BinaryNode<T> nextNode = findNextOf(nodeToRemove);  
			assert nextNode != null;  // should not be null..
			
			// Swap values and remove the nextNode
			nodeToRemove.setValue(nextNode.getValue());
			removeNode(nextNode);
		}
	}
	
	/**
	 * Find the next node - such that its value is right after this node's value..
	 */
	private BinaryNode<T> findNextOf(BinaryNode<T> node) {
		if(node == null || !node.hasRight())
			return null;
		
		// Go one step right and as much as possible to the left
		node = node.getRight();
		while(node.hasLeft())
			node = node.getLeft();
		
		return node;
	}
}
