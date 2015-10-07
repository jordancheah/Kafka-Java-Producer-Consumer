																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									package solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;

public class MyConsumer {
    public void createConsumer() {
        String topic = "my_topic";

        Properties props = new Properties();
        // Configure ZooKeeper location
        props.put("zookeeper.connect", "localhost");
        // Configure consumer group
        props.put("group.id", "group1");

        // Use the configuration to create the ConsumerConnector
        ConsumerConfig consumerConfig = new ConsumerConfig(props);

        // Create ConsumerConnector with createJavaConsumerConnector
        ConsumerConnector consumerConnector = Consumer
                                              .createJavaConsumerConnector(consumerConfig);

        // Create a map of topics we are interested in with the number of
        // streams (usually threads) to service the topic
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1);

        // Get the list of streams and configure it to use Strings
        Map<String, List<KafkaStream<String, String>>> consumerMap =
            consumerConnector
            .createMessageStreams(topicCountMap, new StringDecoder(null),
                                  new StringDecoder(null));

        // Get the stream for the topic we want to consume
        KafkaStream<String, String> stream = consumerMap.get(topic).get(0);

        // Iterate through all of the messages in the stream
        ConsumerIterator<String, String> it = stream.iterator();

        // Note this should done with threads as this is a blocking call
        while (it.hasNext()) {
            MessageAndMetadata<String, String> messageAndMetadata = it.next();

            String key = messageAndMetadata.key();
            String value = messageAndMetadata.message();

            System.out.println("Key is \"" + key + "\" value is \"" + value +
                               "\"");
        }

        // Shutdown the connector once we are done with it
        consumerConnector.shutdown();
    }

    public static void main(String[] args) {
        MyConsumer consumer = new MyConsumer();
        consumer.createConsumer();
    }
}
