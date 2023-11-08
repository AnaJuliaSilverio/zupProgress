FROM amazoncorretto:17-alpine-jdk
COPY /target/zupProgress-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

COPY init.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/init.sh
RUN apk --no-cache add python3 py3-pip && pip3 install awscli
CMD ["/usr/local/bin/init.sh"]