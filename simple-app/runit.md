$ sbt package

# Use spark-submit to run your application
  $ ../spark-1.0.2-bin-hadoop2/bin/spark-submit \
    --class "SimpleApp" \
    --master local[4] \
    target/scala-2.10/simple-project_2.10-1.0.jar \
    /Users/johnferguson/Documents/Code/spark-pres

# The command for a "remote" master with workernodes
    ../spark-1.0.2-bin-hadoop2/bin/spark-submit \
    --class "SimpleApp" \
    --master spark://Johns-MacBook-Pro-2.local:7077 \
    target/scala-2.10/simple-project_2.10-1.0.jar \
    /Users/johnferguson/Documents/Code/spark-pres
