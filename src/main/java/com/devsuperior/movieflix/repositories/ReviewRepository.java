package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.projections.ReviewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query(nativeQuery = true, value = """
            SELECT tb_review.id, tb_review.movie_id AS movieId, tb_review.user_id AS userId, tb_review.text, tb_user.email, tb_user.name
            FROM TB_REVIEW
            INNER JOIN tb_user ON tb_review.user_id = tb_user.id
            WHERE tb_review.movie_id = :id
            """)
    List<ReviewProjection> searchReviewsByMovieId(Long id);

}
