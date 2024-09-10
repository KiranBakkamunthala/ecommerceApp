I have developed a new Spring Boot application using Java 17 and Spring Boot 3.3.3. The application employs proper annotations and is built using Spring Tools Suite (STS). 
It includes four HTTP methods: POST for saving data, GET for retrieving data by ID 
or fetching all entries, PUT for updating records, and DELETE for removing records from the database.

The application features two entity classes: one for Customer and one for Product, each with relevant properties. 
I’ve used Swagger for API documentation and testing, implemented logging, and utilized MySQL for data storage, retrieval, and modification.

The application also includes a global exception handling mechanism and is managed through GitHub, supporting push and pull operations. 
Unit tests are written using JUnit and Mockito, and both Redis caching and Kafka are incorporated for performance and event handling.

