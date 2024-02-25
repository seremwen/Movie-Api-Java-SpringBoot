package zw.nseremwe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zw.nseremwe.dto.MovieDto;
import zw.nseremwe.service.MovieService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie/")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @PostMapping("add-movie")
    public ResponseEntity<MovieDto> addMovie(@RequestPart MultipartFile file, @RequestPart String movieDto) throws IOException {
       MovieDto dto= convertToMovieDto(movieDto);
       return  new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED);

    }
    private MovieDto convertToMovieDto(String movieDtoObject) throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        return objectMapper.readValue(movieDtoObject,MovieDto.class);
    }
    @GetMapping("{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Integer movieId){
     return ResponseEntity.ok(movieService.getMovie(movieId));
    }
    @GetMapping("all")
    public ResponseEntity<List<MovieDto>> getAll(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
}
