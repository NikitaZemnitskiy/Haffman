package com.huffman;

import com.huffman.binaryTree.BinaryTree;
import com.huffman.binaryTree.TreeNode;

import com.huffman.util.ReaderFile;




import java.io.File;
import java.util.Comparator;
import java.util.*;
import java.util.stream.Stream;
import com.huffman.util.WriterBitSetToFile;
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

        char[] charsArr = text.toCharArray();
        Set<Character> charSet = new HashSet<>();
        for (char c : charsArr) {
            charSet.add(c);
        }


        Map<Character, Integer> charMap = new TreeMap<>();

        for (char c : charSet) {
            int count = 0;
            for (char ch : charsArr) {
                if (c == ch) {
                    count++;
                }
            }
            charMap.put(c, count);
        }

        Map<Character, Integer> result = new LinkedHashMap<>();
        Stream<Map.Entry<Character, Integer>> st = charMap.entrySet().stream();
        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
         logger.debug("Code table was created " + result);
        table = CodeTable.createTable((LinkedHashMap<Character, Integer>) result);
        table.writeTable();

        BinaryTree binaryTree = new BinaryTree((LinkedHashMap) result);
        binaryTree.createBinaryTree();
        transformToBinaryCode(binaryTree);

    }

    private void transformToBinaryCode(BinaryTree binaryTree) {
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
        WriterBitSetToFile.write(file, bitSet);
        logger.trace("File has been written");
    }

}
