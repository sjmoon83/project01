package com.sparta.dev.java_02_p01.domain.product.entity;

import com.sparta.dev.java_02_p01.domain.cart.entity.Cart;
import com.sparta.dev.java_02_p01.domain.category.entity.Category;
import com.sparta.dev.java_02_p01.domain.purchaseProduct.entity.PurchaseProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String name;

  @Column(nullable = false)
  Long price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  Category category;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  List<Cart> cart = new ArrayList<>();

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  List<PurchaseProduct> purchaseProduct = new ArrayList<>();

  @Builder
  public Product(
      String name,
      Long price,
      Category category
  ) {
    this.name = name;
    this.price = price;
    this.category = category;
  }

  public void setName(String name) {
    if (StringUtils.hasText(name)) {
      this.name = name;
    }
  }

  public void setPrice(Long price) {
    if (price != null) {
      this.price = price;
    }
  }

  public void setCategory(Category category) {
    if (category != null) {
      this.category = category;
    }
  }


}
