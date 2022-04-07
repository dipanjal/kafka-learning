sudo apt update

# install Java JRE
sudo apt install default-jre -y
java -version
sleep 2s

# Download Confluent Platform
wget https://packages.confluent.io/archive/7.0/confluent-7.0.1.tar.gz
# Extracting tar archive
sleep 2s
tar -xzvf confluent-7.0.1.tar.gz

# Path Configuration
sleep 2s
echo export CONFLUENT_HOME='$HOME/confluent-7.0.1' >> .profile
echo export PATH='$PATH:$CONFLUENT_HOME/bin' >> .profile
echo "Exit the terminal and re-open the session"

schema-registry-start ./etc/schema-registry/schema-registry.properties