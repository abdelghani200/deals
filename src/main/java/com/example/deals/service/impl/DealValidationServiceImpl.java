package com.example.deals.service.impl;

import com.example.deals.dto.DealRequestDto;
import com.example.deals.exception.DuplicateDealException;
import com.example.deals.repository.DealRepository;
import com.example.deals.service.DealValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealValidationServiceImpl implements DealValidationService {

    private final DealRepository repository;
    @Override
    public void validateDealNotExists(DealRequestDto dealRequestDto) {
        if (repository.existsById(dealRequestDto.id())) {
            throw new DuplicateDealException(dealRequestDto.id());
        }
    }

    @Override
    public void validateCurrencies(String fromCurrency, String toCurrency) {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Currencies cannot be null.");
        }
        if (fromCurrency.equals(toCurrency)) {
            throw new IllegalArgumentException("Source and target currencies cannot be the same.");
        }
    }

}
