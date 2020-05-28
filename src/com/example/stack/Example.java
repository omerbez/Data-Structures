package com.example.stack;

public class Example 
{
	
	public static void main(String[] args) {
		
		Stack<Integer> stack = new LinkedStack<>();
		stack.push(5);
		stack.push(10);
		stack.push(20);
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+",");
		}	
		System.out.println();
		
		
		IMinStack<Integer> minStack = new MinimumStack<>();
		minStack.push(9);
		minStack.push(-5);
		minStack.push(10);
		minStack.push(20);
		minStack.push(33);
		minStack.push(-100);
		
		minStack.pop();
		minStack.pop();
		
		System.out.println("Minimum value in the stack: "+minStack.getMin());
	}
}
