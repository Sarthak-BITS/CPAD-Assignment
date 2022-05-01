FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/app
COPY *.jar /opt/app/booking.jar
CMD ["java", "-jar", "/opt/app/booking.jar"]