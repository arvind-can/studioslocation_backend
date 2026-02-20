package com.arvindcan.studioslocation_backend.dto;

import java.util.List;

/**
 * Paginated DTO used to respond to client request askip for multiple results with pagination
 *
 * @param results list of results
 * @param pagination pagination info object
 * @param <T> results type (DTO)
 */
public record PaginatedResponseDTO<T>(List<T> results, PaginationDTO pagination) {}
