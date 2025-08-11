package com.paginationpractice.demo.repository;

import com.paginationpractice.demo.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

}
