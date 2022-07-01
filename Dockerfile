FROM openjdk:11
ADD target/quizzz-0.0.1-SNAPSHOT.jar quizzz-0.0.1-SNAPSHOT.jar
COPY ./src/main/resources/files/Questions.xlsx src/main/resources/files/Questions.xlsx
EXPOSE 8080
ENTRYPOINT ["java","-jar","/quizzz-0.0.1-SNAPSHOT.jar"]