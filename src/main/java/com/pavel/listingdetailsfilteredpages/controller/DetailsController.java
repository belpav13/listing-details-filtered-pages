package com.pavel.listingdetailsfilteredpages.controller;

import com.pavel.listingdetailsfilteredpages.helper.CSVHelper;
import com.pavel.listingdetailsfilteredpages.message.ResponseMessage;
import com.pavel.listingdetailsfilteredpages.model.DetailsEntity;
import com.pavel.listingdetailsfilteredpages.model.DetailsPage;
import com.pavel.listingdetailsfilteredpages.model.DetailsSearchCriteria;
import com.pavel.listingdetailsfilteredpages.service.CSVService;
import com.pavel.listingdetailsfilteredpages.service.DetailsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class DetailsController {

    private final DetailsService detailsService;
    private final CSVService fileService;
    public DetailsController(DetailsService detailsService, CSVService fileService) {
        this.detailsService = detailsService;
        this.fileService = fileService;
    }

    @GetMapping("/listings")
    public ResponseEntity<Page<DetailsEntity>> getDetails(DetailsSearchCriteria detailsSearchCriteria, DetailsPage detailsPage
                                                          ) {
        return new ResponseEntity<>(detailsService.getDetails(detailsPage, detailsSearchCriteria),
                HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

}
