package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.util.Objects.checkIndex;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] elements;
    private int pos = 0;

    public SimpleArray(int size) {
        this.elements = new Object[size];
    }

    public void add(T model) throws IndexOutOfBoundsException {
        elements[pos++] = model;
    }

    public void set(int index, T model) throws IndexOutOfBoundsException {
        checkIndex(index, pos);
        elements[index] = model;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, pos);
        return (T) elements[index];
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index, pos);
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        elements[--this.pos] = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < pos;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[counter++];
            }
        };
    }
}
