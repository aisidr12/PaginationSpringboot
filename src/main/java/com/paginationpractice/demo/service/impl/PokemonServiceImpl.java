package com.paginationpractice.demo.service.impl;

import com.paginationpractice.demo.dto.PokemonDto;
import com.paginationpractice.demo.dto.PokemonResponse;
import com.paginationpractice.demo.service.PokemonService;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

  @Override
  public PokemonDto createPokemon(PokemonDto pokemonDto) {
    return null;
  }

  @Override
  public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
    return null;
  }

  @Override
  public PokemonDto getPokemonById(int id) {
    return null;
  }

  @Override
  public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
    return null;
  }

  @Override
  public void deletePokemonId(int id) {

  }
}
