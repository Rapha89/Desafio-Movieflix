package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	List<Genre> findAll();

}
