package com.sparta.dev.java_02_p01.domain.cart.entity;

import com.sparta.dev.java_02_p01.domain.product.entity.Product;
import com.sparta.dev.java_02_p01.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  Product product;

  @Column(nullable = false)
  Long quantity;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime updatedAt;

  public Cart(
      User user,
      Product product,
      Long quantity
  ) {
    this.user = user;
    this.product = product;
    this.quantity = quantity;
  }

  @Builder

  public void setUser(User user) {
    if (user != null) {

      this.user = user;
    }
  }

  public void setProduct(Product product) {
    if (product != null) {
      this.product = product;
    }
  }

  public void setQuantity(Long quantity) {
    if (quantity != null) {
      this.quantity = quantity;
    }
  }

}
