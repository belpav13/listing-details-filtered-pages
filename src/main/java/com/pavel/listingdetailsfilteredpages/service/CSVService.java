package com.pavel.listingdetailsfilteredpages.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.pavel.listingdetailsfilteredpages.helper.CSVHelper;
import com.pavel.listingdetailsfilteredpages.model.DetailsEntity;
import com.pavel.listingdetailsfilteredpages.repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class CSVService {
    @Autowired
    DetailsRepository repository;

    public void save(MultipartFile file) {
        try {
            List<DetailsEntity> detailsEntities = CSVHelper.csvToDetailsEntities(file.getInputStream());
            repository.saveAll(detailsEntities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<DetailsEntity> getAllDetails() {
        return repository.findAll();
    }

}
