package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> first = head;
        final T element = first.value;
        final Node<T> next = first.next;
        first.value = null;
        first.next = null;
        head = next;
        return element;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> nextNode = head.next;
        head.next = null;
        while (nextNode != null) {
            Node<T> node = nextNode.next;
            nextNode.next = head;
            head = nextNode;
            nextNode = node;
        }
        return true;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail;
        tail = head;
        head = node;
        head.next = tail;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
