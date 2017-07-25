Spark Streaming with Kafka Demo App
===================================

This is a demo project that shows how to run Spark Streaming App with Kafka
using Docker and Docker Compose.

For more details see
http://blog.antlypls.com/blog/2015/10/05/getting-started-with-spark-streaming-using-docker/

## Adaptations to the blog post

- Updated Kafka/Spark versions
- Updated Spark code for the 2.x version

- Docker compose/run

```
$ sudo docker-compose run --rm spark
Pulling kafka (spotify/kafka:latest)...
...
Pulling spark (p7hb/docker-spark:2.2.0)...
...
root@f8ba41b8ed6a:~# pwd
/root
root@f8ba41b8ed6a:~# ls /app
classes  direct_kafka_word_count.jar
```

- Create Kafka topic

```
$ sudo docker exec -it $(sudo docker-compose ps -q kafka) bash
root@bffad02cc52c:/# kafka-topics.sh --create --zookeeper $ZOOKEEPER --replication-factor 1 --partitions 2 --topic word-count
Created topic "word-count".

root@bffad02cc52c:/# kafka-topics.sh --list --zookeeper $ZOOKEEPER
word-count

root@bffad02cc52c:/# kafka-topics.sh --describe --zookeeper $ZOOKEEPER --topic word-count
Topic:word-count	PartitionCount:2	ReplicationFactor:1	Configs:
	Topic: word-count	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: word-count	Partition: 1	Leader: 0	Replicas: 0	Isr: 0
```

- Deploy and run spark job

```
$ sudo docker-compose run --rm spark
Starting sparkkafkadockerdemo_kafka_1 ... done
root@c742a8ac020a:~# ls /app/
classes  direct_kafka_word_count.jar
root@c742a8ac020a:~# spark-submit \
> --class com.example.spark.DirectKafkaWordCount \
> /app/direct_kafka_word_count.jar kafka:9092 word-count
-------------------------------------------                                     
Time: 1501024425000 ms
-------------------------------------------

[ Go to kafka shell and produce some topic content ]

-------------------------------------------
Time: 1501024560000 ms
-------------------------------------------
(Hello,1)
(World,1)

-------------------------------------------
Time: 1501024565000 ms
-------------------------------------------
(!!!,1)

[Cnt-C]
root@c742a8ac020a:~# exit
```

