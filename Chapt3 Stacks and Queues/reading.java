

Like linked list questions, questions on stacks and queues will be much easier to
handle if you are comfortable with the ins and outs of the data structure. The prob-
lems can be quite tricky though. While some problems may be slight modifications on
the original data structure, others have much more complex challenges.

Implementing a Stack

Recall that a stack uses the LIFO (last-in first-out) ordering. That is, like a stack of dinner
plates, the most recent item added to the stack is the first item to be removed.
We have provided simple sample code to implement a stack. Note that a stack can also
be implemented using a linked list. In fact, they are essentially the same thing, except
that a stack usually prevents the user from "peeking" at items below the top node.

class Stack {
	Node top;
	Object pop() {
		if (top != null) {
			Object item = top.data;
			top = top.next;
			return item;
		}
		return null;
	}

	void push(0bject item) {
		Node t = new Node(item);
  		t.next = top;
	}

	top = t;
	Object peekQ {
		return top.data;
	}
}


Implementing a Queue
A queue implements FIFO (first-ln first-out) ordering. Like a line or queue at a ticket
stand, items are removed from the data structure in the same order that they are added.
A queue can also be implemented with a linked list, with new items added to the tail of
the linked list.

class Queue {
	Node first, last;
	void enqueue(0bject item) {
		if (first == null) {
			last = new Node(item);
			first = last;
		} else {
			last.next = new Node(item);
			last = last.next;
		}
	}	
	Object dequeueQ {
		if (first != null) {
			Object item = first.data;
			first = first.next;
			return item;
		}	
		return null;
	}
}	
