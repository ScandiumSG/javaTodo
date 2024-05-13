# What is this?

This repository contains a docker compose todolist project, consisting of a Java/Springboot backend with a postgres database, a angular server-side rendering (SSR) frontend, and a dedicated login/signup server in Java/Springboot with a separate postgres database. 

The project is fully containerized with docker compose. 

## How to run

The project requires docker installed for the intended experience. If docker is installed you simple navigate to the project root and run the command
```
docker compose up
```

This will build and initialize all services. The frontend will be available at `localhost:4200`.

## Without docker (Not recommended)

The project can run without docker aswell, but both Node, Java, and maven needs to be installed. If desired just navigate to the project folder and run the start commands.

For todoapp
```
npm run start_ssr
```

For api or login
```
mvn clean install && mvn spring-boot:run
```
