# craete new topic
kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --topic quickstart-events --create

# craete topic with partion
kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --topic quickstart-events --create --partitions 3

# craete topic with partion and replication factor 
kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --topic quickstart-events --create --partitions 3 --replication-factor 1
kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --topic quickstart-events --create --partitions 3 --replication-factor 2

# List all the topic in the broker/broker cluster
kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --list 

# Get details about a Topic
kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --topic quickstart-events --describe

# Delete a Topic
kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --topic quickstart-events --delete

----------------------
# start a producer on the specific topic
kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092

# start a consumer to listen to the specific topic
kafka-console-consumer.sh --topic quickstart-events --bootstrap-server localhost:9092