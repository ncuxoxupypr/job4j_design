package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private T[] dataElement;
    private int size;
    private int counter;

    public SimpleArray() {
        dataElement = (T[]) new Object[10];
        size = 0;
    }

    public SimpleArray(int size) {
        dataElement = (T[]) new Object[size];
        this.size = 0;
    }

    public T get(int index) {
        if (!checkIndex(index, size)) {
            throw new IndexOutOfBoundsException();
        }
        return dataElement[index];
    }

    public void add(T model) {
        if (size == dataElement.length) {
            grow();
        }
        dataElement[size] = model;
        size++;
        counter++;
    }

    private boolean checkIndex(int index, int size) {
        return index >= 0 && index < size;
    }

    private void grow() {
        T[] array = (T[]) new Object[dataElement.length + (dataElement.length / 2)];
        System.arraycopy(dataElement, 0, array, 0, dataElement.length);
        dataElement = array;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private int expectCount = counter;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectCount != counter) {
                    throw new ConcurrentModificationException();
                }
                return dataElement[point++];
            }
        };
    }
}
