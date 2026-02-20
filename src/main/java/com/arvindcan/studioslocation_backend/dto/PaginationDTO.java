package com.arvindcan.studioslocation_backend.dto;

public record PaginationDTO(
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
