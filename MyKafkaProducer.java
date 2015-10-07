// DESCRIPTION
// Kafka Producer Group in Java with the following characteristics:
// • Reads and sends the playing_cards_datetime.tsv dataset • Connects to localhost:9092
// • Sends messages on the hello_topic
// • Sends all messages as Strings
//
// HOW TO RUN
// When running, start your consumer first and then start the producer.
//
// AUTHOR
// Jordan Cheah     

package solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyKafkaProducer {
    public void createProducer(String filename) throws IOException {
        Properties props = new Properties();
        // Configure brokers to connect to
        props.put("bootstrap.servers", "localhost:9092");

        // Configure serializer classes
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        // Create ProducerRecord and send it
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;

        while ((line = reader.readLine()) != null) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("my_topic", "police", line);
            producer.send(record);
        }

        reader.close();
        producer.close();
    }

    public static void main(String[] args) {
        MyKafkaProducer producer = new MyKafkaProducer();

        try {
            producer.createProducer(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
