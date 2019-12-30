package com.huffman;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;



public class Application {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        if (args.length != 1) {
            throw new IllegalStateException("Введено больше 1 аргумента, введите 1 аргумент, " +
                    "который будет путем к файлу, который необходимо сжать " +
                    "или вернуть в исходное состояние");
        }
        File file = new File(args[0]);
        if(!file.exists()){
            throw new IllegalStateException("Введите путь к файлу, который хотите закодировать или путь к файлу" +
                    "с расширеньем '.hf', если хотите файл раскодировать");
        }
        if (file.getName().endsWith(".hf")) {
            logger.trace("Файл имеет расширение - 'hf'. Запуск декодирования");
            Decoding decoding = new Decoding(file);
            decoding.decode();


        } else {
            logger.trace("Файл не имеет расширение - 'hf'. Запуск кодирования");
            Encoding encoding = new Encoding (file);
            encoding.treeCreating();
        }
    }
}
