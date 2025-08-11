package com.paginationpractice.demo.dto;

import java.util.List;

public record PokemonResponse(List<PokemonDto> content,
                              int pageNo,
                              int pageSize,
                              long totalElements,
                              int totalPages,
                              boolean last) {

}
