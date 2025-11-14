package com.example.deals.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fx_deals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

    @Id
    @Column(nullable = false, updatable = false)
    @NotBlank(message = "Deal ID cannot be blank")
    private String id;

    @NotNull(message = "Ordering currency isi code must not be null")
    @NotEmpty(message = "Ordering currency iso code must not be empty")
    @Size(max = 255, min = 3, message = "Deal is should be between 3 and 255 character.")
    @Column(nullable = false)
    private String orderingCurrencyIsoCode;

    @NotNull(message = "To currency iso code must not be null")
    @NotEmpty(message = "To currency iso code must not be empty")
    @Size(max = 255, min = 3, message = "Ordering currency iso code is should be between 3 and 255 character.")
    @Column(nullable = false)
    private String toCurrencyIsoCode;

    @CreationTimestamp
    private LocalDateTime dealTimestamp;

    @Column(nullable = false, precision = 19, scale = 4)
    @NotNull(message = "Deal amount cannot be null")
    @Positive(message = "Deal amount must be positive")
    private BigDecimal amount;

}
