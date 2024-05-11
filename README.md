# Project Overview

## Table of Contents
* [General Info](#general-info)
* [Features](#features)
* [Technologies](#technologies)
* [Setup](#setup)

## General Info
This project is a web application developed using Spring Boot and Java. It provides a platform for job seekers and employers, allowing them to create, update, and manage their profiles, as well as search for jobs and candidates.

## Features
The main features of the project include:
* User authentication and authorization using Spring Security and OAuth2.
* CRUD operations for job seekers and employers.
* Search functionality for jobs and candidates.
* Metrics collection and reporting.
* Validation of user input data.
* Error handling and response.

## Technologies
The project is created with:
* Java: The main programming language used for development.
* Spring Boot: Used to create stand-alone, production-grade Spring-based applications.
* Spring Security: Provides authentication and authorization support.
* Spring Data JPA: Provides a way to reduce boilerplate code to interact with the database.
* MySQL: The database used for persisting data.
* Maven: Used for managing project's build.
* Lombok: Used to reduce boilerplate code for model/data objects.
* SpringDoc OpenAPI: Used for API documentation.

## Setup
To run this project, follow the steps below:

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Make sure you have Maven installed. You can check by running the command `mvn -v`.
4. Run the project using Maven with the command `mvn spring-boot:run`.

```bash
$ git clone https://github.com/lppduy/recruitment-service
$ cd recruitment-service
$ mvn spring-boot:run
```

Please ensure you have the necessary environment variables set up and the MySQL server running.