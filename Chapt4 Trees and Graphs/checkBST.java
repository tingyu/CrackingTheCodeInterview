/**
4.5 Implemen t a function to check if a binary tree is a binary search tree.
*/

/*
We can implement this solution in two different ways. The first leverages the in-order traversal,
and the second builds off the property that left <= current < right.

Solution #1: In-OrderTraversal
Our first thought might be to do an in-order traversal, copy the elements to an array, 
and then check to see if the array is sorted.This solution takes up a bit of extra memory, but it works mostly.

The only problem is that it can't handle duplicate values in the tree properly. 
For example, the algorithm cannot distinguish between the two trees below 
(one of which is invalid) since they have the same in-order traversal.

However, if we assume that the tree cannot have duplicatevalues,then this approach works. 
The pseudocode for this method looks something like:
*/

public static int index = 0;
public static void copyBST(TreeNode root, int[] array){
	if(root == null) return;
	copyBST(root.left, array);
	array[index] = root.data;
	index++;
	copyBST(root.right, array);
}

public static boolean checkBST(TreeNode root){
	int[] array = new int[root.size()];
	copyBST(root, array);
	for(int i = 1; i < array.length; i++){
		if(array[i] <= array[i-1]) return false;
	}
	return true;
}

/*
Note that it is necessary to keep track of the logical "end" of the array, since it would be allocated to hold all the elements.
When we examine this solution, we find that the array is not actually necessary. 
We never useit other than to compareanelement to the previouselement. 
So why not just track the last element we saw and compare it as we go?
The code below implements this algorithm.
*/
public static Integer last_printed = null;
public static boolean checkBST(TreeNode n){
	if(n == null) return true;

	//check / recurse left
	if(!checkBST(n.left)) return false;

	//check current
	if(last_printed != null && n.data <= last_printed){
		return false;
	}
	last_printed = n.data;

	//check / recurse right
	if(!checkBST(n.right)) return false;

	return true; //All good
}

/*
If you don't like the use of static variables, then you can tweak this code to use a wrapper
class for the integer, as shown below.
1 class WrapInt { 
	public int value;
3}
Or, if you're implementing this in C++ or another language that supports passing integers by reference,
then you can simply do that.
*/

/*
Solution #2: The Min / Max Solution
In the second solution, we leverage the definition of the binary search tree.

What does it mean for a tree to be a binary search tree? We know that it must, 
of course, satisfy the condition left.data <= current.data < right.data for each node, 
but this isn't quite sufficient. Consider the following small tree:

Although each node is bigger than its left node and smaller than its right node, 
this is clearly not a binary search tree since 25 is in the wrong place.

More precisely, the condition is that all left nodes must be less than or equal to the current node, 
which must be less than all the right nodes.

Using this thought, we can approach the problem by passing down the min and max values. 
As we iterate through the tree, we verify against progressively narrower ranges.
Consider the following sample tree:

We start with a range of (min = INT_MIN, max = INT_MAX), which the root obviously meets.
We then branch left, checking that these nodes arewithin the range(m in = INTJIIN, max = 20).
Then,we branch right,checking that the nodes are within therange(min=10, max=20).

We proceed through the tree with this approach. When we branch left, the max gets updated. 
When we branch right, the min gets updated. If anything fails these checks, we stop and return false.


The time complexity for this solution is 0(N), where N is the number of nodes in the tree. 
We can prove that this is the best we can do, since any algorithm must touch all N nodes.

Due to the use of recursion, the space complexity is0( log N) on a balancedtree.
There are up to 0(log N)recursive calls on the stacks in cewe may recurse up to the depth of the tree.
*/

boolean checkBST(TreeNode n){
	return checkBST(n, null, null);
}

boolean checkBST(TreeNode n, Integer min, Integer max){
	if(n == null) return true;

	if((min != null && n.data <= min) || max != null && n.data > max)){
		return false;
	}

	if(!checkBST(n.left, min, n.data)|| !checkBST(n.right, n.data, max)){
		return false;
	}
	return true;
}

/*
Remember that in recursive algorithms, you should always make sure that your base cases, 
as well as your null cases, are well handled.
*/
