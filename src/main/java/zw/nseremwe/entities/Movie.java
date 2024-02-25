package zw.nseremwe.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    @Column(nullable = false, length = 200)
    @NotBlank(message = "Please provide title")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "Please provide director")
    private String director;
    @Column(nullable = false)
    @NotBlank(message = "Please provide studio")
    private String studio;
    @Column(nullable = false)
    @NotBlank(message = "Please provide release year")
    private Integer releaseYear;
    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;
    @Column(nullable = false)
    @NotBlank(message = "Please provide poster")
    private String poster;
}
