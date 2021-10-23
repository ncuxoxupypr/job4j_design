package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private int point = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((double) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int keyIndex = indexFor(hash(key));
        if (table[keyIndex] != null) {
            return false;
        }
        table[keyIndex] = new MapEntry<>(key, value);
        modCount++;
        count++;
        return true;
    }

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] tableTmp = table;
        capacity = capacity * 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> tab : tableTmp) {
            if (tab != null) {
                int index = indexFor(hash(tab.getKey()));
                table[index] = tab;
            }
        }
        modCount++;
    }

    @Override
    public V get(K key) {
        int keyIndex = indexFor(hash(key));
        if (key != null && table[keyIndex] != null) {
            if (table[keyIndex].getKey().equals(key)) {
                return table[keyIndex].getValue();
            }
        }
        modCount++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        int keyIndex = indexFor(hash(key));
        if (key != null && table[keyIndex] != null) {
            if (table[keyIndex].getKey().equals(key)) {
                table[keyIndex] = null;
                modCount++;
                count--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length) {
                    if (table[point] != null) {
                        return true;
                    }
                    point++;
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].getKey();
            }
        };
    }

    private static class MapEntry<K, V> {

        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

}