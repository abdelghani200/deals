package com.example.deals.controller;

import com.example.deals.dto.DealRequestDto;
import com.example.deals.dto.DealResponseDto;
import com.example.deals.service.DealService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/deals")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping
    public ResponseEntity<DealResponseDto> createDeal(@Valid @RequestBody DealRequestDto dto) {

        DealResponseDto res = dealService.createDeal(dto);

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

}
