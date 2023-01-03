CREATE SCHEMA IF NOT EXISTS h2db;

DROP TABLE t_preference_item IF EXISTS;

DROP TABLE t_preference IF EXISTS;

CREATE TABLE IF NOT EXISTS t_preference
(
  id              INT AUTO_INCREMENT PRIMARY KEY,
  user_id         INT         NOT NULL,
  practice_id     INT         NOT NULL,
  preference_name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_preference_item
(
  id            INT AUTO_INCREMENT PRIMARY KEY,
  preference_id INT          NOT NULL,
  report_name   VARCHAR(64)  NOT NULL,
  item_name     VARCHAR(64)  NOT NULL,
  item_value    VARCHAR(64)  NOT NULL,
  FOREIGN KEY (preference_id) REFERENCES t_preference (id)
);
