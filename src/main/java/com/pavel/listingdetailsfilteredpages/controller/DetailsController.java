package com.pavel.listingdetailsfilteredpages.controller;

import com.pavel.listingdetailsfilteredpages.model.DetailsEntity;
import com.pavel.listingdetailsfilteredpages.model.DetailsPage;
import com.pavel.listingdetailsfilteredpages.model.DetailsSearchCriteria;
import com.pavel.listingdetailsfilteredpages.service.DetailsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listings")
public class DetailsController {

    private final DetailsService detailsService;

    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @GetMapping
    public ResponseEntity<Page<DetailsEntity>> getDetails(DetailsPage detailsPage,
                                                          DetailsSearchCriteria detailsSearchCriteria) {
        return new ResponseEntity<>(detailsService.getDetails(detailsPage, detailsSearchCriteria),
                HttpStatus.OK);
    }

}
