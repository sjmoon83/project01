package com.sparta.dev.java_02_p01.domain.purchase.entity;

import com.sparta.dev.java_02_p01.common.enums.PurchaseStatus;
import com.sparta.dev.java_02_p01.domain.purchaseProduct.entity.PurchaseProduct;
import com.sparta.dev.java_02_p01.domain.refund.entity.Refund;
import com.sparta.dev.java_02_p01.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
import org.springframework.util.ObjectUtils;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  PurchaseStatus status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
  List<PurchaseProduct> purchaseProduct = new ArrayList<>();

  @OneToOne(mappedBy = "purchase", fetch = FetchType.LAZY)
  Refund refund;

  @Builder
  public Purchase(
      User user,
      PurchaseStatus status
  ) {
    this.user = user;
    this.status = status;
  }

  public void setUser_id(User user) {
    if (!ObjectUtils.isEmpty(user)) {
      this.user = user;
    }
  }

  public void setStatus(PurchaseStatus status) {
    if (status != null) {
      this.status = status;
    }
  }

}
