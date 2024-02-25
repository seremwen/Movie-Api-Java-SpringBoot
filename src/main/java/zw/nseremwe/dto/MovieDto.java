package zw.nseremwe.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Integer movieId;

    @NotBlank(message = "Please provide title")
    private String title;

    @NotBlank(message = "Please provide director")
    private String director;

    @NotBlank(message = "Please provide studio")
    private String studio;

    private Integer releaseYear;

    private Set<String> movieCast;

    @NotBlank(message = "Please provide poster")
    private String poster;

    @NotBlank(message = "Please provide poster")
    private String posterUrl;
}
