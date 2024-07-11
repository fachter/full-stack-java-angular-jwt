# Full Stack Application

A Full Stack Application with Java, Spring Boot, PostgreSQL and Angular including JWT Authentication


## Database

Start Postgres Database with

```shell
docker-compose -f ./backend/docker-compose.yaml up
```

## Backend

Easiest way to start the backend is by importing the project as a module in IntelliJ, 
such that the maven dependencies can be installed.

Start the Backend by running [BackendApplication.java](backend/src/main/java/com/fachter/backend/BackendApplication.java)

# Frontend

The project uses [PrimeNG](https://primeng.org/) for frontend styling and useful functionality. 

Install it with the other dependencies with: 

```shell
cd frontend && npm i
```

Install Angular CLI to start the application (on port 4200) with:

```shell
cd frontend && ng serve
```