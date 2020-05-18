FROM openjdk:8-jdk-alpine
MAINTAINER ZhangPeng

ENV PROFILE test

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD target/xxl-job-admin-*.jar /xxl-job-admin.jar

ENTRYPOINT ["java", "-jar","./xxl-job-admin.jar","--spring.profiles.active=${PROFILE}"]

EXPOSE 8089