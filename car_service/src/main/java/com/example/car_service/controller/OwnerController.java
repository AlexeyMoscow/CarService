package com.example.car_service.controller;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerCreateResponse;
import com.example.car_service.service.OwnerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerCreateResponse> createOwner(
           @RequestBody OwnerCreateRequest request
    ) {

        OwnerCreateResponse ownerCreateResponse = ownerService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ownerCreateResponse);

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
