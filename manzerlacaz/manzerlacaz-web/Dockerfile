FROM alpine

RUN apk update && \
    apk upgrade 
RUN apk add openjdk17

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} manzerback.jar
EXPOSE 8080
CMD ["java","-jar","manzerback.jar"]