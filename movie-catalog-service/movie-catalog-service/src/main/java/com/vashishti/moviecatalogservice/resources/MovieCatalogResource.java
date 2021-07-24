package com.vashishti.moviecatalogservice.resources;

import com.vashishti.moviecatalogservice.models.CatalogItem;
import com.vashishti.moviecatalogservice.models.UserRating;
import com.vashishti.moviecatalogservice.resources.services.UserRatingInfo;
import com.vashishti.moviecatalogservice.services.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = this.userRatingInfo.getUserRating(userId);
        return userRating.getUserRatings().stream().map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }
}