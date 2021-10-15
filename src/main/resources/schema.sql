CREATE TABLE IF NOT EXISTS city
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    latitude    VARCHAR(250) NOT NULL,
    longitude   VARCHAR(250) NOT NULL,
    country VARCHAR(2) NOT NULL
);