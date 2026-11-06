FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY Main.java pom.xml ./
RUN javac Main.java
EXPOSE 8080
CMD ["java", "Main"]
