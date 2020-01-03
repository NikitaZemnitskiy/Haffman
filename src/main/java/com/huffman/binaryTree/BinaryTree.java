package com.huffman.binaryTree;


import java.util.*;
import java.util.stream.Stream;

public class BinaryTree {

    private LinkedList<TreeNode> nodeList = new LinkedList<>();
    private LinkedHashMap<Character, Integer> map;

    public BinaryTree(LinkedHashMap<Character, Integer> map) {
        this.map = map;
    }
    public BinaryTree(String text) {
        this.map = createSortedLinkedHashMapFromString(text);
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

    private LinkedHashMap<Character, Integer> createSortedLinkedHashMapFromString(String text){
        Map<Character, Integer> charMap = new TreeMap<>();
        char[] charsArr = text.toCharArray();
        for (char c : charsArr) {
            if(charMap.containsKey(c)){
                charMap.put(c,charMap.get(c)+1);
            }
            else
                charMap.put(c, 1);
        }
        LinkedHashMap<Character, Integer> result = new LinkedHashMap<>();
        Stream<Map.Entry<Character, Integer>> st = charMap.entrySet().stream();
        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }
    public Map<Character, Integer> getMap(){
        return this.map;
    }
}