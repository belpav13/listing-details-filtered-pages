mvn clean install
docker build . -t belpav/listing-details-filtered-pages:1.0.0
docker push belpav/listing-details-filtered-pages:1.0.0
