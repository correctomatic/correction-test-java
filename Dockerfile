# Stage 1: Build the project using Maven
# FROM maven:3.8.4-openjdk-11 AS builder
FROM maven:3.9.6-eclipse-temurin-8-alpine AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Run the Maven build to compile the code and package it
RUN mvn clean compile
RUN mvn test-compile
RUN mvn dependency:copy-dependencies -DincludeScope=test

# Install curl
# RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
# Install curl (alpine version)
RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*


# Download the JUnit Platform Console Standalone JAR
RUN curl -L -o /app/junit-platform-console-standalone.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.2/junit-platform-console-standalone-1.8.2.jar

# Stage 2: Create the runtime image
# Need jdk because we need to compile the student's code
FROM openjdk:11-jdk-slim

WORKDIR /app

# Copy the compiled classes and test classes from the builder stage
COPY --from=builder /app/target/classes /app/classes
COPY --from=builder /app/target/test-classes /app/test-classes
COPY --from=builder /app/target/dependency /app/dependency
COPY --from=builder /app/junit-platform-console-standalone.jar /app/junit-platform-console-standalone.jar

# Copy the script to run tests
COPY run_tests.sh .

# Ensure the script is executable
RUN chmod +x run_tests.sh

# # Entry point to run the tests
# CMD ["./run_tests.sh"]
