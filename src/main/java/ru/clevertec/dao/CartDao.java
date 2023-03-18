package ru.clevertec.dao;

import ru.clevertec.DBconnection.GetConnection;
import ru.clevertec.annotation.CrudAnnotation;
import ru.clevertec.annotation.MethodType;
import ru.clevertec.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class CartDao implements Dao<Cart> {

    private final SessionFactory sessionFactory;
    private Transaction transaction = null;

    public CartDao(){
        sessionFactory = GetConnection.getInstance();
    }

    @Override
    @CrudAnnotation(type = MethodType.GET)
    public Cart get(int id) {
         Session session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         Cart cart = session.get(Cart.class,id);
         transaction.commit();
         session.close();
         return getOptional(cart).orElse(new Cart());
    }

    @Override
    @CrudAnnotation(type = MethodType.GET)
    public Cart get(Cart object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Cart cart = session.get(Cart.class,object.getId());
        transaction.commit();
        session.close();
        return getOptional(cart).orElse(new Cart());
    }

    @Override
    @CrudAnnotation(type = MethodType.POST)
    public void add(Cart object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.DELETE)
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.delete(String.valueOf(Cart.class),id);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.DELETE)
    public void delete(Cart object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.PUT)
    public void update(Cart object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(object);
        transaction.commit();
        session.close();
    }
    @CrudAnnotation(type = MethodType.POST)
    public Integer saveAndGetId(Cart object) {
        Integer id;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        id = (Integer) session.save(object);
        transaction.commit();
        session.close();
        return id;
    }

    private Optional<Cart> getOptional(Cart cart){
        return Optional.ofNullable(cart);
    }

}
