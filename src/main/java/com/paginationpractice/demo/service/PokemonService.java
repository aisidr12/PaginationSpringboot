package com.paginationpractice.demo.service;

import com.paginationpractice.demo.dto.PokemonDto;
import com.paginationpractice.demo.dto.PokemonResponse;

public interface PokemonService {

  PokemonDto createPokemon(PokemonDto pokemonDto);
  PokemonResponse getAllPokemon(int pageNo, int pageSize);
  PokemonDto getPokemonById(int id);
  PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
  void deletePokemonId(int id);

}
