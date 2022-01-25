FROM openjdk:8-alpine as builder
RUN  mkdir /app
COPY . /app
WORKDIR /app
RUN ./mvnw clean package

FROM builder
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8083
ENTRYPOINT [ "java", "-jar", "/app/app.jar"]