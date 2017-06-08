package com.nair.practice.datastructures.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T> {

	private BinarySearchTreeNode<T> root;

	public BinarySearchTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinarySearchTreeNode<T> root) {
		this.root = root;
	}

	private void insert(T t, BinarySearchTreeNode<T> node){
		int compareResult = node.compare(t);
		if(compareResult > 0){
			if(node.getLeftChild() == null){
				BinarySearchTreeNode<T>	newNode = new BinarySearchTreeNode<T>(t);
				node.setLeftChild(newNode);
				return;
			}
			insert(t, node.getLeftChild());
		}else if(compareResult < 0){
			if(node.getRightChild() == null){
				BinarySearchTreeNode<T>	newNode = new BinarySearchTreeNode<T>(t);
				node.setRightChild(newNode);
				return;
			}
			insert(t, node.getRightChild());
		}else{
			node.incrementCount();
		}
	}
	
	public void insert(T t){
		if(root == null){
			root = new BinarySearchTreeNode<T>(t);
		}else{
			insert(t, root);
		}
	}
	
	public List<BinarySearchTreeNode<T>> inOrderTraversal(){
		if(root == null){
			return Collections.emptyList();
		}
		List<BinarySearchTreeNode<T>> list = new ArrayList<BinarySearchTreeNode<T>>();
		list.addAll(inorder(root));
		return list;
	}

	private List<BinarySearchTreeNode<T>> inorder(BinarySearchTreeNode<T> node) {
		if(node != null){
			List<BinarySearchTreeNode<T>> list = new ArrayList<BinarySearchTreeNode<T>>();
			list.addAll(inorder(node.getLeftChild()));
			list.add(node);
			list.addAll(inorder(node.getRightChild()));
			return list;
		}
		return Collections.emptyList();
	}

	public List<BinarySearchTreeNode<T>> preOrderTraversal(){
		if(root == null){
			return Collections.emptyList();
		}
		List<BinarySearchTreeNode<T>> list = new ArrayList<BinarySearchTreeNode<T>>();
		list.addAll(preorder(root));
		return list;
	}

	private List<BinarySearchTreeNode<T>> preorder(BinarySearchTreeNode<T> node) {
		if(node != null){
			List<BinarySearchTreeNode<T>> list = new ArrayList<BinarySearchTreeNode<T>>();
			list.add(node);
			list.addAll(preorder(node.getLeftChild()));
			list.addAll(preorder(node.getRightChild()));
			return list;
		}
		return Collections.emptyList();
	}

	public List<BinarySearchTreeNode<T>> postOrderTraversal(){
		if(root == null){
			return Collections.emptyList();
		}
		List<BinarySearchTreeNode<T>> list = new ArrayList<BinarySearchTreeNode<T>>();
		list.addAll(postorder(root));
		return list;
	}

	private List<BinarySearchTreeNode<T>> postorder(BinarySearchTreeNode<T> node) {
		if(node != null){
			List<BinarySearchTreeNode<T>> list = new ArrayList<BinarySearchTreeNode<T>>();
			list.addAll(postorder(node.getLeftChild()));
			list.addAll(postorder(node.getRightChild()));
			list.add(node);
			return list;
		}
		return Collections.emptyList();
	}
	
	public List<BinarySearchTreeNode<T>> levelOrderTraversal(){
		if(root == null){
			return Collections.emptyList();
		}
		List<BinarySearchTreeNode<T>> list = new ArrayList<BinarySearchTreeNode<T>>();
		Queue<BinarySearchTreeNode<T>> queue = new LinkedList<BinarySearchTreeNode<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BinarySearchTreeNode<T> node = queue.poll();
			if(node.getLeftChild() != null){
				queue.add(node.getLeftChild());
			}
			if(node.getRightChild() != null){
				queue.add(node.getRightChild());
			}
			list.add(node);
		}
		return list;
	}

}
