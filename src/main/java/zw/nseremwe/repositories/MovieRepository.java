package zw.nseremwe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.nseremwe.entities.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
