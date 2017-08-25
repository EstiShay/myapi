# Legal Aid Clinic Management API

#### Java project for Epicodus, August 2017

#### By Esti Shay

## Description

Independent project to create an API.  This is a case management system for a legal aid clinic.  Objects include cases, clients, and attorneys.

## Demonstrated Functionality in Postman
Creating and reading new records

![Adding new client](/src/main/resources/public/images/clientsnew.jpg "Postman creation of new client")
![Show clients](/src/main/resources/public/images/clientsall.jpg "Postman display of all clients")
![Adding new attorney](/src/main/resources/public/images/attorneysnew.jpg "Postman creation of new attorney")
![Show attorneys](/src/main/resources/public/images/attorneysall.jpg "Postman display of all attorneys")
![Custom exception message for unknown ID](/src/main/resources/public/images/customExceptionMsg.jpg "Custom message for unknown record ID")

## Setup/Installation Requirements

* Clone the repo
* Run App.java
* Access routes via Postman

## Specifications

User stories:
* As an ADMIN, I want to:
    * add new attorneys
    * delete all types of records
    
* As a LEGAL ASSISTANT, I want to: 
    * add new clients
    * add new cases
    * update cases
    * update client records
    * mark cases as closed or reopen them
    
* As an ATTORNEY, I want to:
    * find all of my cases
    * find all of the cases associated with one of my clients
    * see when the next brief or filing is due, or when I next need to appear in court for a specific case



## Technologies Used

Java, Spark, SQL, H2, JSON, GSON, Postman, written in IntelliJ

### License

Licensed under GPL

Copyright &copy; 2017 Esti Shay