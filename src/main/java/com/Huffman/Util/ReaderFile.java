package com.Huffman.Util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class ReaderFile implements Reader<File, String> {

    @Override
    public String read(File file) {
        try {
            return Files.readAllLines(file.toPath(), Charset.forName("utf8"))
                    .stream()
                    .collect(Collectors.joining());
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
