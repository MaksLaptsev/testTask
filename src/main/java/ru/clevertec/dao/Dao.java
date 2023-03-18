package ru.clevertec.dao;

public interface Dao<T> {
    T get(int id);
    T get(T object);
    void add(T object);
    void delete(int id);
    void delete(T object);
    void update(T object);
    Integer saveAndGetId(T object);
}
