package com.example.deals.service;

import com.example.deals.dto.DealRequestDto;
import com.example.deals.dto.DealResponseDto;

public interface DealService {
    DealResponseDto createDeal(DealRequestDto requestDTO);

}
