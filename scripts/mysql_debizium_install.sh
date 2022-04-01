# !/bin/bash

cd $CONFLUENT_HOME/share
echo > test.txt

inventory


{
  "name": "daraz-inventory-connector",
  "config": {
    "connector.class": "io.debezium.connector.mysql.MySqlConnector",
    "tasks.max": "1",
    "database.hostname": "localhost",
    "database.port": "3306",
    "database.user": "root",
    "database.password": "password",
    "database.server.id": "223344",
    "database.server.name": "daraz",
    "database.whitelist": "inventory",
    "database.history.kafka.bootstrap.servers": "localhost:9092",
    "database.history.kafka.topic": "schema-changes.inventory"
  }
}
