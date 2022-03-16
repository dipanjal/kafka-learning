# !/bin/sh
# KAFKA INSTALLATION

sudo apt update

# Prerequisite: JDK 8 or higher I'm installing open jdk 11 here.
# =============================================
sudo apt install default-jre -y
java -version
sleep 2s
# =============================================

# Installing Additionals
sudo apt install net-tools -y


# sudo apt install default-jdk -y
# javac -version

# DOWNLOADING KAFKA
# =============================================
echo "Downloading Kafka..."
wget https://dlcdn.apache.org/kafka/3.1.0/kafka_2.12-3.1.0.tgz
tar -xzvf kafka_2.12-3.1.0.tgz
sleep 2s
# =============================================

# Add Kafka to your path
echo "Adding Kafka in User Path..."
echo 'PATH="$PATH:$HOME/kafka_2.12-3.1.0/bin"' >> ~/.profile
echo "Kafka Installation Done"
echo "exit open a new terminal to get the reflection"

# SET Kafka Heap Size
export KAFKA_HEAP_OPTS="-Xmx400M -Xms400M"

# Setting up Kafka As a Service
cat <<EOF | sudo tee /etc/systemd/system/kafka.service

[Unit]
Requires=network.target remote-fs.target
After=network.target remote-fs.target

[Service]
Type=simple
User=ubuntu
ExecStart=/home/ubuntu/kafka_2.12-3.1.0/bin/kafka-server-start.sh /home/ubuntu/kafka_2.12-3.1.0/config/server.properties
ExecStop=/home/ubuntu/kafka_2.12-3.1.0/bin/kafka-server-stop.sh
Restart=on-abnormal

[Install]
WantedBy=multi-user.target

EOF