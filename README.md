# api-examples

Requirements: Java 8, MySQL database, Maven

This project provides sample code in Java that demonstrates how to pull data from the Searchlight API and store it in a
MySQL database.

When executed, this code will pull in rank and search volume data for specified accounts and store it in the database
along with all necessary dimensional data.

To execute:

1) Replace the placeholder on the first line of db_scripts/create_schemas.sql with the name of the database to read data into.

2) Replace the placeholders in src/main/resources/conductorAPI.properties with their actual values.

3) Run `mvn compile exec:java` from the main directory of the project. Execution may take a few minutes.
