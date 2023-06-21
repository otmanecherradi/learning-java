package me.otmane.assignments.springboot.core;

import me.otmane.assignments.springboot.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductsService {

    Product save(Product product);

    Optional<Product> byPk(Long pk);

    List<Product> list();

    void delete(Product product);
}
