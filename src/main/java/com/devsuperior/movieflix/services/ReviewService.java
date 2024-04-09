package com.devsuperior.movieflix.services;


import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository repository;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto){
        Review entity = new Review();
        copyDtoToEntity(dto, entity);
        return dto;
    }

    private void copyDtoToEntity(ReviewDTO dto, Review entity){
        Movie movie = movieRepository.findById(dto.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        entity.setMovie(movie);
        UserDTO userDTO = userService.getProfile();
        entity.setUser(new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail()));
        entity.setText(dto.getText());
        entity = repository.save(entity);
        dto.setId(entity.getId());
        dto.setUserId(userDTO.getId());
        dto.setUserName(entity.getUser().getName());
        dto.setUserEmail(entity.getUser().getEmail());
    }

}
