# The command for localhost startup instance using 4 nodes - uber jar
[Docs for submitting](http://spark.apache.org/docs/latest/submitting-applications.html)

    ../../../SparkSummit/spark/bin/spark-submit \
    --class "org.fergjo.SparkApp.App" \
    --master local[4] \
    target/SparkApp-0.0.1-SNAPSHOT-jar-with-dependencies.jar

## Build the uber jar
cd /Users/johnferguson/Documents/Code/java/FileSparkLocal/SparkApp
mvn clean compile assembly:single


# The command for a "remote" master with workernodes
    ../../../SparkSummit/spark/bin/spark-submit \
    --class "org.fergjo.SparkApp.App" \
    --master spark://new-host.home:7077 \
    target/SparkApp-0.0.1-SNAPSHOT.jar 

## Build the naked jar
cd /Users/johnferguson/Documents/Code/spark-pres/SparkApp
mvn clean package

## Start the master and workers
/Users/johnferguson/Documents/Code/spark-pres/spark-1.0.2-bin-hadoop2/sbin/start-master.sh

In case of emergencey, break glass:

sbin/stop-all.sh 

Get the workers going... in different shells.
./bin/spark-class org.apache.spark.deploy.worker.Worker spark://new-host.home:7077

./bin/spark-class org.apache.spark.deploy.worker.Worker spark://new-host.home:7077 --webui-port 8082

http://localhost:8080/






