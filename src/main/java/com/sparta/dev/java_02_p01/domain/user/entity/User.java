package com.sparta.dev.java_02_p01.domain.user.entity;

import com.sparta.dev.java_02_p01.domain.cart.entity.Cart;
import com.sparta.dev.java_02_p01.domain.purchase.entity.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String email;

  @Column(nullable = false)
  String password;

  @Column(nullable = false)
  String name;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Cart> cart = new ArrayList<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Purchase> purchase = new ArrayList<>();

  @Builder
  public User(
      String email,
      String password,
      String name) {
    this.email = email;
    this.password = password;
    this.name = name;
  }

  public void setEmail(String email) {
    if (StringUtils.hasText(email)) {
      this.email = email;
    }
  }

  public void setPassword(String password) {
    if (StringUtils.hasText(password)) {
      this.password = password;
    }
  }

  public void setName(String name) {
    if (StringUtils.hasText(name)) {
      this.name = name;
    }
  }
}
