package ru.clevertec.dao;

import ru.clevertec.DBconnection.GetConnection;
import ru.clevertec.annotation.CrudAnnotation;
import ru.clevertec.annotation.MethodType;
import ru.clevertec.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Optional;

public class ProductDao implements Dao<Product>{
    private final SessionFactory sessionFactory;
    private Transaction transaction = null;

    public ProductDao(){
        sessionFactory = GetConnection.getInstance();
    }

    @Override
    @CrudAnnotation(type = MethodType.GET)
    public Product get(int id) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Product product = session.get(Product.class,id);
        transaction.commit();
        session.close();
        return getOptional(product).orElse(new Product());
    }

    @Override
    @CrudAnnotation(type = MethodType.GET)
    public Product get(Product object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Product product = session.get(Product.class,object.getId());
        transaction.commit();
        session.close();
        return getOptional(product).orElse(new Product());
    }

    @Override
    @CrudAnnotation(type = MethodType.POST)
    public void add(Product object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.PUT)
    public void update(Product object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.DELETE)
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.delete(String.valueOf(Product.class),id);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.DELETE)
    public void delete(Product object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.POST)
    public Integer saveAndGetId(Product object) {
        Integer id;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        id =(Integer) session.save(object);
        transaction.commit();
        session.close();
        return id;
    }

    private Optional<Product> getOptional(Product d){
        return Optional.ofNullable(d);
    }
}
