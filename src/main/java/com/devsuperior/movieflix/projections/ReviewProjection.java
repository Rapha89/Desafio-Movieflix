package com.devsuperior.movieflix.projections;

public interface ReviewProjection {

    Long getId();
    Long getMovieId();
    Long getUserId();
    String getText();
    String getEmail();
    String getName();


}
