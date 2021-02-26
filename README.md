# spring-boot-vue-keycloak

Testing a Spring Boot API protected by Keycloak and using VueJS as frontend framework. 

## Keycloak

There is an existing Keycloak setup in `backend/init/realm.json`

How to run it:

```bash
cd backend
docker-compose up
```

It will start a Keycloak instance on 8888 port with these properties:
```
realm=spring-boot-vue-keycloak
client_id=spring-boot-vue-keycloak-backend
client_secret=e354b3e4-bb33-4ee5-8090-199fc99351bb
```

## Backend

```bash
cd backend
./mvnw spring-boot:run
```

## Frontend

Go to the frontend folder, install the dependencies for the first time and run the development server

```bash
cd frontend
npm install
npm run serve
```