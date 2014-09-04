/**
3.3
Imagine a (literal) stack of plates. If the stack gets too high, it migh t topple. 
Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold. 
Implement a data structure SetOfStacks that mimics this. SetOf- Stacks should be composed of 
several stacks and should create a new stack once the previous one exceeds capacity. 
SetOfStacks.push() and SetOfStacks. pop () should behave identically to a single stack 
(that is, pop () should return the same values as it would if there were just a single stack).
FOLLOW UP
Implement a function popAt(int index) which performs apop operation ona specific sub-stack
*/


//In this problem, we've been told what our data structure should look like:
class SetOfStacks{
	ArrayList<Stack> stacks = new ArrayList<Stack>();
	public void push(int v){...}
	public int pop(){...}
}

/*
We know that push() should behave identically to a single stack,which means that we need push() to call push()
on the last stack in the array of stacks. We have to be a bit careful here though: 
if the last stack is at capacity, we need to create a new stack. 
Our code should look something like this:
*/

public void push(int v){
	Stack last = getLastStack();
	if(last != null && !last.isFull()){ //add to last stack。不明白这里为什么要判断last != null???如果是空的话也应该create a new Stack>??
		last.push(v);
	}else{ // must create a new Stack
		Stack stack = new Stack(capacity);
		stack.push(v);
		stacks.add(stack);
	}
}

/*
What should pop()do?It should behave similarly to push() in that it should operate on the last stack. 
If the last stack is empty (after popping), then we should remove the stack from the list of stacks.
*/

public int pop(){
	Stack last = getLastStack();
	int v = last.pop();
	if(last.size() == 0) stacks.remove(stack.size() -1);
	return v;
}


/*
Follow Up: Implement popAt(int index)
This is a bit trickier to implement, but we can imagine a "rollover" system. 
If we pop an element from stack 1, we need to remove the bottom of stack 2 and push it onto stack 1. 
We then need to rollover from stack 3 to stack 2, stack 4 to stack 3, etc.
You could make an argument that, rather than "rolling over," 
we should be OK with some stacks not being at full capacity. 
This would improve the time complexity (by a fair amount, with a large number of elements), 
but it might get us into tricky situations later on if someone assumes that all stacks 
(other than the last) operate at full capacity. There's no "right answer" here; 
you should discuss this trade-off with your interviewer.

*/

public class SetOfStacks{
	ArrayList<Stack> stacks = new ArrayList<Stack>();
	public int capacity;
	public SetOfStacks(int capacity){
		this.capacity = capacity;
	}

	public Stack getLastStack(){
		if(stack.size() == 0) return null;
		return stacks.get(stacks.size() - 1);
	}

	public void push(int v) { /* see earlier code */ }
	public int pop() { /* see earlier code */ }
	public boolean isEmpty(){
		Stack last = getLastStack();
		return last == null || last.isEmpty();
	}

	public int popAt(int index){
		return leftShift(index, true);
	}

	public int leftShift(int index, boolean removeTop){
		Stack stack = stacks.get(index);
		int removed_item;
		if(removeTop) removed_item = stack.pop();
		else removed_item = stack.removeBottom();
		if(stack.isEmpty()){
			stacks.remove(index);
		}else if(stacks.size() > index + 1){//不是最后一个stack,把后面stack的里面的元素填过来
			int v = leftShift(index + 1, false);
			stack.push(v);
		}
		return removed_item;
	}
}

/*
这里用了above, below。是为了上面的removeTop或者removeBottom的操作。
上面为什么把下一个stack的bottom的值放到这个stack的顶部？？因为下一个stack是更新的，应该放在当前stack的上面。当前stack的top元素
前面的那个就是下一个stack的bottom.这样递归调用，依次把下一个stack的底部的值移到当前的top
这样操作才可以使得里面的stack都是满的。而不是从前面的stack里面移动值来填满。
*/

public class Stack{
	private int capacity;
	public Node top, bottom;
	public int size = 0;

	public Stack(int capacity){this.capacity = capacity;}
	public boolean isFull() {return capacity == size;}

	public void join(Node above, Node below){
		if(below != null) below.above = above;
		if(above != null) above.below = below;
	}

	public boolean push(int v){
		if(size >= capacity) return false;
		size++;
		Node n = new Node(v);
		if(size == 1) bottom = n;
		join(n, top);
		top = n;
		return true;
	}

	public int pop(){
		Node t = top;
		top = top.below;
		size--;
		return t.value;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public int removeBottom(){
		Node b = bottom;
		bottom = bottom.above;
		if(bottom != null) bottom.below = null;
		size--;
		return b.value;
	}
}

/*
This problem is not conceptually that tough, but it requires a lot of code to implement it fully. 
Your interviewer would not ask you to implement the entire code.
A good strategy on problems like this is to separate code into other methods, 
like a leftshift method that popAt can call.This will make your code cleaner and give you the opportunity to 
lay down the skeleton of the code before dealing with some of the details.
*/



