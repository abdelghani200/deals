package com.example.deals.service;

import com.example.deals.dto.DealRequestDto;

public interface DealValidationService {

    void validateDealNotExists(DealRequestDto dealRequestDto);

    void validateCurrencies(String fromCurrency, String toCurrency);

}
