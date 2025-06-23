package com.sparta.dev.java_02_p01.domain.refund.entity;

import com.sparta.dev.java_02_p01.common.enums.RefundStatus;
import com.sparta.dev.java_02_p01.domain.purchase.entity.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refund {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  Purchase purchase;

  @Column(nullable = false)
  String reason;

  @Column(nullable = false)
  RefundStatus status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime updatedAt;

  @Builder
  public Refund(
      Purchase purchase,
      String reason,
      RefundStatus status
  ) {
    this.purchase = purchase;
    this.reason = reason;
    this.status = status;
  }

  public void setPurchase(Purchase purchase) {
    if (purchase != null) {
      this.purchase = purchase;
    }
  }

  public void setReason(String reason) {
    if (StringUtils.hasText(reason)) {
      this.reason = reason;
    }
  }

  public void setStatus(RefundStatus status) {
    if (!ObjectUtils.isEmpty(status)) {
      this.status = status;
    }
  }

}
