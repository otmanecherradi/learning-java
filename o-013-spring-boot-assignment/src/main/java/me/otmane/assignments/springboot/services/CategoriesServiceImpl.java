package me.otmane.assignments.springboot.services;


import me.otmane.assignments.springboot.core.ICategoriesService;
import me.otmane.assignments.springboot.entities.Category;
import me.otmane.assignments.springboot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements ICategoriesService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> byPk(Long pk) {
        return categoryRepository.findById(pk);
    }

    @Override
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
