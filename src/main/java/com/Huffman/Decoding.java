package com.Huffman;

import com.Huffman.BinaryTree.BinaryTree;
import com.Huffman.BinaryTree.TreeNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.LinkedHashMap;

import com.Huffman.Util.WriterStringToFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Decoding {
    private static Logger logger = LoggerFactory.getLogger(Decoding.class);
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
            logger.error("File is missing");
            throw new IllegalStateException();
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
        WriterStringToFile writer = new WriterStringToFile();
        writer.write(file, stringBuilder.toString());
    }

}
