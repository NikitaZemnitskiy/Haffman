package com.huffman;

import com.huffman.binaryTree.BinaryTree;
import com.huffman.binaryTree.TreeNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.LinkedHashMap;

import com.huffman.util.WriterStringToFile;


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
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }

        BitSet bitSet = BitSet.valueOf(fileContent);

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
        WriterStringToFile.write(file, stringBuilder.toString());
    }

}
