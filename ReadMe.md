# Bet Settlement service
    Service used to publish a bet settlement payload and publishes it to Kafka topic event-outcomes. 
    Then from kafka we are having one listener event-outcomes which on receving records check whether
    record is in our db exist or not. If exist pushed to RocketMQ

#### Things required
   * Java(JRE, JVM) installed 21 or 21+ 
   * Docker needs to be installed (Docker has been used for local setup of kafka and RocketMQ)

### How to run
*   First go to deployment folder while lying execute command(docker compose up -d) this will start docker and setup will be done
  * Now Simply Run SportyAssignmentApplication
