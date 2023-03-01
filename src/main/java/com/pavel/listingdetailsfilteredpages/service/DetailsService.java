package com.pavel.listingdetailsfilteredpages.service;

import com.pavel.listingdetailsfilteredpages.model.DetailsEntity;
import com.pavel.listingdetailsfilteredpages.model.DetailsPage;
import com.pavel.listingdetailsfilteredpages.model.DetailsSearchCriteria;
import com.pavel.listingdetailsfilteredpages.repository.DetailsCriteriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DetailsService {
    private final DetailsCriteriaRepository detailsCriteriaRepository;

    public DetailsService(DetailsCriteriaRepository detailsCriteriaRepository) {
        this.detailsCriteriaRepository = detailsCriteriaRepository;
    }


    public Page<DetailsEntity> getDetails(DetailsPage detailsPage,
                                          DetailsSearchCriteria detailsSearchCriteria) {
        return detailsCriteriaRepository.findAllWithFilters(detailsPage, detailsSearchCriteria);
    }
}
