# example-rest-api
Example code to create a rest api, that read cdv data file and populate
a memory database and create integrations tests to validate this data.

# Build
To run the build you can use the command line
```shell
  ./mvnw clean install
```
# Start Application

This application run using Apache Maven embedded, so you can run in root directory
## To run using default configuration
```shell
 ./mvnw spring-boot:run
```
## To run updating configuration
```shell
 ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-DCSV_INITIALIZATION_FILE=./specification/movielist.csv -DH2_CONSOLE_ENABLED=false"
```

## Open database management
By default, the H2 Console open in `localhost:8080/h2-console` 
but you can change this value run the application.
The property name is **H2_CONSOLE_PATH**
