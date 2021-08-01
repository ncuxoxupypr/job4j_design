package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private T[] dataElement;
    private int size;

    public SimpleArray() {
        dataElement = (T[]) new Object[10];
        size = 0;
    }

    public T get(int index) {
        if (!checkIndex(index, size)) {
            throw new IndexOutOfBoundsException();
        }
        return dataElement[index];
    }

    public void add(T model) {
        if (size == dataElement.length) {
            dataElement = Arrays.copyOf(dataElement, dataElement.length * 2);
        }
        dataElement[size++] = model;
    }

    private boolean checkIndex(int index, int size) {
        return index >= 0 && index < size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private int expectCount = size;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectCount != size) {
                    throw new ConcurrentModificationException();
                }
                return dataElement[point++];
            }
        };
    }
}
