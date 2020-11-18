FROM maven:latest
 
COPY . /root

WORKDIR /root

RUN mvn verify

CMD ["mvn", "spring-boot:run"]
