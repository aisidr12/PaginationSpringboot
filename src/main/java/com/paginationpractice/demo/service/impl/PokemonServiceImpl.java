package com.paginationpractice.demo.service.impl;

import com.paginationpractice.demo.dto.PokemonDto;
import com.paginationpractice.demo.dto.PokemonResponse;
import com.paginationpractice.demo.entity.Pokemon;
import com.paginationpractice.demo.exception.PokemonNotFoundException;
import com.paginationpractice.demo.repository.PokemonRepository;
import com.paginationpractice.demo.service.PokemonService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  /**
   * This method retrieve all the information in pages in order to be show according to the Page
   * number and page Size
   *
   * @param pageNo   pageNumber starting in 0
   * @param pageSize how many in size to be shown in the page
   * @return
   */
  @Override
  public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
    List<Pokemon> listOfPokemon = pokemons.getContent();
    List<PokemonDto> content = listOfPokemon.stream().map(this::mapToDto).toList();

    return new PokemonResponse(content, pokemons.getNumber(), pokemons.getSize(),
        pokemons.getTotalElements(), pokemons.getTotalPages(), pokemons.isLast());
  }

  @Override
  public PokemonDto getPokemonById(int id) {
    Pokemon pokemon = pokemonRepository.findById(id)
        .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));
    return mapToDto(pokemon);
  }

  @Override
  public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
    Pokemon pokemon = pokemonRepository.findById(id)
        .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));

    pokemon.setName(pokemonDto.name());
    pokemon.setType(pokemonDto.type());

    Pokemon updatedPokemon = pokemonRepository.save(pokemon);
    return mapToDto(updatedPokemon);
  }

  @Override
  public void deletePokemonId(int id) {
    Pokemon pokemon = pokemonRepository.findById(id)
        .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be delete"));
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
