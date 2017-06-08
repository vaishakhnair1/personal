package com.nair.practice.datastructures.trees;

public class BinarySearchTreeNode<T> {
	
	private T data;
	private int count;
	private BinarySearchTreeNode<T> leftChild;
	private BinarySearchTreeNode<T> rightChild;
	
    public BinarySearchTreeNode(T data) {
		this.data = data;
	}

	public BinarySearchTreeNode(T data, BinarySearchTreeNode<T> leftChild, BinarySearchTreeNode<T> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	@SuppressWarnings("unchecked")
	public final int compare(T data) {
        return ((Comparable<? super T>)this.data).compareTo(data);
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BinarySearchTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinarySearchTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public BinarySearchTreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinarySearchTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	public int getCount() {
		return count;
	}
	
	public void incrementCount(){
		++count;
	}
	
	public void decrementCount(){
		--count;
	}

}
