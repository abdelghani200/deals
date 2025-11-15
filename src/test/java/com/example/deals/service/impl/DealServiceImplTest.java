package com.example.deals.service.impl;

import com.example.deals.dto.DealRequestDto;
import com.example.deals.dto.DealResponseDto;
import com.example.deals.entity.Deal;
import com.example.deals.mapper.DealMapper;
import com.example.deals.repository.DealRepository;
import com.example.deals.service.DealValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealServiceImplTest {

    @Mock
    private DealRepository repository;

    @Mock
    private DealMapper mapper;

    @Mock
    private DealValidationService validationService;

    @InjectMocks
    private DealServiceImpl dealService;

    private DealRequestDto requestDto;
    private Deal dealEntity;
    private Deal savedDeal;
    private DealResponseDto responseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        requestDto = new DealRequestDto(
                "DEAL123",
                "USD",
                "EUR",
                BigDecimal.valueOf(100.50)
        );

        dealEntity = new Deal();
        dealEntity.setId("DEAL123");
        dealEntity.setOrderingCurrencyIsoCode("USD");
        dealEntity.setToCurrencyIsoCode("EUR");
        dealEntity.setAmount(BigDecimal.valueOf(100.50));

        savedDeal = new Deal();
        savedDeal.setId("DEAL123");
        savedDeal.setOrderingCurrencyIsoCode("USD");
        savedDeal.setToCurrencyIsoCode("EUR");
        savedDeal.setAmount(BigDecimal.valueOf(100.50));
        savedDeal.setDealTimestamp(LocalDateTime.now());

        responseDto = new DealResponseDto(
                "DEAL123",
                "USD",
                "EUR",
                savedDeal.getDealTimestamp(),
                BigDecimal.valueOf(100.50)
        );
    }

    @Test
    void testCreateDeal_Success() {
        doNothing().when(validationService).validateDealNotExists(requestDto);
        doNothing().when(validationService).validateCurrencies("USD", "EUR");
        when(mapper.toEntity(requestDto)).thenReturn(dealEntity);
        when(repository.save(dealEntity)).thenReturn(savedDeal);
        when(mapper.toDto(savedDeal)).thenReturn(responseDto);

        DealResponseDto result = dealService.createDeal(requestDto);

        assertNotNull(result);
        assertEquals("DEAL123", result.id());
        verify(validationService).validateDealNotExists(requestDto);
        verify(validationService).validateCurrencies("USD", "EUR");
        verify(repository).save(dealEntity);
        verify(mapper).toDto(savedDeal);
    }

    @Test
    void testCreateDeal_DealAlreadyExists() {
        doThrow(new RuntimeException("Deal already exists"))
                .when(validationService).validateDealNotExists(requestDto);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> dealService.createDeal(requestDto));

        assertEquals("Deal already exists", ex.getMessage());
        verifyNoInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void testCreateDeal_InvalidCurrency() {
        doNothing().when(validationService).validateDealNotExists(requestDto);
        doThrow(new RuntimeException("Invalid currency"))
                .when(validationService).validateCurrencies("USD", "EUR");

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> dealService.createDeal(requestDto));

        assertEquals("Invalid currency", ex.getMessage());
        verifyNoInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void testCreateDeal_RepositoryFailure() {
        doNothing().when(validationService).validateDealNotExists(requestDto);
        doNothing().when(validationService).validateCurrencies("USD", "EUR");
        when(mapper.toEntity(requestDto)).thenReturn(dealEntity);
        when(repository.save(dealEntity)).thenThrow(new RuntimeException("DB error"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> dealService.createDeal(requestDto));

        assertEquals("DB error", ex.getMessage());
    }

    @Test
    void testCreateDeal_MapperFailure() {
        doNothing().when(validationService).validateDealNotExists(requestDto);
        doNothing().when(validationService).validateCurrencies("USD", "EUR");
        when(mapper.toEntity(requestDto)).thenReturn(dealEntity);
        when(repository.save(dealEntity)).thenReturn(savedDeal);
        when(mapper.toDto(savedDeal)).thenThrow(new RuntimeException("Mapper failed"));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> dealService.createDeal(requestDto));

        assertEquals("Mapper failed", ex.getMessage());
    }

    @Test
    void testCreateDeal_NullRequestDto() {
        NullPointerException ex = assertThrows(NullPointerException.class,
                () -> dealService.createDeal(null));
    }

    @Test
    void testCreateDeal_NegativeAmount() {
        DealRequestDto invalidAmountDto = new DealRequestDto(
                "DEAL456",
                "USD",
                "EUR",
                BigDecimal.valueOf(-100)
        );

        doThrow(new RuntimeException("Amount must be positive"))
                .when(validationService).validateDealNotExists(invalidAmountDto);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> dealService.createDeal(invalidAmountDto));

        assertEquals("Amount must be positive", ex.getMessage());
    }
}
