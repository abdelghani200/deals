package com.example.deals.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record DealRequestDto (

    @NotBlank(message = "Deal ID is required.")
    String id,

    @NotBlank(message = "From Currency ISO Code is required.")
    String orderingCurrencyIsoCode,

    @NotBlank(message = "To Currency ISO Code is required.")
    String toCurrencyIsoCode,

    @NotNull(message = "Deal amount is required.")
    @Positive(message = "Deal amount must be positive")
    BigDecimal amount


) {}
