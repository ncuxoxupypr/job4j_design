package ru.job4j.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = -1;
    private int nextPoint = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point != nextIndex();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        point = nextPoint;
        return data[point];
    }

    public int nextIndex() {
        for (int i = point + 1; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                nextPoint = i;
                break;
            }
        }
        return nextPoint;
    }
}
