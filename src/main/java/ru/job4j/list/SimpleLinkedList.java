package ru.job4j.list;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> head;
    private int size = 0;
    private int modCount = 0;

    @Override
    public void add(E value) {
        modCount++;
        size++;
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new Node<>(value, null);
        }
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> resultNode = head;
        for (int i = 0; i < index; i++) {
            resultNode = resultNode.next;
        }
        return resultNode.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> node = head;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E elem = node.value;
                node = node.next;
                return elem;
            }
        };
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}