package com.example.car_service.controller;

import com.example.car_service.domain.dto.owner.*;
import com.example.car_service.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

            @Valid @ModelAttribute OwnerFilterRequest filter,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "20")
            int size,

            @RequestParam(defaultValue = "fullName")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<OwnerResponse> ownerResponse = ownerService.findWithFilter(filter, pageable);

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
