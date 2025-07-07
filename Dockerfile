# 构建阶段
FROM maven:3.8.7-eclipse-temurin-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 运行阶段
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/mysql-demo-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"] 
