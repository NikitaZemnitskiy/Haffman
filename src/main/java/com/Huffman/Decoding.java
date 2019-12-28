package com.Huffman;

import com.Huffman.BinaryTree.BinaryTree;
import com.Huffman.BinaryTree.TreeNode;
import com.Huffman.Util.Writer;
import com.Huffman.Util.Reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.LinkedHashMap;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;


public class Decoding {
    private static Logger logger = LoggerFactory.getLogger(Decoding.class);
    private File file;
    LinkedHashMap<Character, Integer> map;
    private Reader reader;
    private Writer writer;

    Decoding(File file, Reader reader, Writer writer) {
        this.file = file;
        this.reader = reader;
        this.writer = writer;
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
        writer.write(file, stringBuilder.toString());
    }

}
