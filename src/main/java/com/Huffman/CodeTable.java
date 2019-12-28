package com.Huffman;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


public class CodeTable {
    private static Logger logger = (Logger) LoggerFactory.getLogger(CodeTable.class);
    private LinkedHashMap<Character, Integer> map;
    static final String FILENAME = "Codetable.txt";
    static final int SIGNATURE = 0xDDEECCBB;

    public LinkedHashMap<Character, Integer> getMap() {
        return map;
    }

    public static CodeTable createTable(LinkedHashMap<Character, Integer> map) {
        CodeTable result = new CodeTable();
        result.map = map;
        return result;
    }


     void writeTable() {
        File f = new File(FILENAME);
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))) {
            dos.writeInt(SIGNATURE);
            dos.writeInt(map.size());
            for (Map.Entry<Character, Integer> e : map.entrySet()) {
                dos.writeChar(e.getKey());
                dos.writeInt(e.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     void readTable() {
        File f = new File(FILENAME);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
            this.map = map;
            if (dis.readInt() != SIGNATURE) {
                logger.error("Wrong code table");
                throw new IllegalStateException();
            }
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                Character key = dis.readChar();
                Integer value = dis.readInt();
                map.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
