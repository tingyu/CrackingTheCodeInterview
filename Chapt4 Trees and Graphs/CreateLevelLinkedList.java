/**
4.4 Given a binary tree, design an algorithm which creates a linked list of all the nodes at
each depth (e.g., if you have a tree with depth D,you'll have D linked lists)
*/

/*
Though we might think at first glance that this problem requires a level-by-level traversal, 
this isn't actually necessary. We can traverse the graph any way that we'd like, provided 
we know which level we're on aswe do so.

We can implement a simple modification of the pre-order traversal algorithm, where we pass in 
level + 1 to the next recursive call.The code below provides an implementation using depth first search.
*/

void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level){
	if(root == null) return; //base case

	LinkedList<TreeNode> list = null;
	if(lists.size() == level){ //Level not contained in list
		lists = new LinkedList<TreeNode>();
		/* Levels are always traversed in order. So, if this is the first time we've visited
		   level 1, we must have seen levels 0 through i - 1. We can therefore safely add 
		   the level at the end.
		*/
		lists.add(list);
	}else{
		list = lists.get(level);
	}
	list.add(root);
	createLevelLinkedList(root.left, lists, level + 1);
	createLevelLinkedList(root.right, lists, level + 1);
}

ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
	ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
	createLevelLinkedList(root, lists, 0);
	return lists;
}

/*
Alternatively, we can also implement a modification of breadth first search. 
With this implementation, we want to iterate through the root first, then level 2, then level 3, and soon.
With each level i, we will have already fully visited all nodes on level i - 1.
This means that to get which nodes are on level i, we can simply look at all children 
of the nodes of level i - 1.
*/

ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
	ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
	//"visit the root"
	LinkedList<TreeNode> current = new LinkedList<TreeNode>();
	if(root != null){
		current.add(root);
	}

	while(current.size() > 0){
		result.add(current); //add previous level
		LinkedList<TreeNode> parents = current; //Go to next level
		current = new LinkedList<TreeNode>();
		for(TreeNode parent : parents){
			//Visit the children
			if(parent.left != null){
				current.add(parent.left);
			}
			if(parent.right != null){
				current.add(parent.right);
			}
		}
	}
	return result;
}

/*
One might ask which of these solutions is more efficient. 
Both run in 0 ( N ) time, but what about the space efficiency?
At first, we might want to claim that the second solution is more space efficient.
In a sense,that's correct.The first solution uses O(log N) recursive calls, each of which adds a new level to the stack. 
The second solution, which is iterative, does not require this extra space.
However, both solutions require returning 0 ( N ) data.The extra 0 ( l o g N) space usage 
from the recursive implementation is dwarfed by the 0(N) data that must be returned. 
So while the first solution may actually use more data, they are equally efficient when it comes to "big 0."
*/

