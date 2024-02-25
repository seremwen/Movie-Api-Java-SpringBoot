package zw.nseremwe.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zw.nseremwe.dto.MovieDto;
import zw.nseremwe.entities.Movie;
import zw.nseremwe.repositories.MovieRepository;
import zw.nseremwe.service.FileService;
import zw.nseremwe.service.MovieService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final FileService fileService;
    @Value("${project.poster}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;
    public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        //1. Upload file
       String uploadedFileName= fileService.uploadFile(path,file);

        //2. Set the value of poster as filename
        movieDto.setPoster(uploadedFileName);

        //3. map mmoviedto to movie object
        Movie movie= new Movie(
                movieDto.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getReleaseYear(),
                movieDto.getMovieCast(),
                movieDto.getPoster()
        );
        //4 Save movie object
      Movie savedMovie=  movieRepository.save(movie);
        //5. generate posterUrl

        String posterUrl= baseUrl+"/file/"+ uploadedFileName;
        //6. map movie object to dto object and return
        MovieDto response= new MovieDto(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getReleaseYear(),
                savedMovie.getMovieCast(),
                savedMovie.getPoster(),
                posterUrl
        );
        return response;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        // Check the data in DB and if exist return data of the given ID

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie Not found"));

        //generate posterUrl

        String posterUrl= baseUrl+"/file/"+ movie.getPoster();

        MovieDto response= new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getReleaseYear(),
                movie.getMovieCast(),
                movie.getPoster(),
                posterUrl
        );
        return response;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        //1. fetch all data from DB
       List<Movie> movies= movieRepository.findAll();
       List<MovieDto> movieDtos = new ArrayList<>();
        //2. iterate through the list, genaerate posterUrl for each movieObject, and map to movie DTO object
        for(Movie movie:movies){
            String posterUrl= baseUrl+"/file/"+ movie.getPoster();
            MovieDto movieDto= new MovieDto(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getReleaseYear(),
                    movie.getMovieCast(),
                    movie.getPoster(),
                    posterUrl
            );
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }
}
