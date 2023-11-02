package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message="this field is required")
    @Size(min=3, message="product name must be at least 3 letters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "price must be greater than or equal to zero")
    @Column(name = "price")
    private float price;

    @Min(value = 1, message = "remaining quantity must be a positive integer")
    @Column(name = "remaining_quantity")
    private int remainingQuantity;

    @Column(name = "description")
    private String description;

    @NotNull(message="this field is required")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image")
    private String image;

    @Transient
    public String getPhotosImagePath() {
        if (image == null) return null;

        return "/system-images/products/" + image;
    }

    public Product() {

    }

    public Product(String name, float price, int remainingQuantity, String description, Category category) {
        this.name = name;
        this.price = price;
        this.remainingQuantity = remainingQuantity;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", remainingQuantity=" + remainingQuantity +
                ", description='" + description + '\'' +
//                ", category=" + category +
                '}';
    }
}
