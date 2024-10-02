FROM tomcat:10-jdk17

COPY target/demoJPA-awards-1.0-SNAPSHOT.war /usr/local/tomcat/webapps
# app will be accessible by http://localhost:8080/demoJPA-awards-1.0-SNAPSHOT/hello

EXPOSE 8080
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]