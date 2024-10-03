**Jakarta EE 10 demo project**
- JPA (Hibernate)
- JAX-RS

JPQL, HQL, SQL queries for PostgreSQL DB, sample data is in resources.

Need to use application server to run this app.

Example configuration is in `.idea/runConfigurations/JBoss_WildFly_31_0_0_Final.xml`



**Endpoints:**
* `/hello` - just text with links to other endpoints
* `/db/getUsers` - prints a list of users from `pg_user` table (from "postgres" schema)
* `/awards` - list of queries
* `/awards/jpql-get-all` - JPQL query using persistence.EntityManager
* `/awards/hql-get-all`  - HQL query using hibernate.SessionFactory
* `/awards/hql-get-awards-of-high-rated-players` - HQL query with condition
* `/awards/sql-get-awards-after-2022` - Native SQL query using persistence.EntityManager
* `/players` - list of queries
* `/players/jpql-get-from-sport-club` - JPQL query with parameter
* `/referee/` - list of queries
* `/referee/hql-get-with-licence-number` - HQL query with parameter


### How to run the application with Tomcat server and Postgres database in Docker

1. Open the terminal in a project's root directory.
2. Make sure docker is running (on Windows — only with Docker Desktop)
   - `docker version`
3. Create a new network
   - `docker network create app-network`
4. Run database image and connect it to the network
   - `docker run --name db-postgres15 -d -e POSTGRES_PASSWORD=mypassword postgres:15`
   - `docker network connect app-network db-postgres15`
5. Rebuild application artifact
   - Setup connection, username and password in xml configuration files (`persistence.xml` and `hibernate.cfg`)
       - Set DB URL to `jdbc:postgresql://db-container-name/postgres`
       - Set password to `mypassword`
   - Rebuild project
6. Rebuild image for the application and run it in the network
   - `docker build -t awards-app:1.0 .`
   - `docker run --name awards-jpa-app -d -p 8080:8080 --network=app-network  awards-app:1.0`
7. Go to URL http://localhost:8080/demoJPA-awards-1.0-SNAPSHOT/hello
   - Ensure app is running in Tomcat server
8. Go to URL http://localhost:8080/demoJPA-awards-1.0-SNAPSHOT/db/get-users
   - Ensure db is connected (see only "postgres" as a result)


### How to do the same with simple way using docker-compose
1. Open the terminal in a project's root directory.
2. Run command `docker-compose up -d`
3. Go to URL http://localhost:8080/demoJPA-awards-1.0-SNAPSHOT/hello
   - Ensure app is running in Tomcat server 
4. Go to URL http://localhost:8080/demoJPA-awards-1.0-SNAPSHOT/db/get-users
   - Ensure db is connected (see only "postgres" as a result)