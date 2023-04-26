package me.otmane.assignment.core;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class Entity {
  @Column(name = "pk", primaryKey = true, insertable = false, updatable = false)
  protected Long pk;
}
