# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:8-jre-alpine

MAINTAINER efremov

ARG VER

LABEL name="URL shortener microservice"
LABEL description="URL shortener microservice. Project for freeCodeCamp."
LABEL version=${VER}
LABEL url="http://localhost:8080/u/"
LABEL vendor="@efremov"


ADD target/urlshortener-swarm.jar /opt/urlshortener-swarm.jar
EXPOSE 8080
ENV JAVA_TOOL_OPTIONS="-Djava.net.preferIPv4Stack=true"
ENTRYPOINT ["java", "-jar", "/opt/urlshortener-swarm.jar"]

