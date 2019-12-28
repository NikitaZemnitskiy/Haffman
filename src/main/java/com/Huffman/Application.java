package com.Huffman;

import com.Huffman.Util.ReaderFile;
import com.Huffman.Util.WriterBitSetToFile;
import com.Huffman.Util.WriterStringToFile;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;


import java.io.File;



public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        if (args.length != 1) {
            logger.error("Введено больше 1 аргумента, введите 1 аргумент, " +
                    "который будет путем к файлу, который необходимо сжать " +
                    "или вернуть в исходное состояние");
            throw new IllegalStateException();
        }
        File file = new File(args[0]);
        if(!file.exists()){
            logger.error("Введите коректный путь к файлу");
            throw new IllegalStateException();
        }
        if (file.getName().endsWith(".hf")) {
            logger.trace("Файл имеет расширение - 'hf'. Запуск декодирования");
            Decoding decoding = new Decoding(file, new ReaderFile(), new WriterStringToFile());
            decoding.decode();


        } else {
            logger.trace("Файл не имеет расширение - 'hf'. Запуск кодирования");
            Encoding encoding = new Encoding(new ReaderFile(), file, new WriterBitSetToFile());
            encoding.treeCreating();
        }
    }
}
