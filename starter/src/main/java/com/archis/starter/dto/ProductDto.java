package com.archis.starter.dto;

import com.archis.starter.entity.Category;
import com.archis.starter.entity.Comment;
import com.archis.starter.entity.Product;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private String photoUrl;
    private double price;
    private List<Comment> comments;
    private CategoryDto category;

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", price=" + price +
                ", comments=" + comments +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
