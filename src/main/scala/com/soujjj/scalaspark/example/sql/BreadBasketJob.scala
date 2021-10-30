package com.soujjj.scalaspark.example.sql

import com.soujjj.scalaspark.example.util.SampleData

object BreadBasketJob extends SparkSQLJob {
  def main(args: Array[String]): Unit = {
    val breadBasket = sparkSession.read
      .format("csv")
      .option("inferSchema", "true")
      .option("header", "true")
      .load(SampleData.getPath("bread_basket.csv"))
    breadBasket.createOrReplaceTempView("bread_basket")

    val result = breadBasket.sqlContext.sql("""
        |SELECT
        | Item
        | , COUNT(*) as cnt
        |FROM
        | bread_basket
        |GROUP BY
        | Item
        |ORDER BY
        | cnt DESC
        |""".stripMargin)
    result.show(100)
  }

  override def name(): String = this.getClass.toString
}
