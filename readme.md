This branch is for different id types and primary keys testing.

### Info about ids testing:

1. To look how hibernate generates tables based on entities with different id types, do the following:
   - Find entities in `src/main/java/my/example/awards/pktest/sourceentities` and unconnect `@Entity` annotation
   - Open `src/main/resources/META-INF/persistence.xml` and uncomment entities from `sourceentities` package
   - Set properties:
     ```
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true"/>
            <property name="hibernate.highlight_sql" value="true"/>
            <property name="hibernate.hbm2ddl.auto" value="create"/>
     ```
   - Run the app and open URL `/hello/init` to trigger db creating
   - Look at the console output and at database itself
     - The results of hibernate generating and ddl from db are located in files started with `pk` and located in `src/main/resources/sql-scripts/ids-test/`
2. To test reverse engineering functionality:
    - Create Postgres db and run sql script located in `src/main/resources/sql-scripts/ids-test/tables_for_pk_testing_postgres.sql`
    - Execute "Generate Entities from DB" action using `reversegen` package
    - If you want to validate results with hibernate, do the following:
      - Open `src/main/resources/META-INF/persistence.xml` and uncomment entities from `reversegen` package. Comment others.
      - Set property `<property name="hibernate.hbm2ddl.auto" value="validate"/>`
      - Replace entity used in query in `my.example.awards.queries.JpqlQueries.getAllIdentityIdEntity` with any of generated ones 
      - Run the app and open URL `/hello/init` to trigger db validating



### Info about base project:

**Jakarta EE 10 demo project**
- JPA (Hibernate)
- JAX-RS

JPQL, HQL, SQL queries for PostgreSQL DB, sample data is in resources.

Need to use application server to run this app.

Example configuration is in `.idea/runConfigurations/JBoss_WildFly_31_0_0_Final.xml`

Original `persistence.xml` and `hibernate.cfg.xml` are located in `resources/ignore` folder.

**Endpoints:**
* `/hello` - just text with links to other endpoints
* `/awards` - list of queries
* `/awards/jpql-get-all` - JPQL query using persistence.EntityManager
* `/awards/hql-get-all`  - HQL query using hibernate.SessionFactory
* `/awards/hql-get-awards-of-high-rated-players` - HQL query with condition
* `/awards/sql-get-awards-after-2022` - Native SQL query using persistence.EntityManager
* `/players` - list of queries
* `/players/jpql-get-from-sport-club` - JPQL query with parameter
* `/referee/` - list of queries
* `/referee/hql-get-with-licence-number` - HQL query with parameter