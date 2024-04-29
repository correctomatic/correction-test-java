# FROM maven:3.8.4-jdk-11 AS builder
FROM maven:3.8.4-jdk-11 AS builder

WORKDIR /app

COPY ./pom.xml .
# COPY ./foo/my-project/pom.xml .
# Cache dependencies for improving build time
# (doesn't seem to work very well, review later)
RUN mvn -B dependency:go-offline

COPY ./src ./src
# COPY ./foo/my-project/src ./src
# RUN mvn -B package -DskipTests


# Use a Java base image
# FROM ibmjava:8-sfj

# # Set working directory
# WORKDIR /usr/src/app

# # Copy the test source code into the container
# # COPY src/ src/

# # Compile the test code
# # RUN javac -cp junit-platform-console-standalone-1.8.2.jar -d . src/*.java

# # Copy JAR with JUnit tests and any other necessary files into the container
# # COPY your-junit-tests.jar .
# COPY grade.py .

# # Make the script executable
# # RUN chmod +x run_tests.sh

# # Set the entry point script
# # ENTRYPOINT ["./run_tests.sh"]
