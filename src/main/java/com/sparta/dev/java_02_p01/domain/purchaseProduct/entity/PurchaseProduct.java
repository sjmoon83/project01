package com.sparta.dev.java_02_p01.domain.purchaseProduct.entity;

import com.sparta.dev.java_02_p01.domain.product.entity.Product;
import com.sparta.dev.java_02_p01.domain.purchase.entity.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  Purchase purchase;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  Product product;

  @Column(nullable = false)
  Long quantity;

  @Builder
  public PurchaseProduct(
      Purchase purchase,
      Product product,
      Long quantity
  ) {
    this.purchase = purchase;
    this.product = product;
    this.quantity = quantity;
  }

  public void setPurchase(Purchase purchase) {
    if (purchase != null) {
      this.purchase = purchase;
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
