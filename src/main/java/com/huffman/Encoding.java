package com.huffman;

import com.huffman.binaryTree.BinaryTree;
import com.huffman.binaryTree.TreeNode;
import com.huffman.util.ReaderFile;
import java.io.File;
import java.util.Comparator;
import java.util.*;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Encoding {
    private static final Logger logger = LoggerFactory.getLogger(Encoding.class);


    private File file;
    private String text;

    private CodeTable table = new CodeTable();


     Encoding( File file) {
         ReaderFile reader = new ReaderFile();
         this.text = reader.read(file);
         this.file = file;
    }

     void treeCreating() {
         BinaryTree binaryTree = new BinaryTree(text);

         table = CodeTable.createTable((LinkedHashMap<Character, Integer>) binaryTree.getMap());
         table.writeTable();


        binaryTree.createBinaryTree();
        BitSetCreator bitSetCreator = new BitSetCreator(binaryTree, text);
        bitSetCreator.writeBitSetToFile(file);

    }

}
