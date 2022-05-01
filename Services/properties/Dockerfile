FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/app
COPY *.jar /opt/app/properties.jar
CMD ["java", "-jar", "/opt/app/properties.jar"]