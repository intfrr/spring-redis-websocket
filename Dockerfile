FROM openjdk:8-jre-alpine
VOLUME /tmp
ARG JAR_FILE
ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+UseSerialGC"

COPY ${JAR_FILE} /opt/app.jar

RUN addgroup bootapp && \
    adduser -D -S -h /var/cache/bootapp -s /sbin/nologin -G bootapp bootapp

WORKDIR /opt
USER bootapp
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/app.jar"]

#ADD *.jar app.jar
#RUN sh -c 'touch /app.jar'
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]