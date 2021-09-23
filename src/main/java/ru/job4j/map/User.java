package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user = new User("Alisa", 3, new GregorianCalendar(2018, Calendar.JULY, 16));
        User user1 = new User("Alisa", 3, new GregorianCalendar(2018, Calendar.JULY, 16));
        System.out.println("Hashcode user: " + user.hashCode());
        System.out.println("Hashcode user1: " + user1.hashCode());
        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(user1, new Object());
        map.forEach((key, value) -> System.out.println("key: " + key + " value: " + value));
    }

    @Override
    public String toString() {
        return "User {" + "name='" + name + '\'' + ", children=" + children + ", birthday=" + birthday + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }
}
