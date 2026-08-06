package com.example.car_service.controller;

import com.example.car_service.domain.dto.owner.*;
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

    @GetMapping
    public ResponseEntity<OwnerPageResponse> findOwnersWithFilter(

            @Valid @ModelAttribute OwnerSearchRequest searchRequest
    ) {
        OwnerPageResponse ownerResponse = ownerService.findWithFilter(searchRequest);

        return ResponseEntity.ok(ownerResponse);
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
        ownerService.softDeleteOwnerById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/admin")
    public ResponseEntity<Void> hardDeleteOwnerById(
            @PathVariable UUID id
    ) {
        ownerService.hardDeleteOwnerById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restoreOwnerById(
            @PathVariable UUID id
    ) {
        ownerService.restoreOwner(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
