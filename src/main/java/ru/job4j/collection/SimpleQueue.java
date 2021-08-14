package ru.job4j.collection;

import ru.job4j.list.SimpleStack;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;
    private int exp = 0;

    public T poll() {
        if (exp == 0) {
            while (exp != size) {
                T rsl = in.pop();
                out.push(rsl);
                exp++;
            }
            size = 0;
        }
        exp--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
