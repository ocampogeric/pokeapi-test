# Instructions to run project

## 1.- Download gradle dependencies
```bash
gradle build
```

## 2.- Init MySql database
Move to docker compose folder
```bash
cd pokemondb-docker-compose
```
Init MySql service with docker-compose and the existing file docker-compose.yml, _mysql-data_ folder will be the temporal data volume for our mysql container, do not modify it.
```bash
docker-compose up -d
```
Wait a few seconds for mysql service it's ready for accept connections.
Then, go back to _build/lib/_ folder and execute compiled and generated jar
```bash
cd ../build/lib/
java -jar ocampogeric-soap-pokemon-0.0.1.jar
```

This will execute a server in localhost:8082 with our SOAP WebService :)

## 3.- Consume soap web service
You can execute soapui or your favourite method to consume it.
