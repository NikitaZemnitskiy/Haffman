package com.Huffman.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterStringToFile implements Writer<File, String> {


    @Override
    public void write(File file, String s) {
        String fileNewName = file.getName().substring(0, file.getName().indexOf("."));
        try (FileWriter fileWriter = new FileWriter(fileNewName)) {
            fileWriter.write(s);
            fileWriter.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Missing file");
        }
    }
}
