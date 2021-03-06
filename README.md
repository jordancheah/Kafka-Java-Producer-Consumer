# Kafka-Java-Producer-Consumer

This program illustrates how to create Kafka Producer and Kafka Consumer in Java.

Kafka Producer Group:
* Reads and sends the playing_cards_datetime.tsv dataset 
* Connects to localhost:9092
* Sends messages on my_topic, with key = my_key
* Sends all messages as Strings

Kafka Consumer Group:
* Consumes messages sent on my_topic 
* Connects to ZooKeeper on localhost
* Consumes all data as Strings
* Outputs the contents of the messages to the screen

## How To Compile
```
mvn eclipse:eclipse
mvn package
```

## How To Run
Start ZooKeeper, Kafka server (broker), and the Schema Registry in separate windows.
```
$ cd confluent-1.0/
$ ./bin/zookeeper-server-start ./etc/kafka/zookeeper.properties
$ ./bin/kafka-server-start ./etc/kafka/server.properties
$ ./bin/schema-registry-start ./etc/schema-registry/schema-registry.properties
```

Then start your consumer first and then start the producer.  For example, if your IDE is Eclipse, this is how you would run:
```
Run from Eclipse:
Right-click - solution - MyConsumer.java - Run - Java Application
Right-click - solution - MyKafkaProducer.java - Run As - Run Configurations
    Program arguments = playing_cards_datetime.tsv
    click Apply, then click Run
```


## Results
```
Key is "my_key" value is "2015-01-14 23:21:46    cafaef78-110d-4ebb-9aaa-66a903101bca    TexasHoldem    Heart    8"
Key is "my_key" value is "2015-01-14 23:21:46    cafaef78-110d-4ebb-9aaa-66a903101bca    TexasHoldem    Diamond    8"
Key is "my_key" value is "2015-01-14 23:21:46    f95b4858-c4c2-4c7d-80b7-8e436589caea    Blackjack    Heart    Jack"
```

