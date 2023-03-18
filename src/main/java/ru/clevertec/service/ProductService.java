package ru.clevertec.service;

import ru.clevertec.dao.ProductDao;
import ru.clevertec.entity.Product;

public class ProductService implements Service<Product>{
    private ProductDao productDao;

    public ProductService(){
        productDao = new ProductDao();
    }

    @Override
    public Product get(int id) {
        return productDao.get(id);
    }

    @Override
    public void put(Product product) {
        productDao.add(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }
}
