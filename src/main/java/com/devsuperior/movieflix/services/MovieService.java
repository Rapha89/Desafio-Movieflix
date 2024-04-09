package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> movie = repository.findById(id);
        Movie entity = movie.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado!"));
        return new MovieDetailsDTO(entity, entity.getGenre());
    }

    public Page<MovieCardDTO> findByGenre(Pageable pageable, String genreId) {
        if(genreId.equals("0")){
            Page<MovieProjection> page = repository.findByGenre(pageable, null);
            return page.map(x -> new MovieCardDTO(x));
        }
        Page<MovieProjection> page = repository.findByGenre(pageable, genreId);
        return page.map(x -> new MovieCardDTO(x));
    }


}
