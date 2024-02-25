package zw.nseremwe.service;

import org.springframework.web.multipart.MultipartFile;
import zw.nseremwe.dto.MovieDto;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException;

    MovieDto getMovie(Integer movieId);
    List<MovieDto> getAllMovies();

}
