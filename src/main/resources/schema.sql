DROP TABLE IF EXISTS card_details;

CREATE TABLE card_details (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  card_number VARCHAR(250) NOT NULL,
  card_name VARCHAR(250) NOT NULL,
  card_limit Decimal(6,2)
);