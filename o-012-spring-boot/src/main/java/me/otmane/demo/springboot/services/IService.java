package me.otmane.demo.springboot.services;

import java.util.List;
import java.util.Optional;

public interface IService<Entity, PkType> {
    Entity create(Entity entity);
    List<Entity> list();
    Optional<Entity> byPk(PkType pk);

    void delete(PkType pk);
}
