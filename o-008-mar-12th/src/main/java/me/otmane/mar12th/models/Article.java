package me.otmane.mar12th.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Article {
  private long pk;
  private String title;
  private float price;
  private String description;

  @Override
  public String toString() {
    return "Article{" +
        "pk=" + pk +
        ", title='" + title + '\'' +
        ", price=" + price +
        ", description='" + description + '\'' +
        '}';
  }
}
