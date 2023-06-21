package me.otmane.assignments.springboot.controllers;

import me.otmane.assignments.springboot.controllers.dto.CategoryDto;
import me.otmane.assignments.springboot.controllers.dto.ProductDto;
import me.otmane.assignments.springboot.core.ICategoriesService;
import me.otmane.assignments.springboot.core.IProductsService;
import me.otmane.assignments.springboot.entities.Category;
import me.otmane.assignments.springboot.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductsController {

    @Autowired
    private ICategoriesService categoriesService;

    @Autowired
    private IProductsService productsService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productsService.list());
    }

    @GetMapping(value = "/{pk}/")
    public ResponseEntity<Product> byPk(@PathVariable final Long pk) {
        final Optional<Product> maybeProduct = productsService.byPk(pk);

        return maybeProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/")
    public ResponseEntity<Product> create(@RequestBody final ProductDto dto) {
        final Optional<Category> maybeCategory = categoriesService.byPk(dto.categoryPk());

        if (maybeCategory.isEmpty())
            return ResponseEntity.notFound().build();

        Product product = Product.builder()
                .name(dto.name())
                .category(maybeCategory.get())
                .build();

        product = productsService.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping(value = "/{pk}/")
    public ResponseEntity<Product> update(@PathVariable final Long pk, @RequestBody final ProductDto dto) {
        final Optional<Product> maybeProduct = productsService.byPk(pk);

        if (maybeProduct.isEmpty())
            return ResponseEntity.notFound().build();

        final Optional<Category> maybeCategory = categoriesService.byPk(dto.categoryPk());

        if (maybeCategory.isEmpty())
            return ResponseEntity.notFound().build();

        Product product = maybeProduct.get();

        product.setName(dto.name());
        product.setCategory(maybeCategory.get());

        product = productsService.save(product);

        return ResponseEntity.accepted().body(product);
    }

    @DeleteMapping(value = "/{pk}/")
    public ResponseEntity<Object> delete(@PathVariable final Long pk) {
        final Optional<Product> maybeProduct = productsService.byPk(pk);

        if (maybeProduct.isEmpty())
            return ResponseEntity.notFound().build();

        productsService.delete(maybeProduct.get());

        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/{pk}/category/")
    public ResponseEntity<Category> category(@PathVariable final Long pk) {
        final Optional<Product> maybeProduct = productsService.byPk(pk);

        return maybeProduct.map(product -> ResponseEntity.ok(product.getCategory()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
