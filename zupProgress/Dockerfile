FROM amazoncorretto:17-alpine-jdk
COPY /target/zupProgress-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

COPY init.sh /usr/local/bin/
RUN apk --no-cache add python3 py3-pip && pip3 install awscli
RUN aws configure set aws_access_key_id AKIAUSXQ5LQTYHIHWOXD
RUN aws configure set aws_secret_access_key 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
RUN aws configure set region us-east-1
