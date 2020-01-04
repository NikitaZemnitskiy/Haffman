package com.huffman;

import com.huffman.binaryTree.BinaryTree;
import com.huffman.util.ReaderFile;

import java.io.File;
import java.util.LinkedHashMap;




public class Encoding {


    public final static char EOF = '†';
    private File file;
    private String text;

    private CodeTable table = new CodeTable();


     Encoding( File file) {
         ReaderFile reader = new ReaderFile();
         this.text = reader.read(file) + EOF;
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
