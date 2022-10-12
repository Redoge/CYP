FROM maven:3.8.6-openjdk-18

COPY ./ ./

RUN mvn package -Dmaven.test.skip=true




#COPY ./target/CYP-0.0.1-SNAPSHOT.jar ./target/CYP-0.0.1-SNAPSHOT.jar


