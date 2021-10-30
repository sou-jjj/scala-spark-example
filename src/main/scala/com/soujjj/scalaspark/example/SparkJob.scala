package com.soujjj.scalaspark.example

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

abstract class SparkJob {
  val sparkConf    = new SparkConf().setAppName(name())
  val sparkContext = new SparkContext(sparkConf)
  val sparkSession = SparkSession.builder().appName(name()).getOrCreate()
  def name(): String
}
