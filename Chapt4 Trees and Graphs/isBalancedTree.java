/**
Implement a function to check if a binary tree is balanced. For the purposes of this question, 
a balanced tree is defined to be a tree such that the heights of the two subtrees of any 
node never differ by more than one
*/

/*
In this question, we've been fortunate enough to be told exactly what balanced means: 
that for each node, the two subtrees differ in height by no more than one. 
We can implement a solution based on this definition. We can simply recurse through the entire tree, 
and for each node, compute the heights of each subtree.
*/

public static int getHeight(TreeNode root){
	if(root == null) return 0;//base case
	return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
}

public static boolean isBalanced(TreeNode root){
	if(root == null) return true;//base case

	int heightDiff = getHeight(root.left) - getHeight(root.right);

	if(Math.abs(heightDiff) > 1){
		return false;
	}else{//recurse
		return isBalanced(root.left)&&isBalanced(root.right);
	}
}

/*
Although this works, it's not very efficient. On each node, we recurse through its entire subtree. 
This means that getHeight is called repeatedly on the same nodes.The algo- rithm isthereforeO(N2).
The algorithm is O(N log N)since each node is "touched" once per node above it

We need to cut out some of the calls to getHeight.
因为getHeight和isBalanced有些重复了

If we inspect this method, we may notice that getHeight could actually check if the tree is
 balanced as the same time as it's checking heights. What do we do when we discover that the 
 subtree isn't balanced? Just return -1.

This improved algorithm works by checking the height of each subtree as we recurse down from 
the root. On each node, we recursively get the heights of the left and right subtrees through 
the checkHeight method. If the subtree is balanced, then check- Height will return the actual 
height of the subtree. If the subtree is not balanced, then checkHeight will return -1. 
Wewill immediately break and return -1 from the current call.
*/

public static int checkHeight(TreeNode root){
	if(root == null){
		return 0; //height of 0
	}

	//check if left is balanced
	int leftHeight = checkHeight(root.left);
	if(leftHeight == -1){
		return -1; //Not balanced
	}

	//check if right is balanced
	int rightHeight = checkHeight(root.right);
	if(rightHeight == -1){
		return -1; //not balanced
	}

	//check if current node is balanced
	int heightDiff = leftHeight - rightHeight;
	if(Math.abs(heightDiff) > 1){
		return -1; //Not balanced
	} else{
		//return right
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public static boolean isBalanced(TreeNode root){
		if(checkHeight(root) == -1){
			return false;
		}else{
			return true;
		}
	}
}

//This code runs in 0(N) time and 0(H) space, where H is the height of the tree.

//可以简化下代码如下
 public class Solution{
 	public boolean isBalanced(TreeNode root) {
 		if(root == null)
 			return true;
 		if(getHeight(root) == -1)
 			return false;
 		return true;
 	}

 	public int getHeight(TreeNode root){
 		if(root == null)
 			return 0;

 		int left = getHeight(root.left);
 		int right = getHeight(root.right);

 		if(left == -1 || right == -1)
 			return -1;

 		if(Math.abs(left - right) > 1){
 			return -1;
 		}

 		return Math.max(left, right) + 1;
 	}
 }