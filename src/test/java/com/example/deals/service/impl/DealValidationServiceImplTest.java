package com.example.deals.service.impl;

import com.example.deals.dto.DealRequestDto;
import com.example.deals.exception.DuplicateDealException;
import com.example.deals.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealValidationServiceImplTest {

    private DealValidationServiceImpl validationService;

    @Mock
    private DealRepository dealRepository;

    private DealRequestDto dealRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validationService = new DealValidationServiceImpl(dealRepository);

        dealRequest = new DealRequestDto(
                "DEAL-001",
                "USD",
                "EUR",
                null
        );
    }

    @Test
    void validateDealNotExists_ShouldPass_WhenDealDoesNotExist() {
        when(dealRepository.existsById("DEAL-001")).thenReturn(false);

        assertDoesNotThrow(() -> validationService.validateDealNotExists(dealRequest));
        verify(dealRepository, times(1)).existsById("DEAL-001");
    }

    @Test
    void validateDealNotExists_ShouldThrow_WhenDealExists() {
        when(dealRepository.existsById("DEAL-001")).thenReturn(true);

        assertThrows(DuplicateDealException.class, () -> validationService.validateDealNotExists(dealRequest));
        verify(dealRepository, times(1)).existsById("DEAL-001");
    }


    @Test
    void validateCurrencies_ShouldPass_WhenDifferentCurrencies() {
        assertDoesNotThrow(() -> validationService.validateCurrencies("USD", "EUR"));
    }

    @Test
    void validateCurrencies_ShouldThrow_WhenFromCurrencyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> validationService.validateCurrencies(null, "EUR"));
    }

    @Test
    void validateCurrencies_ShouldThrow_WhenToCurrencyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> validationService.validateCurrencies("USD", null));
    }

    @Test
    void validateCurrencies_ShouldThrow_WhenCurrenciesAreTheSame() {
        assertThrows(IllegalArgumentException.class, () -> validationService.validateCurrencies("USD", "USD"));
    }
}