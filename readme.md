**Jakarta EE 10 demo project**
- JPA (Hibernate)
- JAX-RS

JPQL, HQL, SQL queries for PostgreSQL DB, sample data is in resources.

Need to use application server to run this app.

Example configuration is in `.idea/runConfigurations/JBoss_WildFly_31_0_0_Final.xml`



**Endpoints:**
* `/hello` - just text
* `/awards` - list of queries
* `/awards/jpql-get-all` - JPQL query using persistence.EntityManager
* `/awards/hql-get-all`  - HQL query using hibernate.SessionFactory
* `/awards/sql-get-awards-after-2022` - Native SQL query using persistence.EntityManager