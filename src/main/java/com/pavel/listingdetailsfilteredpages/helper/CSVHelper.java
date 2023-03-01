package com.pavel.listingdetailsfilteredpages.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pavel.listingdetailsfilteredpages.model.DetailsEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "session_id", "advertiser_id", "country", "price", "event_type_id", "gdpr", "min_cpm", "priority", "bid_price" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<DetailsEntity> csvToDetailsEntities(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<DetailsEntity> detailsEntities = new ArrayList<DetailsEntity>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                DetailsEntity detailsEntity = new DetailsEntity(
                        Long.parseLong(csvRecord.get("session_id")),
                        Long.parseLong(csvRecord.get("advertiser_id")),
                        csvRecord.get("country"),
                        Double.parseDouble(csvRecord.get("price")),
                        Long.parseLong(csvRecord.get("event_type_id")),
                        Integer.parseInt(csvRecord.get("gdpr")),
                        Integer.parseInt(csvRecord.get("min_cpm")),
                        Double.parseDouble(csvRecord.get("priority")),
                        Double.parseDouble(csvRecord.get("bid_price"))


                );

                detailsEntities.add(detailsEntity);
            }

            return detailsEntities;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
