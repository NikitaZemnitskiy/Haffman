package com.huffman.binaryTree;

public class TreeNode {
    int value;

    TreeNode left;
    TreeNode right;
    String str;

    TreeNode(int value, String str) {
        this.value = value;
        this.str = str;
    }

    TreeNode(int value, String str, TreeNode left, TreeNode right) {
        this.value = value;
        this.str = str;
        this.left = left;
        this.right = right;
    }


    public boolean isLast() {
        return getLeft() == null && getRight() == null;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public String getStr() {
        return str;
    }
}
