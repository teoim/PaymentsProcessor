
# **Payments processor**

### Simple payment processor API exposing 4 endpoints:
- `GET /payments` 
- `GET /payments-pagination` (*pageNumber* and *pageSize* as integer request parameters)
- `GET /payments/{id}` 
- `POST /payments` 
- `DELETE /payments/{id}`

### The app will run on default port 8080.


## Docker
### The [docker image](https://hub.docker.com/repository/docker/teoim/payment-processor/general) is not dockerfile-based, instead it is built with maven, using the following command:
### `./mvnw spring-boot:build-image -DskipTests`
#### This is currently the official Spring-recommended way of building docker images for Spring applications. It will do so starting from a light(er) base docker image.

#### Download the image: 
- `docker pull teoim/payment-processor:latest`
#### Run image: 
In the following commands, please replace the <local-port> and <desired-container-name> by, respectively, an available port on your machine and a name for the running container.
- `docker run -p <local-port>:8080 --name <desired-container-name> teoim/payment-processor:latest`  

This will map the <local-port> to the docker container internal port 8080, where the app will be listening for requests.  
The app will now be reachable at `http://localhost:<local-port>/payments` 


## H2 Database 
### The H2 database console is accessible at `http://localhost:<local-port>/h2-console` and the following settings: 
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:payments`
- User Name: `sa`
- Password: \<empty password\>

### The `payments` database is automatically created at startup based on the settings from the `application.properties` file.

### The `payment` table and fields are also automatically created at startup, through JPA based on the `com/rftech/payments/processor/repository/dao/PaymentDAO.java` entity.

### At startup, 3 records will be automatically loaded in the database, based on the `src/main/resources/import.sql` file. The tests are written starting from these 3 records. 

