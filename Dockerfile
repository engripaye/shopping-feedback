# Use Eclipse Temurin JDK 21 (slim variant)
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src ./src

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the Spring Boot JAR (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/shopping-feedback-0.0.1-SNAPSHOT.jar"]
