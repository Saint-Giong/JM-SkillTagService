# APPLICATION BUILD
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

## --- Localize DTO ---
#COPY SG-SharedDtoPackage/pom.xml ./SG-SharedDtoPackage/pom.xml
#COPY SG-SharedDtoPackage/src ./SG-SharedDtoPackage/src
#COPY SG-SharedDtoPackage/.mvn ./SG-SharedDtoPackage/.mvn
#COPY SG-SharedDtoPackage/mvnw ./SG-SharedDtoPackage/mvnw
#
#RUN --mount=type=cache,target=/root/.m2 \
#    cd SG-SharedDtoPackage && \
#    ./mvnw clean install -DskipTests
## --------------------

# Maven runner
COPY JM-SkillTagService/mvnw .
COPY JM-SkillTagService/.mvn .mvn
RUN chmod +x mvnw

# Dependency
COPY JM-SkillTagService/pom.xml ./pom.xml
COPY JM-SkillTagService/SkillTagApi/pom.xml ./SkillTagApi/pom.xml
COPY JM-SkillTagService/SkillTagService/pom.xml ./SkillTagService/pom.xml

# Copy outside cache
COPY JM-SkillTagService/settings.xml /

RUN --mount=type=secret,id=GITHUB_USERNAME,env=GITHUB_USERNAME,required=true  \
    --mount=type=secret,id=GITHUB_KEY,env=GITHUB_KEY,required=true \
    --mount=type=cache,target=/root/.m2 \
    cp /settings.xml /root/.m2 && \
    cat /root/.m2/settings.xml && \
    ./mvnw dependency:go-offline -U

# Copy the full source code
COPY JM-SkillTagService/SkillTagApi/src ./SkillTagApi/src
COPY JM-SkillTagService/SkillTagService/src ./SkillTagService/src

# Build the Spring Boot application
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw clean package -DskipTests

# Application Run
FROM eclipse-temurin:17-jdk AS runner

WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/SkillTagService/target/*.jar app.jar

# Expose the default Spring Boot port (you can override in compose)
EXPOSE 8185

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]