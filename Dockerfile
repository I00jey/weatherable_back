# 1. Base image (JDK 17 사용)
FROM openjdk:17-jdk-alpine

# 2. JAR 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 3. 포트 오픈
EXPOSE 8080

# 4. 앱 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
