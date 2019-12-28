package com.Huffman.Util;

import com.Huffman.CodeTable;

public interface Writer<T,E> {
    public void write(T t, E e);
}
