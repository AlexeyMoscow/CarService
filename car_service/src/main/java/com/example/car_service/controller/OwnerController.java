package com.example.car_service.controller;

import com.example.car_service.domain.dto.owner.*;
import com.example.car_service.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping
    public ResponseEntity<OwnerPageResponse> findOwnersWithFilter(

            @Valid @ModelAttribute OwnerSearchRequest searchRequest
    ) {
        Page<OwnerResponse> ownerResponse = ownerService.findWithFilter(searchRequest);

        OwnerPageResponse ownerPageResponse = new OwnerPageResponse(
                ownerResponse.getContent(),
                ownerResponse.getNumber(),
                ownerResponse.getSize(),
                ownerResponse.getTotalElements(),
                ownerResponse.getTotalPages()
        );

        return ResponseEntity.ok(ownerPageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(
            @PathVariable UUID id
    ) {
        OwnerResponse ownerResponse = ownerService.findById(id);

        return ResponseEntity.ok(ownerResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwnerById(
            @PathVariable UUID id,
            @Valid @RequestBody OwnerUpdateRequest updatedOwner
    ) {
        OwnerResponse updatedOwnerResponse = ownerService.updateOwnerById(id, updatedOwner);

        return ResponseEntity.ok(updatedOwnerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwnerById(
            @PathVariable UUID id
    ) {
        ownerService.deleteOwnerById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
