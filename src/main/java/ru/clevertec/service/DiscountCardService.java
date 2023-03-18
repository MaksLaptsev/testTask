package ru.clevertec.service;

import ru.clevertec.dao.DiscountCardDao;
import ru.clevertec.entity.DiscountCard;

public class DiscountCardService implements Service<DiscountCard>{
    private DiscountCardDao cardDao;

    public DiscountCardService(){
        cardDao = new DiscountCardDao();
    }

    @Override
    public DiscountCard get(int id) {
        return cardDao.get(id);
    }

    @Override
    public void put(DiscountCard card) {
        cardDao.add(card);
    }

    @Override
    public void update(DiscountCard card) {
        cardDao.update(card);
    }

    @Override
    public void delete(int id) {
        cardDao.delete(id);
    }
}
