package me.otmane;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
