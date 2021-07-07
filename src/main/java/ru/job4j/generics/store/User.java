package ru.job4j.generics.store;

public class User extends Base {
    private String id;
    private int age;

    public User(String id, int age) {
        super(id);
        this.age = age;
    }
}
