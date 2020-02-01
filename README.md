This is spring boot application with angular js pages. 

1. Describes CRUD operations using restful API.
2. Kafka consumer/producer example as well.
3. Angular js controller


Kafka Steps:

1. Install Cygwin
2. Download confluent kafka
3. navigate to downloaded folder ex: cd /cygdrive/d/work/softwares/confluent-5.3.2/bin
4. start zookeeper - ./zookeeper-server-start ../etc/kafka/zookeeper.properties
5. start kafka - ./kafka-server-start ../etc/kafka/server.properties
6. create a topic using command - "./kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic customer_loader"
7. Send a message to topic "./kafka-console-producer --broker-list localhost:9092 --topic customer_loader"
8. Publish message to kafka topic "customer_event"

8. Watch the messages "kafka-console-consumer --bootstrap-server localhost:9092 --topic customer_event --from-beginning"
