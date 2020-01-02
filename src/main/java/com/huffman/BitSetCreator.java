package com.huffman;

import com.huffman.binaryTree.BinaryTree;
import com.huffman.binaryTree.TreeNode;
import com.huffman.util.WriterBitSetToFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;


public class BitSetCreator {
    private BitSet bitSet;

    public BitSetCreator(BinaryTree binaryTree, String text) {
        this.bitSet = bitSetFromBinaryTree(binaryTree, text);
    }

    public BitSetCreator(File file) {
     this.bitSet = bitSetFromFile(file);
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    private BitSet bitSetFromBinaryTree(BinaryTree binaryTree, String text) {
        BitSet bitSet = new BitSet();
        char[] charsArray = text.toCharArray();
        int pos = 0;
        for (char c : charsArray) {

            TreeNode currentNode = binaryTree.getFirstNode();

            while (!currentNode.isLast()) {
                if (currentNode.getLeft().getStr().contains(Character.toString(c))) {
                    currentNode = currentNode.getLeft();
                } else if (currentNode.getRight().getStr().contains(Character.toString(c))) {
                    bitSet.set(pos);
                    currentNode = currentNode.getRight();
                } else {
                    throw new IllegalStateException("Буквы нет в дереве, буква '" + c + "' отсутствует");
                }
                pos++;
            }
        }
        return bitSet;
    }
    public void writeBitSetToFile(File file){
        WriterBitSetToFile.write(file, bitSet);
    }
    private BitSet bitSetFromFile(File file){
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }

        BitSet bitSet = BitSet.valueOf(fileContent);
        return bitSet;
    }
}