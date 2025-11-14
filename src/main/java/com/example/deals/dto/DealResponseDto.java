package com.example.deals.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record DealResponseDto (


    String id,

    @JsonProperty("from_currency")
    String orderingCurrencyIsoCode,

    @JsonProperty("to_currency")
    String toCurrencyIsoCode,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime dealTimestamp,

    @JsonProperty("amount")
    BigDecimal amount

) {}
