/**
3.1 Describe how you could use a single array to implement three stacks.
*/

/*
Like many problems, this one somewhat depends on how well we'd like to support these stacks. 
If we're ok with simply allocating a fixed amount of space for each stack, we can do that. 
This may mean though that one stack runs out of space, while the others are nearly empty.
Alternatively, we can be flexible in our space allocation, but this significantly increases the complexity of the problem.

Approach 1: Fixed Division
We can divide the array in three equal parts and allow the individual stack to grow in that limited space. 
Note: we will use the notation"[" to mean inclusive of an end point and"(" to mean exclusive of an end point.
• Forstack 1, we will use [0, n/3).
• For stack2,wewill use[n/3j 2n/3).
• For stacks, wewill use [2n/3, n).
*/

int stackSize = 100;
int[] buffer = new int [stackSize * 3];  //the array buffer
int[] stackPointer = {-1, -1, -1};  //pointers to track top element

void push(int stackNum, int value) throws Exception{
	//check if we have space
	if(stackPointer[stackNum] + 1 >= stackSize){ //last element
		throws new Exception("Out of space.");
	}

	//increment stack pointer and then update top value
	stackPointer[stackNum]++; 
	buffer[absTopOfStack(stackNum)] = value;
}

int pop(int stackNum) throws Exception{
	if(stackPointer[stackNum] == -1){
		throws new Exception("Trying to pop an empty stack.");
	}
	int value = buffer[absTopOfStack(stackNum)]; //Get Top
	buffer[absTopOfStack(stackNum)] = 0; //clear index
	stackPointer[stackNum]--;  //Decrement pointer
	return value;
}


int peek(int stackNum){
	int index = absTopOfStack(stackNum);
	return buffer[index];
}

boolean isEmpty(int stackNum){
	return stackPointer[stackNum] == -1;
}

//return index of top of stack "stackNum", in absolute terms
int absTopOfStack(int stackNum){
	return stackNum * stackSize + stackPointer[stackNum];
}

/*
If we had additional information about the expected usages of the stacks, 
then we could modify this algorithm accordingly. For example, 
if we expected Stack 1 to have many more elements than Stack 2, 
we could allocate more space to Stack 1 and less space to Stack 2.
*/

/*
Approach 2: Flexible Divisions
A second approach is to allow the stack blocks to be flexible in size. When one stack exceeds its initial capacity, 
we grow the allowable capacity and shift elements as neces- sary.
We will also design our array to be circular, such that the final stack may start 
at the end of the array and wraparound to the beginning.
Please note that the code for this solution is far more complex than would be 
appro- priate for an interview. You could be responsible for pseudocode, 
or perhaps the code of individual components, but the entire implementation would be far too challenging.
*/

//StackData is a simple class that holds a set of data about each stack. It does not hold the actual iterm in the stack
public class StackData{
	public int start;
	public int pointer;
	public int size = 0;
	public int capacity;

	public StackData(int _start, int _capacity){
		start = _start;
		pointer = _start - 1;
		capacity = _capacity;
	}

	public boolean isWithinStack(int index, int total_size){
		//if stack wraps, the head (right size) wraps around to the left
		if(start <= index && index < start + capacity){ //index在start和start + capacity之间
			//no wrapping, or "head" (right size) of wrapping case
			return true;
		} else if(start + capacity > total_size && index < (start + capacity)%total_size){ //start+capacity大于总长度，并且index < ..
			//tail of wrapping case
			return true;
		}
		return false;
	}
}

public class QuestionB{
	static int number_of_stacks = 3;
	static int default_size = 4;
	static int total_size = default_size * number_of_stacks;
	static StackData[] stacks = {new StackData(0, default_size), 
		new StackData(default_size, default_size), 
		new StackData(default_size*2, default_size)};
	static int[] buffer = new int[total_size];

	public static void main(String[] args) throws Exception{
		push(0, 10);
		push(1, 20);
		push(2, 30);
		int v = pop(0);
		...
	}

	public static int numberOfElements(){
		return stack[0].size + stack[1].size + stack[2].size;
	}

	//返回下一个元素
	public static int nextElement(int index){
		if(index + 1 == total_size) return 0;
		else return index +1;
	}

	//返回上一个元素
	public static int previousElement(int index){
		if(index == 0) return total_size - 1;
		else return index - 1;
	}

	public static void shift(int stackNum){
		StackData stack = stacks[stackNum];
		if(stack.size >= stack.capacity){//如果大于capacity，就需要把下一个stack往后移动，然后当前stack的capacity++
			int nextStack = (stackNum + 1) % number_of_stacks;
			shift(nextStack); //make some room
			stack.capacity++;
		}

		//shift elements in reverse order
		//逆序一个个元素往后移动
		for(int i = (stack.start + stack.capacity - 1)%total_size; stack.isWithinStack(i, total_size); i = previousElement(i)){
			buffer[i] = buffer[previousElement(i)];
		}

		//把stack的start往后移动一个位置
		buffer[stack.start] = 0;
		stack.start = nextElement(stack.start); //move stack start
		stack.pointer = nextElement(stack.pointer); //move pointer
		stack.capacity--; //return capacity to original
	}

	//expand stack by shifting over other stacks
	public static void expand(int stackNum){
		shift((stackNum + 1)%number_of_stacks);
		stack[stackNum].capacity++;
	}

	public static void push(int stackNum, int value) throws Exception{
		StackData stack = stacks[stackNum];
		//check that we have space
		if(stack.size() >= stack.capacity){
			if(numberOfElements() >= total_size){ //totally full
				throws new Exception("Out of space");
			}else{//just need to shift things around
				expand(stackNum);
			}
		}
		//find the index of the top element in the array+1, and increment the stack pointer
		stack.size++;
		stack.pointer = newElement(stack.pointer);
		buffer[stackPointer] = value;
	}

	public staic int pop(int stackNum) throws Exception{
		StackData stack = stacks[stackNum];
		if(stack.size == 0){
			throw new Exception("Trying to pop an empty stack");
		}

		int value = buffer[stack.pointer];
		buffer[stack.pointer] = 0;
		stack.pointer = previousElement(stack.pointer);
		stack.size--;
		return value;
	}

	public static int peek(int stackNum){
		StackData stack = stacks[stackNum];
		return buffer[stack.pointer];
	}

	public static boolean isEmpty(int stackNum){
		StackData stack = stacks[stackNum];
		return stack.size == 0;
	}
}


