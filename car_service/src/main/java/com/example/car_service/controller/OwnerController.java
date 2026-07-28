package com.example.car_service.controller;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerResponse;
import com.example.car_service.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(
           @Valid @RequestBody OwnerCreateRequest request
    ) {

        OwnerResponse ownerResponse = ownerService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ownerResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(
            @PathVariable UUID id
    ) {
        OwnerResponse ownerResponse = ownerService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ownerResponse);
    }

    /*
    GET
    PATCH/PUT
    DELETE

    1) Написать валидацию входного контракта
    2) Реализовать ручки по типам запросам
        GATEWAY глянуть
     */

}
