package me.otmane.assignments.springboot.core;

import me.otmane.assignments.springboot.entities.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoriesService {

    Category save(Category category);

    Optional<Category> byPk(Long pk);

    List<Category> list();

    void delete(Category category);
}
