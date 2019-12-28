package com.Huffman.Util;

import java.io.*;
import java.util.BitSet;

public class WriterBitSetToFile  {


    public void write(File file, BitSet bitSet) {
       /* String newName = file.getName().substring(0, file.getName().indexOf("."));
        newName = newName.concat(".hf");*/
       String newName = file.getName().concat(".hf");
        try (FileOutputStream fos = new FileOutputStream(newName)) {

            byte[] buffer = bitSet.toByteArray();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            throw new IllegalStateException("You miss file");
        }
    }

}
