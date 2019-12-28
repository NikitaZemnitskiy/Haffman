package com.Huffman.BinaryTree;

public class TreeNode {
    int value;
    boolean isLast;
    TreeNode left;
    TreeNode right;
    String str;

    TreeNode(int value, String str) {
        this.value = value;
        isLast = true;
        this.str = str;
    }

    TreeNode(int value, String str, TreeNode left, TreeNode right) {
        this.value = value;
        this.str = str;
        isLast = false;
        this.left = left;
        this.right = right;
    }


    public boolean isLast() {
        return isLast;
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
