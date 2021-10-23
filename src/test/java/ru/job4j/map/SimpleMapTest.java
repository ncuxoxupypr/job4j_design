package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void put() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "Ivanov"));
        assertTrue(map.put(2, "Petrov"));
    }

    @Test
    public void putMulti() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "Ivanov"));
        assertTrue(map.put(2, "Petrov"));
        assertTrue(map.put(3, "qqq"));
        assertTrue(map.put(4, "aaa"));
        assertTrue(map.put(5, "zzz"));
        assertTrue(map.put(6, "xxx"));
    }

    @Test
    public void get() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "Ivanov"));
        assertTrue(map.put(2, "Petrov"));
        assertNull(map.get(7));
        assertThat(map.get(2), is("Petrov"));
    }

    @Test
    public void getMulti() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Ivanov");
        map.put(2, "Petrov");
        assertNull(map.get(7));
        assertThat(map.get(2), is("Petrov"));
        map.put(3, "Sidorov");
        map.put(4, "Davydoff");
        map.remove(3);
        assertNull(map.get(3));
        assertThat(map.get(4), is("Davydoff"));
    }

    @Test
    public void remove() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Ivanov");
        map.put(2, "Petrov");
        map.put(3, "qqq");
        map.put(4, "aaa");
        map.put(5, "zzz");
        map.put(6, "xxx");
        assertFalse(map.remove(7));
        assertTrue(map.remove(5));
    }

    @Test
    public void removeMulti() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Ivanov");
        map.put(2, "Petrov");
        map.put(3, "qqq");
        map.put(4, "aaa");
        map.put(5, "zzz");
        map.put(6, "xxx");
        assertFalse(map.remove(7));
        assertTrue(map.remove(5));
        map.remove(5);
        assertFalse(map.remove(5));
        assertNull(map.get(5));
    }

    @Test
    public void iterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Ivanov");
        map.put(2, "Petrov");
        assertThat(map.iterator().hasNext(), is(true));
        map.iterator().next();
        assertThat(map.iterator().hasNext(), is(true));
        map.iterator().next();
        assertThat(map.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorWhenEmpty() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorWhenModify() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        map.put(1, "aaa");
        it.next();
    }
}