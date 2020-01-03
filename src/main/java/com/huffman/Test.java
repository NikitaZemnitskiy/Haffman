package com.huffman;


import java.util.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
       // String s = buildString();
        String s = "Никита гулял по лесу и обнаружил чужую закладку по синей изо под кучей осенних листьев";
        long start = System.currentTimeMillis();
        System.out.println(createDefault(s));
        System.out.println("Defailt: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(createNew(s));
        System.out.println("New " + (System.currentTimeMillis() - start) + "ms");
    }

    public static Map<Character, Integer> createDefault(String text) {
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
        return result;
    }
    public static Map<Character, Integer> createNew(String text) {

        Map<Character, Integer> charMap = new TreeMap<>();
        char[] charsArr = text.toCharArray();
        for (char c : charsArr) {
            if(charMap.containsKey(c)){
                charMap.put(c,charMap.get(c)+1);
            }
            else
                charMap.put(c, 1);
        }
        Map<Character, Integer> result = new LinkedHashMap<>();
        Stream<Map.Entry<Character, Integer>> st = charMap.entrySet().stream();
        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }


    private static String buildString() {
        Random rnd = new Random();
        char[] buf = new char[100_000_000];
        for (int i = 0; i < buf.length; i++)
            buf[i] = (char)(rnd.nextInt(127 - 33) + 33);
        return new String(buf);
    }
}

