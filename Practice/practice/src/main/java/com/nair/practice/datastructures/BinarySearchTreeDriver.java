package com.nair.practice.datastructures;

import com.nair.practice.datastructures.trees.BinarySearchTree;
import com.nair.practice.datastructures.trees.BinarySearchTreeNode;

public class BinarySearchTreeDriver {

	public static void main(String[] args) {
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
		int[] ints = new int[]{4,2,6,1,3,5,7};
		for(int i : ints){
			binarySearchTree.insert(i);
		}
		for(BinarySearchTreeNode<Integer> node : binarySearchTree.levelOrderTraversal()){
			System.out.println(node.getData());
		}
	}
}
