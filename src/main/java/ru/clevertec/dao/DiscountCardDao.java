package ru.clevertec.dao;

import ru.clevertec.DBconnection.GetConnection;
import ru.clevertec.annotation.CrudAnnotation;
import ru.clevertec.annotation.MethodType;
import ru.clevertec.entity.DiscountCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class DiscountCardDao implements Dao<DiscountCard>{
    private final SessionFactory sessionFactory;
    private Transaction transaction = null;

    public DiscountCardDao(){
        sessionFactory = GetConnection.getInstance();
    }

    @Override
    @CrudAnnotation(type = MethodType.GET)
    public DiscountCard get(int id) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        DiscountCard card = session.get(DiscountCard.class,id);
        transaction.commit();
        session.close();
        return getOptional(card).orElse(new DiscountCard());
    }

    @Override
    @CrudAnnotation(type = MethodType.GET)
    public DiscountCard get(DiscountCard object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        DiscountCard card = session.get(DiscountCard.class,object.getId());
        transaction.commit();
        session.close();
        return getOptional(card).orElse(new DiscountCard());
    }

    @Override
    @CrudAnnotation(type = MethodType.POST)
    public void add(DiscountCard object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.PUT)
    public void update(DiscountCard object) {
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
        session.delete(String.valueOf(DiscountCard.class),id);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.DELETE)
    public void delete(DiscountCard object) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
    }

    @Override
    @CrudAnnotation(type = MethodType.POST)
    public Integer saveAndGetId(DiscountCard object) {
        Integer id;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        id = (Integer) session.save(object);
        transaction.commit();
        session.close();
        return id;
    }

    private Optional<DiscountCard> getOptional(DiscountCard d){
       return Optional.ofNullable(d);
    }
}
