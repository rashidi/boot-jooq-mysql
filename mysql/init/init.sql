USE demo;

CREATE TABLE users(
  `id` INT AUTO_INCREMENT PRIMARY KEY ,
  `username` VARCHAR(60) NOT NULL UNIQUE
);

INSERT INTO users (username) VALUES ('rashidi'), ('rudyard.kipling'), ('robert.frost');
