FROM openjdk:17-jdk-slim

# 한국 시간대 설정
RUN apt-get update && apt-get install -y tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && \
    echo "Asia/Seoul" > /etc/timezone && \
    dpkg-reconfigure -f noninteractive tzdata
    
# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일과 리소스 파일을 복사
COPY build/libs/woosan-0.0.1-SNAPSHOT.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
