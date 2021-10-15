INSERT INTO city(name, latitude, longitude, country)
SELECT *
FROM CSVREAD('src/main/resources/cities.csv');