package me.otmane.assignments.springboot.services;



import me.otmane.assignments.springboot.core.IProductsService;
import me.otmane.assignments.springboot.entities.Product;
import me.otmane.assignments.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements IProductsService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> byPk(Long pk) {
        return productRepository.findById(pk);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
