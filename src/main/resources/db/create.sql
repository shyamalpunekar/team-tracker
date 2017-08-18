SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS members (
  id int PRIMARY KEY auto_increment,
  memberName VARCHAR,
  memberId int
  );

  CREATE TABLE IF NOT EXISTS teams (
  id int PRIMARY KEY auto_increment,
   teamName VARCHAR
  );