package com.huffman;

import com.huffman.binaryTree.BinaryTree;
import com.huffman.binaryTree.TreeNode;
import com.huffman.util.WriterStringToFile;

import java.io.File;
import java.util.BitSet;
import java.util.LinkedHashMap;


public class Decoding {

    private File file;
    LinkedHashMap<Character, Integer> map;


    Decoding(File file) {
        this.file = file;

    }

    public void decode() {
        CodeTable codeTable = new CodeTable();
        codeTable.readTable();
        map = codeTable.getMap();

        BinaryTree binaryTree = new BinaryTree(map);
        binaryTree.createBinaryTree();
        BitSetCreator bitSetCreator = new BitSetCreator(file);
        BitSet bitSet = bitSetCreator.getBitSet();

        TreeNode currentNode = binaryTree.getFirstNode();

        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < bitSet.size(); i++) {
            if (currentNode.isLast()) {
                stringBuilder.append(currentNode.getStr());
                currentNode = binaryTree.getFirstNode();
                i--;
            } else if (bitSet.get(i)) {
                currentNode = currentNode.getRight();
            } else {
                currentNode = currentNode.getLeft();
            }
        }
        String text = stringBuilder.toString();
        text = text.substring(0,text.indexOf(Encoding.EOF));
        WriterStringToFile.write(file, text);
    }

}
