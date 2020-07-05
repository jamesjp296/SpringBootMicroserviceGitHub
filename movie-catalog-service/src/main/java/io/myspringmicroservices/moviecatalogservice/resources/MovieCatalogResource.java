package io.myspringmicroservices.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.myspringmicroservices.moviecatalogservice.models.CatalogItem;
import io.myspringmicroservices.moviecatalogservice.models.Movie;
import io.myspringmicroservices.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/Catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * @Autowired private WebClient.Builder webClientBulider;
	 */

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating ratingsList = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId,
				UserRating.class);

		return ratingsList.getUserRating().stream().map(rating -> {

			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());

		/*
		 * Movie movie = webClientBulider.build() .get()
		 * .uri("http://localhost:8082/movies/" + rating.getMovieId()) .retrieve()
		 * .bodyToMono(Movie.class) .block();
		 */
	}

}
