$ sbt package

# Use spark-submit to run your application
$ YOUR_SPARK_HOME/bin/spark-submit \
  --class "SimpleApp" \
  --master local[4] \
  target/scala-2.10/simple-project_2.10-1.0.jar \
  /Users/johnferguson/Documents/Code/spark-pres

# The command for a "remote" master with workernodes
    ../spark-1.0.2-bin-hadoop2/bin/spark-submit \
    --class "SimpleApp" \
    --master spark://new-host.home:7077 \
    target/scala-2.10/simple-project_2.10-1.0.jar \
    /Users/johnferguson/Documents/Code/spark-pres