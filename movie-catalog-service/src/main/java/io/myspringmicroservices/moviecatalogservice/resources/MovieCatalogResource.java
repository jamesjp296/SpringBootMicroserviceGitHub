package io.myspringmicroservices.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.myspringmicroservices.moviecatalogservice.models.CatalogItem;
import io.myspringmicroservices.moviecatalogservice.models.Movie;
import io.myspringmicroservices.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/Catalog")
public class MovieCatalogResource {

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		RestTemplate restTemplate = new RestTemplate();
		
		List<Rating> ratingsList = Arrays.asList(new Rating("1234", 4), new Rating("5647", 5));
		
		
		

		return ratingsList.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+ rating.getMovieId() , Movie.class);
			return new CatalogItem(movie.getName() ,"Test", rating.getRating());
		}).collect(Collectors.toList());

	}
}
