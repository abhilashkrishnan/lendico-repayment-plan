# Lendio Loan Repayment Plan Generator

```Loan repayment plan generator using Java 8 and Spring Boot.```

## Running the application

Build with Maven

```mvn clean install```

### Run the application

Start the Spring Boot application

```
cd PROJECT_ROOT_DIR 
java -jar target/lendico-repayment-plan-0.0.1-SNAPSHOT.jar 
```

### Test the application

Using REST Client, send a HTTP POST request

```
http://localhost:8080/generate-plan
```

Send JSON payload in the body of the HTTP POST request

```{
"loanAmount": "5000",
"nominalRate": "5.0",
"duration": 24,
"startDate": "2018-01-01T00:00:01Z"
}
```
