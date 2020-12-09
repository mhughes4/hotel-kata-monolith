FROM maven:latest
 
COPY . /root

WORKDIR /root

CMD ["mvn", "spring-boot:run"]
