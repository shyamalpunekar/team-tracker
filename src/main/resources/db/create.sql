SET MODE PostgreSQL;



CREATE TABLE IF NOT EXISTS members (
  id int PRIMARY KEY auto_increment,
  memberName VARCHAR,
  teamId INTEGER,
  createdAt VARCHAR
  );

  CREATE TABLE IF NOT EXISTS teams (
  id int PRIMARY KEY auto_increment ,
   teamName VARCHAR,
   createdAt VARCHAR
  );