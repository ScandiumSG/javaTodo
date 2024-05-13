# javaTodo
A Java login server for the simple todolist.

The purpose of the repository is just to explore Springboot and make a simple (but functional) backend for a todo list application. 

The project is intended to be run with Docker Compose, with an exposed port at 9001, the postgres database within the docker compose is not accessible outside of the Springboot project. 

## Requirements

This service needs a keystore.pfx file in the root dir (same level as this README).

The following commands was used to generate the keystore, using a openssl docker container.
```
openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout privateKey.key -out certificate.crt

openssl pkcs12 -export -out keystore.pfx -inkey privateKey.key -in certificate.crt -name "testingKey"
```

The keystore.pfx is then exported and put into this projects root.