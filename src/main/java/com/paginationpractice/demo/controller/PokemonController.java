package com.paginationpractice.demo.controller;

import com.paginationpractice.demo.dto.PokemonDto;
import com.paginationpractice.demo.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class PokemonController {

  private final PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @PostMapping("pokemon/create")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
    return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
  }


  @PutMapping("pokemon/{id}/update")
  public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int pokemonId) {
    PokemonDto response = pokemonService.updatePokemon(pokemonDto, pokemonId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("pokemon/{id}/delete")
  public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId) {
    pokemonService.deletePokemonId(pokemonId);
    return new ResponseEntity<>("Pokemon delete", HttpStatus.OK);
  }

}
