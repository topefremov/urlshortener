# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:8-jre-alpine

MAINTAINER efremov

ARG VER

LABEL name="URL shortener microservice - ID Generator"
LABEL description="URL shortener microservice. Project for freeCodeCamp."
LABEL version=${VER}
LABEL url="http://localhost:8181/api"
LABEL vendor="@efremov"


ADD target/idgenerator-swarm.jar /opt/idgenerator-swarm.jar
EXPOSE 8081
ENV JAVA_TOOL_OPTIONS="-Djava.net.preferIPv4Stack=true"
ENTRYPOINT ["java", "-jar", "/opt/idgenerator-swarm.jar"]

