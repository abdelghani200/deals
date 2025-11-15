package com.example.deals.service.impl;

import com.example.deals.dto.DealRequestDto;
import com.example.deals.dto.DealResponseDto;
import com.example.deals.entity.Deal;
import com.example.deals.mapper.DealMapper;
import com.example.deals.repository.DealRepository;
import com.example.deals.service.DealService;
import com.example.deals.service.DealValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {

    private final DealRepository repository;
    private final DealMapper mapper;
    private final DealValidationService validationService;


    @Override
    public DealResponseDto createDeal(DealRequestDto requestDTO) {
        log.info("Creating deal with ID: {}", requestDTO.id());

        validationService.validateDealNotExists(requestDTO);
        validationService.validateCurrencies(
                requestDTO.orderingCurrencyIsoCode(),
                requestDTO.toCurrencyIsoCode()
        );

        Deal deal = mapper.toEntity(requestDTO);
        Deal savesDeal = repository.save(deal);

        return mapper.toDto(savesDeal);
    }

}
