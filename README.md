# Team Tracker

#### _A Team Tracker extended Web Application for Epicodus Java Week 2 Independent Project, Aug 18, 2017_

#### By _**Shyamal Punekar**_

## Description

This is a program that allows a Startup Weekend organizer or hack-a-thon coordinator to track teams and their members.
Management software with dashboard for a hypothetical Teams to allow to do the following:
* VIEW all Teams and each Teams's member;
* ADD a Team and add Team member to particular Team;
* UPDATE Team details and member details;
* DELETE Team if no longer required and delete a member if they no longer required.

## Specifications

* It allows the user to add new teams.
  * _Example Input: Team Name: ROCK_
  * _Example Output: "Registration Confirmed. Your new team has been added."_
* It allows the user to add new members to an existing team.
  * _Example Input: Member Name: Kate_
  * _Example Output: Registration Confirmed. New testMember has been added to team IT Crowd_

  ## What's Included

```
team-tracker
├── README.md
├── build.gradle
├── .gitignore
└── src
     ├── main
     │     ├── java
     │     │     ├── App.java
     │     │     ├── Member.java
     │     │     ├── Team.java
     │     │     
     │     └── resources
     │             └── templates
     │                     ├── index.hbs
     │                     ├── layout.hbs
     │                     ├── testMember-details.hbs
     │                     ├── success.hbs
     │                     ├── testMember-form.hbs
     │                     ├── testMember-success.hbs
     │                     ├── success.hbs
     │                     ├── team-detail.hbs
     │                     ├── team-form.hbs
     │                     └── team-testMember.hbs
     └── test
           └── java
                 ├── MemberTest.java
                 └── TeamTest.java
```

## Setup/Installation Requirements

You will need [gradle](https://gradle.org/gradle-download/) installed on your device.

* `$ git clone https://github.com/shyamalpunekar/team-tracker-extended`
* In PSQL:
  * CREATE DATABASE hair_salon;
  * \c hair_salon;
  * CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, hire_date varchar, base_salary varchar, work_schedule varchar);
  * CREATE TABLE clients (id serial PRIMARY KEY, name varchar, appt_date varchar, cut_request varchar, stylist_id int);
* _In the cloned repository root directory, run the command 'gradle run'
* Navigate to `localhost:4567` in a web browser of your choice.


## Support and contact details

Please feel free to contact shyamal.punekar@gmail.com if you have any questions, issues, concerns, comments or suggestions.

## Technologies Used

* Java
* jUnit
* Gradle
* html/css
* spark
* Javascript
* handlebars
* postgres

### License

_Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.


Copyright (c) 2017 **_Shyamal Punekar_**
