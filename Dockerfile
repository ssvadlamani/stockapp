FROM java:8

EXPOSE 8080

ADD target/stockapp-0.0.1-SNAPSHOT.jar stockapp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","stockapp-0.0.1-SNAPSHOT.jar"]