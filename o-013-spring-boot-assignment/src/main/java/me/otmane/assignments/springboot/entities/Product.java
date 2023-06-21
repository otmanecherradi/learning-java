package me.otmane.assignments.springboot.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.otmane.assignments.springboot.entities.serializers.ProductCategorySerializer;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk", insertable = false, updatable = false)
    private Long pk;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_pk")
    @JsonSerialize(using = ProductCategorySerializer.class)
    private Category category;

    @CreationTimestamp()
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
}
