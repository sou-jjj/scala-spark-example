package com.soujjj.scalaspark.example

import com.soujjj.scalaspark.example.util.SampleData
import org.apache.spark.rdd.RDD

object WordCountJob extends SparkJob {
  def main(args: Array[String]): Unit = {
    val map = sparkContext
      .textFile(SampleData.getPath("alice.txt"))
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))

    val counts: RDD[(String, Int)] = map.reduceByKey(_ + _)
    counts
      .sortBy(f => f._2, ascending = false)
      .take(100)
      .foreach(println)

    sparkContext.stop()
  }

  override def name(): String = this.getClass.toString
}
