# # Exercise with Spring Boot and Postgres

This is an exercise Java / Maven / Postgres / Spring Boot (version 2.3) application.

## How to Run 

This application is packaged as a jar. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean install```
* Once successfully built, you can run the service by:

```
        java -jar target/exercise-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run 
```

## About the Service

The service is an exercise review REST service. You can do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints defined in ```com.example.exercise.controller.DesarrolladorController``` on **port 9012**. (see below)

Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single jar on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger2 using annotations 

Here are some endpoints you can call:

```
http://localhost:9012/developers (get)
http://localhost:9012/developers (post)
http://localhost:9012/developers/{id} (put)
http://localhost:9012/developers/{id} (del)
```

### To view Swagger 2 API docs

Run the server and browse to localhost:9012/swagger-ui.html



# Running the project with MySQL

Converting it to run with another relational database such as MySQL or PostgreSQL is very easy. Since the project uses Spring Data and the Repository pattern, it's even fairly easy to back the same service with MongoDB. 

Here is what you would do to back the services with MySQL, for example: 

### In pom.xml add: 

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

### Append this to the end of application.properties: 

```
spring.datasource.url=jdbc:mysql://localhost:3306/exercise?createDatabaseIfNotExist=true
spring.datasource.username=factumadmin
spring.datasource.password=3tk98M17
spring.datasource.platform=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.generate-ddl=true
spring.jpa.properties.hibernate.hbm2ddl.auto=update

spring.jpa.show-sql=true

```

## Run with docker

Must have docker and docker-compose installed. Run using the comand

Linux

```
sudo docker-compose up --build
```


## Note

The file application.properties is configured to run docker, if you want to run tests you can apply one of two solutions

1. Add the following line to the /etc/hosts file
```
127.0.0.1 dbpostgresql
```

2. Remplace dbpostgresql in file application.properties for localhost.

# Questions and Comments: larafranklin95@gmail.com
