# Use an OpenJDK image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy Maven/Gradle wrapper and build files
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn
COPY src ./src

# Build the project using Maven wrapper
RUN ./mvnw clean package -DskipTests

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/shopping-feedback-0.0.1-SNAPSHOT.jar"]
