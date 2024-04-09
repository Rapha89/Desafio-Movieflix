package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	Optional<Movie> findById(Long id);

	@Query(nativeQuery = true, value = """
   			SELECT * FROM (
			SELECT tb_movie.id, tb_movie.title, tb_movie.sub_title AS subTitle, tb_movie.movie_year AS movieYear, tb_movie.img_url AS imgUrl
			FROM TB_MOVIE
			INNER JOIN tb_genre on tb_genre.id = tb_movie.genre_id
			WHERE (:genreId IS NULL OR tb_genre.id = :genreId)
			ORDER BY TITLE
			) AS tb_result
			""", countQuery = """
   			SELECT COUNT(*) FROM (
   			SELECT tb_movie.id, tb_movie.title, tb_movie.sub_title AS subTitle, tb_movie.movie_year AS movieYear, tb_movie.img_url AS imgUrl
			FROM TB_MOVIE
			INNER JOIN tb_genre on tb_genre.id = tb_movie.genre_id
			WHERE (:genreId IS NULL OR tb_genre.id = :genreId)
			ORDER BY TITLE
	        ) AS tb_result
			""")
	Page<MovieProjection> findByGenre(Pageable pageable, String genreId);

}
