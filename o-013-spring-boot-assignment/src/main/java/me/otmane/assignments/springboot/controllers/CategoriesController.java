package me.otmane.assignments.springboot.controllers;

import me.otmane.assignments.springboot.controllers.dto.CategoryDto;
import me.otmane.assignments.springboot.core.ICategoriesService;
import me.otmane.assignments.springboot.entities.Category;
import me.otmane.assignments.springboot.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoriesController {

    @Autowired
    private ICategoriesService categoriesService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok(categoriesService.list());
    }

    @GetMapping(value = "/{pk}/")
    public ResponseEntity<Category> byPk(@PathVariable final Long pk) {
        final Optional<Category> maybeCategory = categoriesService.byPk(pk);

        return maybeCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/")
    public ResponseEntity<Category> create(@RequestBody final CategoryDto dto) {
        Category category = Category.builder()
                .name(dto.name())
                .build();

        category = categoriesService.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping(value = "/{pk}/")
    public ResponseEntity<Category> update(@PathVariable final Long pk, @RequestBody final CategoryDto dto) {
        final Optional<Category> maybeCategory = categoriesService.byPk(pk);

        if (maybeCategory.isEmpty())
            return ResponseEntity.notFound().build();

        Category category = maybeCategory.get();

        category.setName(dto.name());

        category = categoriesService.save(category);

        return ResponseEntity.accepted().body(category);
    }

    @DeleteMapping(value = "/{pk}/")
    public ResponseEntity<Object> delete(@PathVariable final Long pk) {
        final Optional<Category> maybeCategory = categoriesService.byPk(pk);

        if (maybeCategory.isEmpty())
            return ResponseEntity.notFound().build();

        categoriesService.delete(maybeCategory.get());

        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/{pk}/products/")
    public ResponseEntity<List<Product>> products(@PathVariable final Long pk) {
        final Optional<Category> maybeCategory = categoriesService.byPk(pk);

        return maybeCategory.map(category -> ResponseEntity.ok(category.getProducts()))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
