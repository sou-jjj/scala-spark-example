package com.soujjj.scalaspark.example.sql

import com.soujjj.scalaspark.example.util.SampleData

object MarketingCampaignJob extends SparkSQLJob {
  override def name(): String = this.getClass.toString

  def main(args: Array[String]): Unit = {
    val marketingCampaigns = sparkSession.read
      .format("csv")
      .option("inferSchema", "true")
      .option("header", "true")
      .option("sep", "\t")
      .csv(SampleData.getPath("marketing_campaign.tsv"))
    marketingCampaigns.createOrReplaceTempView("marketing_campaign")

    val result = marketingCampaigns.sqlContext.sql(
      """
        |SELECT
        | Year_Birth
        | , Income
        | , Marital_Status
        | , Recency
        | , MntWines
        |FROM marketing_campaign
        |ORDER BY 
        | Income DESC
        |""".stripMargin
    )
    result.show(100)
  }
}
