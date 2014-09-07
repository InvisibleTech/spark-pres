import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SimpleApp {
 def main(args: Array[String]) {
   val conf = new SparkConf().setAppName("Simple Application")
   val sc = new SparkContext(conf)

   val bookToRead = s"${args(0)}/data/books/WarAndPeace.txt"
   
   val bookData = sc.textFile(bookToRead).cache()

   val numAs = bookData.filter(line => line.contains("a")).count()
   val numBs = bookData.filter(line => line.contains("b")).count()
   println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
 }
}
