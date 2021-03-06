package org.fergjo.SparkApp;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class App {
    public static void main(String[] args) {
        String book = String.format("%s/data/books/WarAndPeace.txt", args[0]);

        SparkConf conf = new SparkConf().setAppName("Simple Application");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaRDD<String> bookData = sc.textFile(book).cache();

        long numAs = bookData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("a");
            }
        }).count();

        long numBs = bookData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("b");
            }
        }).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }
}
