package com.example.car_service.controller;

import com.example.car_service.domain.dto.car_owner.CarOwnerCreateRequest;
import com.example.car_service.domain.dto.car_owner.CarOwnerCreateResponse;
import com.example.car_service.service.CarOwnerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car-owner")
public class CarOwnerController {

    private final CarOwnerService carOwnerService;

    @PostMapping
    public ResponseEntity<CarOwnerCreateResponse> createCarOwner(
           @RequestBody CarOwnerCreateRequest request
    ) {

        CarOwnerCreateResponse carOwnerCreateResponse = carOwnerService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(carOwnerCreateResponse);

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
