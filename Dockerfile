# 1. Base image (Gradle 설치된 이미지)
FROM gradle:8.5.0-jdk17 AS build

# 2. 프로젝트 복사
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# 3. JAR 빌드
RUN gradle build --no-daemon

# 4. 실행 이미지
FROM openjdk:17-jdk-alpine

# 5. 빌드한 JAR 복사
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

# 6. 포트 오픈
EXPOSE 8080

# 7. 실행 명령
ENTRYPOINT ["java", "-jar", "/app.jar"]
