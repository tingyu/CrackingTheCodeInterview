/**
3.5
Implement a MyQueue class which implements a queue using two stacks.
*/

/*
Solution:
Since the major difference between a queue and a stack is the order (first-in first-out vs. last-in first-out), 
we know that we need to modify peek() and pop() to go in reverse order. 
We can use our second stack to reverse the order of the elements 
(by popping s1 and pushing the elements on to s2). 

In such an implementation, on each peek() and pop() operation, 
we would pop everything from s1 onto s2, perform the peek / pop operation, and then push everything back.
This will work, but if two pop / peeks are performed back-to-back, we're needlessly moving elements.
We can implement a"lazy"approach where we let the elements sit in s2 until  we absolutely must reverse the elements.

In this approach, stackNewest hasthe newest elements on top and stackOldest has the oldest elements on top. 
When we dequeue an element, we want to remove the oldest element first, 
and so we dequeue from stackOldest. If stackOldest is empty, 
then we want to transfer all elements from stackNewest into this stack in reverse order.
To insert an element, we push onto stackNewest, since it has the newest elements on top.

使用两个stack：stackNewest和stackOldest。enqueue的时候就加到stackNewest。
peek和remove的时候和stack的正好要reverse。所以需要先move elements from stackNewest to stackOldest
然后再在stackOldest里面peek或者pop
*/


public class MyQueue<T>{
	Stack<T> stackNewest, stackOldest;

	public MyQueue() {
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();
	}

	public int size(){
		return stackNewest.size() + stackOldest.size();
	}

	public void add(T value){
		//push onto stackNewest, which always has the newest elements on top
		stackNewest.push(value);
	}

	//move elements from stackNewest to stackOldest. This is usually done so that we can do operations on stackOldest
	private void shiftStacks(){
		if(stackOldest.isEmpty()){
			while(!stackNewest.isEmpty()){
				stackOldest.push(stackNewest.pop());
			}
		}
	}

	public T peek(){
		shiftStacks(); //Ensure stackOldest has the current elements
		return stackOldest.peek(); //retrieve the oldest item
	}

	public T remove(){
		shiftStacks(); //Ensure stackOldest has the current elements
		return stackOldest.pop(); //pop the oldest item
	}
}

/*
During your actual interview, you may find that you forget the exact API calls. 
Don't stress too much if that happens to you. Most interviewers are okay with your 
asking for them to refresh your memory on little details. They're much more concerned 
with your big picture understanding.
*/