package com.huffman.binaryTree;


import java.util.*;

public class BinaryTree {

    private LinkedList<TreeNode> nodeList = new LinkedList<>();
    private LinkedHashMap<Character, Integer> map;

    public BinaryTree(LinkedHashMap<Character, Integer> map) {
        this.map = map;
    }

    public void createBinaryTree() {

        for (Map.Entry entry : map.entrySet()) {
            nodeList.add(new TreeNode((Integer) entry.getValue(), entry.getKey().toString()));
        }


        while (nodeList.size() != 1) {
            TreeNode node1 = nodeList.get(0);
            TreeNode node2 = nodeList.get(1);

            int sumValue = node1.value + node2.value;
            String str = node1.str.concat(node2.str);

            TreeNode newTreenode = new TreeNode(sumValue, str, node1, node2);
            nodeList.remove(node1);
            nodeList.remove(node2);
            if (nodeList.size() == 0) {
                nodeList.add(newTreenode);
                break;
            } else {
                boolean isAdded = false;
                for (int i = 0; i < nodeList.size(); i++) {
                    if (nodeList.get(i).value >= newTreenode.value) {
                        nodeList.add(i, newTreenode);
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded) {
                    nodeList.add(nodeList.size(), newTreenode);
                }
            }
        }

    }

    public TreeNode getFirstNode() {
        return nodeList.get(0);
    }
}