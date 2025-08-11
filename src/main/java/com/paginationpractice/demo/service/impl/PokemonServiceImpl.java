package com.paginationpractice.demo.service.impl;

import com.paginationpractice.demo.dto.PokemonDto;
import com.paginationpractice.demo.dto.PokemonResponse;
import com.paginationpractice.demo.entity.Pokemon;
import com.paginationpractice.demo.exception.PokemonNotFoundException;
import com.paginationpractice.demo.repository.PokemonRepository;
import com.paginationpractice.demo.service.PokemonService;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

  private final PokemonRepository pokemonRepository;

  public PokemonServiceImpl(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  @Override
  public PokemonDto createPokemon(PokemonDto pokemonDto) {
    Pokemon pokemon = new Pokemon();
    pokemon.setName(pokemonDto.name());
    pokemon.setType(pokemonDto.type());

    Pokemon newPokemon = pokemonRepository.save(pokemon);

    return new PokemonDto(newPokemon.getId(), newPokemon.getName(), newPokemon.getType());
  }

  @Override
  public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
    return null;
  }

  @Override
  public PokemonDto getPokemonById(int id) {
    Pokemon pokemon = pokemonRepository.findById(id)
        .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));
    return mapToDto(pokemon);
  }

  @Override
  public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
    Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));

    pokemon.setName(pokemonDto.name());
    pokemon.setType(pokemonDto.type());

    Pokemon updatedPokemon = pokemonRepository.save(pokemon);
    return mapToDto(updatedPokemon);  }

  @Override
  public void deletePokemonId(int id) {
    Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be delete"));
    pokemonRepository.delete(pokemon);
  }

  private PokemonDto mapToDto(Pokemon pokemon) {
    return new PokemonDto(pokemon.getId(), pokemon.getName(), pokemon.getType());
  }

  private Pokemon mapToEntity(PokemonDto pokemonDto) {
    Pokemon pokemon = new Pokemon();
    pokemon.setName(pokemonDto.name());
    pokemon.setType(pokemonDto.type());
    return pokemon;
  }
}
