package com.Huffman;

import com.Huffman.BinaryTree.BinaryTree;
import com.Huffman.BinaryTree.TreeNode;
import com.Huffman.Util.Reader;
import com.Huffman.Util.Writer;



import java.io.File;
import java.util.Comparator;
import java.util.*;
import java.util.stream.Stream;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class Encoding {
    private static final Logger logger = LoggerFactory.getLogger(Encoding.class);
    private File file;
    private String text;
    private Writer writer;
    private char EOF = '∡';
    private CodeTable table = new CodeTable();


     Encoding(Reader reader, File file, Writer writer) {
        this.text = reader.read(file).toString();
        this.writer = writer;
        this.file = file;
    }

     void treeCreating() {
        text = text.concat(Character.toString(EOF));
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
            if (c == EOF) {
                break;
            }
            TreeNode currentNode = binaryTree.getFirstNode();

            while (!currentNode.isLast()) {
                if (currentNode.getLeft().getStr().contains(Character.toString(c))) {
                    currentNode = currentNode.getLeft();
                } else if (currentNode.getRight().getStr().contains(Character.toString(c))) {
                    bitSet.set(pos);
                    currentNode = currentNode.getRight();
                } else {
                    logger.error("Буквы нет в дереве, буква " + c + " отсутствует");
                    throw new IllegalStateException("Буквы нет в дереве, буква '" + c + "' отсутствует");
                }
                pos++;
            }
        }

        writer.write(file, bitSet);
        logger.trace("File was writed");
    }

}
