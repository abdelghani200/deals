package com.example.deals.mapper;

public package com.example.deals.mapper;

import com.example.deals.dto.DealRequestDto;
import com.example.deals.dto.DealResponseDto;
import com.example.deals.entity.Deal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealMapper {

    Deal toEntity(DealRequestDto dto);

    DealResponseDto toDto(Deal entity);

}
 {
    
}
