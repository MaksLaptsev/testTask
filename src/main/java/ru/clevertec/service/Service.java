package ru.clevertec.service;

public interface Service<T> {
    T get(int id);
    void put(T t);
    void update(T t);
    void delete(int id);
}
