# listing-details-filtered-pages

1. Run application.
2. Upload csv by POST action
   curl --location 'http://localhost:8080/upload' \
   --form 'file=@"[path2file]/listing-details.csv"'
![img_1.png](img_1.png)
3. Open H2 in browser and check table.(optional)
   http://localhost:8080/h2/
![img.png](img.png)
4. Run Query by Get action
   curl --location 'http://localhost:8080/listings?
                                          minPrice=0.0081&
                                          maxPrice=0.15&
                                          minMinCpm=1&
                                          maxMinCpm=2& 
                                          pageNumber=1&
                                          pageSize=20'
    
    will be delivered json of second page with 20 blocks.
![img_2.png](img_2.png)
.....
![img_3.png](img_3.png)
