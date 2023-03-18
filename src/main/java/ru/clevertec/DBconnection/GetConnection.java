package ru.clevertec.DBconnection;

import ru.clevertec.entity.Cart;
import ru.clevertec.entity.DiscountCard;
import ru.clevertec.entity.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetConnection {
    private static SessionFactory instance = null;

    private GetConnection(){
        instance  = new Configuration()
                .addAnnotatedClass(DiscountCard.class)
                .addAnnotatedClass(Cart.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public static SessionFactory getInstance(){
        if(instance == null){
            synchronized (GetConnection.class){
                if (instance == null){
                    new GetConnection();
                }
            }
        }
        return instance;
    }

}
