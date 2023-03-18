package ru.clevertec.service;

import ru.clevertec.dao.CartDao;
import ru.clevertec.entity.Cart;

public class CartService implements Service<Cart> {
    private CartDao cartDao;

    public CartService() {
        this.cartDao = new CartDao();
    }

    @Override
    public Cart get(int id) {
        return cartDao.get(id);
    }

    @Override
    public void put(Cart cart) {
        cartDao.add(cart);
    }

    @Override
    public void update(Cart cart) {
        cartDao.update(cart);
    }

    @Override
    public void delete(int id) {
        cartDao.delete(id);
    }

    public Integer saveAndGetId(Cart cart){
       return cartDao.saveAndGetId(cart);
    }

}
